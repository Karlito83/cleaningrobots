package de.tud.swt.cleaningrobots.behaviours;

import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;
import de.tud.swt.cleaningrobots.hardware.Wlan;
import de.tud.swt.cleaningrobots.merge.MergeAll;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;
import de.tud.swt.cleaningrobots.util.NearRobotInformation;

public class MergeAllOfNearBehaviour extends Behaviour {
	
	private Wlan wlan;	
	private MergeAll ma;
	
	private int maxCount;
	private List<NearRobotInformation> robotInformation;
	private boolean firstStart;
	
	public MergeAllOfNearBehaviour(RobotCore robot) {
		super(robot);
		
		this.ma = new MergeAll();
		this.maxCount = 100;
		this.firstStart = true;
		this.robotInformation = new LinkedList<NearRobotInformation>();
		
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		hardware.put(Components.WLAN, 1);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
		
		//Vision Radius aus Wlan Hardwarecomponente ziehen
		for (HardwareComponent hard : d.getHcs())
		{
			if (hard.getComponents() == Components.WLAN)
			{
				wlan = (Wlan)hard;
			}
		}
	}

	@Override
	public boolean action() {
		
		//Schalte alle Hardwarecomponenten an wenn sie nicht schon laufen
		for (HardwareComponent hard : d.getHcs())
		{
			if (!hard.isActive())
			{
				hard.changeActive();
			}
		}
		
		if (this.firstStart) {
			//create robot information map
			List<RobotCore> nearRobots = this.getRobot().getICommunicationProvider().getAllRobots();
			nearRobots.remove(this.getRobot());
			
			for (RobotCore core : nearRobots) {
				robotInformation.add(new NearRobotInformation(core.getName()));
			}
		}
		
		for (NearRobotInformation i: robotInformation) {
			if (i.getCounter() > -1) {
				i.addCounterOne();
				if (i.getCounter() > maxCount)
					i.resetCounter();
			}
		}
		
		
		
		if (this.getRobot().getPosition().equals(getRobot().getDestinationContainer().getLoadStationPosition()))
			return false;
				
		//Tausche komplettes modell von ronny
		long startTime = System.nanoTime();
		logger.trace("enter getNearRobotsAndImportModel");
		
		List<RobotCore> nearRobots = this.getRobot().getICommunicationProvider().getNearRobots(10);//wlan.getVisionRadius());
		nearRobots.remove(this.getRobot());
		for (RobotCore nearRobot : nearRobots) {
			//darf nur mi Robotern in der nähe Kommunizieren wenn diese Wirklich die gleiche HardwareComponente habe und diese aktiv ist
			if (nearRobot.hasActiveHardwareComponent(wlan.getComponents())) {
				for (NearRobotInformation i: robotInformation) {
					if (i.getName().equals(nearRobot.getName())) {
						if (i.getCounter() == -1) {
							ImportExportConfiguration config = new ImportExportConfiguration();
							config.world = true;
							config.knownstates = true;
							config.knowledge = true;
							EObject model = nearRobot.exportModel(config);
							ma.importAllModel(model, this.getRobot(), config);
							i.addCounterOne();
						}
					}
				}				
			}
		}
		
		long endTime = System.nanoTime();
		logger.info("Importing the data from " + nearRobots.size() + " other agents took " + (endTime - startTime) + " ns.");
		
		logger.trace("exit getNearRobotsAndImportModel");
		return false;
	}
}

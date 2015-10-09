package de.tud.swt.cleaningrobots.behaviours;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;
import de.tud.swt.cleaningrobots.hardware.Wlan;
import de.tud.swt.cleaningrobots.merge.MergeAll;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;

public class MergeAllRonny extends Behaviour {

	private final Logger logger = LogManager.getRootLogger();
	
	private Wlan wlan;
	
	private MergeAll ma;
	
	private int counter;
	private int maxCount;
	
	public MergeAllRonny(RobotCore robot) {
		super(robot);
		
		this.ma = new MergeAll();
		this.counter = 0;
		this.maxCount = 100;
		
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
		
		counter++;
		
		//Schalte alle Hardwarecomponenten an wenn sie nicht schon laufen
		for (HardwareComponent hard : d.getHcs())
		{
			if (!hard.isActive())
			{
				hard.changeActive();
			}
		}
		
		if (this.getRobot().getPosition().equals(getRobot().getDestinationContainer().getLoadStationPosition()))
			return false;
		
		if (counter < maxCount)
			return false;
		
		//Tausche komplettes modell von ronny
		long startTime = System.nanoTime();
		logger.trace("enter getNearRobotsAndImportModel");
		
		List<RobotCore> nearRobots = this.getRobot().getICommunicationProvider().getNearRobots(10);//wlan.getVisionRadius());
		nearRobots.remove(this.getRobot());
		for (RobotCore nearRobot : nearRobots) {
			//darf nur mi Robotern in der nähe Kommunizieren wenn diese Wirklich die gleiche HardwareComponente habe und diese aktiv ist
			if (nearRobot.hasActiveHardwareComponent(wlan.getComponents())) {
				ImportExportConfiguration config = new ImportExportConfiguration();
				config.world = true;
				config.knownstates = true;
				config.knowledge = true;
				EObject model = nearRobot.exportModel(config);
				ma.importAllModel(model, this.getRobot(), config);
				
				counter = 0;
			}
		}
		
		long endTime = System.nanoTime();
		logger.info("Importing the data from " + nearRobots.size() + " other agents took " + (endTime - startTime) + " ns.");
		
		logger.trace("exit getNearRobotsAndImportModel");
		return false;
	}
}

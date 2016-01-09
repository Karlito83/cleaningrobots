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

/**
 * Behavior which realize the data exchange and integration between two robots.
 * Without any roles and with the EMF model.
 * 
 * @author Christopher Werner
 *
 */
public class MergeAllOfNearBehaviour extends Behaviour {
	
	private Wlan wlan;	
	private MergeAll ma;
	
	private int maxCount;
	private List<NearRobotInformation> robotInformation;
	private boolean firstStart;
	
	public MergeAllOfNearBehaviour(RobotCore robot) {
		super(robot);
		
		this.ma = new MergeAll(this.robot.configuration);
		this.maxCount = 200;
		this.firstStart = true;
		this.robotInformation = new LinkedList<NearRobotInformation>();
		
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		hardware.put(Components.WLAN, 1);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
		
		//get vision Radius from the WLAN component
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
		
		//start all hardware components
		for (HardwareComponent hard : d.getHcs())
		{
			hard.switchOn();
		}
		
		if (this.firstStart) {
			//create robot information map
			List<RobotCore> nearRobots = this.robot.getICommunicationAdapter().getAllRobots();
			nearRobots.remove(this.robot);
			
			for (RobotCore core : nearRobots) {
				robotInformation.add(new NearRobotInformation(core.getName()));
			}
			this.firstStart = false;
		}
		
		//increment all counters or reset them
		for (NearRobotInformation i: robotInformation) {
			if (i.getCounter() > -1) {
				i.addCounterOne();
				if (i.getCounter() > maxCount)
					i.resetCounter();
			}
		}		
		
		if (this.robot.isLoading)
			return false;
						
		List<RobotCore> nearRobots = this.robot.getICommunicationAdapter().getNearRobots(20);//wlan.getVisionRadius());
		nearRobots.remove(this.robot);
		for (RobotCore nearRobot : nearRobots) {
			//could only communicate with near robots if they have active WLAN
			if (nearRobot.hasActiveHardwareComponent(wlan.getComponents())) {
				for (NearRobotInformation i: robotInformation) {
					if (i.getName().equals(nearRobot.getName())) {
						if (i.getCounter() == -1) {
							ImportExportConfiguration config = new ImportExportConfiguration();
							config.world = true;
							config.knownstates = true;
							config.knowledge = true;
							EObject model = nearRobot.exportModel(config);
							ma.importAllModel(model, this.robot, config);
							i.addCounterOne();
						}
					}
				}				
			}
		}
		return false;
	}
}

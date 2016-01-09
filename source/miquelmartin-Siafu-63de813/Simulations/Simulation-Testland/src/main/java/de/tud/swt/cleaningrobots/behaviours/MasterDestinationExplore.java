package de.tud.swt.cleaningrobots.behaviours;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.MasterRole;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;
import de.tud.swt.cleaningrobots.hardware.Wlan;
import de.tud.swt.cleaningrobots.merge.MasterFieldMerge;
import de.tud.swt.cleaningrobots.util.RobotDestinationCalculation;

/**
 * Behavior that calculate new destinations for the explorer and has contact if the followers are loading.
 * 
 * @author Christopher Werner
 *
 */
public class MasterDestinationExplore extends Behaviour {

	private MasterRole mr;
	
	private Wlan wlan;
	private boolean firstStart;
	private int calculationAway;
	private MasterFieldMerge mfm;	
	
	private Map<String, RobotDestinationCalculation> information;
	
	public MasterDestinationExplore(RobotCore robot, MasterRole mr) {
		super(robot);
		
		//create and add the states
		this.mr = mr;
		this.mfm = new MasterFieldMerge(this.robot.configuration);
		this.information = new HashMap<String, RobotDestinationCalculation>();
		this.firstStart = true;
		
		//add the hardware components and proof there correctness
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
	public boolean action() throws Exception {
		//start all hardware components
		for (HardwareComponent hard : d.getHcs())
		{
			hard.switchOn();
		}
		
		if (this.firstStart)
		{
			double maxAway = 0;
			//create information list with follower robots
			List<RobotRole> follower = this.mr.getFollowers();
				
			for (RobotRole rr : follower) {
				RobotCore core = rr.getRobotCore();
				if (core.hasHardwareComponent(Components.WLAN) && core.hasHardwareComponent(Components.LOOKAROUNDSENSOR))
				{
					//add Robot to Map
					information.put(core.getName(), new RobotDestinationCalculation(core.getName()));
					double away = Math.sqrt(core.getAccu().getMaxFieldGoes(core.getMinEnergie()));
					
					if (maxAway < away)
						maxAway = away;
				}
			}			
			
			this.calculationAway = (int) maxAway;
			this.firstStart = false;
		}
				
		//search near Explore Robots
		List<RobotCore> nearRobots = this.robot.getICommunicationAdapter().getNearRobots(wlan.getVisionRadius());
		nearRobots.remove(this.robot);
				
		for (RobotDestinationCalculation rdc : information.values()) {
			//set all NeedNew to false
			rdc.needNew = false;
			//change new and old destination if not near
			if (rdc.newDest != null)
			{
				boolean change = true;
				//look if in nearRobots
				for (RobotCore nearRobot : nearRobots) 
				{
					if (nearRobot.getName().equals(rdc.getName()))
					{
						//robot in near robots, not change
						change = false;						
					}
				}
				if (change)
				{
					//set newDest to null and change to oldDest
					rdc.oldDest = rdc.newDest;
					rdc.newDest = null;
				}
			}
		}
		
		//if no nearRobots end this behavior
		if (nearRobots.isEmpty())
			return false;

		//search robots that need new destination		
		boolean newOneFind = false;
		
		for (RobotCore nearRobot : nearRobots) {
			//look if near robot has active WLAN and is in information and need new destination
			if (nearRobot.hasActiveHardwareComponent(wlan.getComponents()))// && nearRobot.hasHardwareComponent(Components.LOOKAROUNDSENSOR)) 
			{
				//search same Robot
				for (RobotDestinationCalculation rdc : information.values()) {
					if (nearRobot.getName().equals(rdc.getName())) 
					{
						if (rdc.newDest == null)
						{
							newOneFind = true;
							rdc.needNew = true;
						}
					}
				}
			}
		}
		
		//end if no one need new destination		
		if (!newOneFind)
			return false;
		
		Map<String, RobotDestinationCalculation> result = this.robot.getWorld().getNextUnknownFields(information, calculationAway); 
		
		if (result == null) {
			//set all destination to null that the robot could shut down
			for (RobotCore nearRobot : nearRobots) {
				for (RobotDestinationCalculation rdc : information.values()) {
					if (rdc.getName().equals(nearRobot.getName()))
					{
						mfm.sendNullDestination(nearRobot.getName());
						nearRobot.getDestinationContainer().setMasterDestination(null);
					}
				}
			}
			return false;
		}
		
		information = result; 
		
		//send new Information to nearRobots which needed new Information
		for (RobotCore nearRobot : nearRobots) {
			for (RobotDestinationCalculation rdc : information.values()) {
				if (rdc.getName().equals(nearRobot.getName()) && rdc.needNew)
				{
					mfm.sendDestination(nearRobot.getName());
					nearRobot.getDestinationContainer().setMasterDestination(rdc.newDest);
				}
			}
		}
		return false;
	}

}

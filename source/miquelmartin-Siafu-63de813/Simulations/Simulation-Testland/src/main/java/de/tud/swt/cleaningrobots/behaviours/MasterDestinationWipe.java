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
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.util.RobotDestinationCalculation;

public class MasterDestinationWipe extends Behaviour {

	private MasterRole mr;

	private State STATE_HOOVE;
	private State STATE_WIPE;
	
	private Wlan wlan;
	private boolean firstStart;
	private int calculationAway;
	private MasterFieldMerge mfm;	
	
	private Map<String, RobotDestinationCalculation> information;
	
	public MasterDestinationWipe(RobotCore robot, MasterRole mr) {
		super(robot);
		
		this.STATE_HOOVE = ((State)robot.configuration.as).createState("Hoove");
		this.STATE_WIPE = ((State)robot.configuration.as).createState("Wipe");
		
		this.mr = mr;
		this.mfm = new MasterFieldMerge(this.robot.configuration);
		this.information = new HashMap<String, RobotDestinationCalculation>();
		
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
		firstStart = true;
	}

	@Override
	public boolean action() throws Exception {
		//Schalte alle Hardwarecomponenten an wenn sie nicht schon laufen
		for (HardwareComponent hard : d.getHcs())
		{
			if (!hard.isActive())
			{
				hard.changeActive();
			}
		}
		
		if (firstStart)
		{
			double maxAway = 0;
			//create information list with follower robots
			List<RobotRole> follower = this.mr.getFollowers();
				
			for (RobotRole rr : follower) {
				RobotCore core = rr.getRobotCore();
				if (core.hasHardwareComponent(Components.WLAN) && core.hasHardwareComponent(Components.WIPER))
				{
					//add Robot to Map
					information.put(core.getName(), new RobotDestinationCalculation(core.getName()));
					double away = Math.sqrt(core.getAccu().getMaxFieldGoes(core.getMinEnergie()));
					
					if (maxAway < away)
						maxAway = away;
				}
			}			
			
			this.calculationAway = (int) maxAway;
			System.out.println("Information: " + information.keySet() + " Away: " + calculationAway + " maxAway: " + maxAway);			
			this.firstStart = false;
		}
				
		//search near Explore Robots
		List<RobotCore> nearRobots = this.robot.getICommunicationAdapter().getNearRobots(wlan.getVisionRadius());
		nearRobots.remove(this.robot);
				
		for (RobotDestinationCalculation rdc : information.values()) {
			//alle NeedNew auf false setzen
			rdc.needNew = false;
			//new und old dest tauschen wenn nicht mehr in Reichweite
			if (rdc.newDest != null)
			{
				boolean change = true;
				//schaue ob noch in nearRobots
				for (RobotCore nearRobot : nearRobots) 
				{
					if (nearRobot.getName().equals(rdc.getName()))
					{
						//Roboter noch in Reichweite also nicht umsetzen
						change = false;						
					}
				}
				if (change)
				{
					//setze newDest zurück auf null und erneuere oldDest
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
			//look if near robot has active Wlan and is in information and need new destination
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
		
		//end if no one need new dest		
		if (!newOneFind)
			return false;
		
		Map<String, RobotDestinationCalculation> result = this.robot.getWorld().getNextPassablePositionsByStateWithoutState(information, calculationAway, STATE_HOOVE, STATE_WIPE); 
		
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

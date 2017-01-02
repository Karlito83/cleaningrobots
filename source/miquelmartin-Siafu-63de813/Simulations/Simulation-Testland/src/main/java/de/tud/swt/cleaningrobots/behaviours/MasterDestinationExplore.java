package de.tud.swt.cleaningrobots.behaviours;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.hardware.ComponentTypes;
import de.tud.swt.cleaningrobots.hardware.Wlan;
import de.tud.swt.cleaningrobots.merge.DestinationMerge;
import de.tud.swt.cleaningrobots.roles.MasterRole;
import de.tud.swt.cleaningrobots.util.RobotDestinationCalculation;

/**
 * Behavior that calculate new destinations for the explorer and has contact if the followers are loading.
 * 
 * @author Christopher Werner
 *
 */
public class MasterDestinationExplore extends Behaviour {

	private MasterRole mr;
	
	private int visionRadius;
	private int calculationAway;
	private DestinationMerge merge;	
	
	private Map<String, RobotDestinationCalculation> information;
	
	public MasterDestinationExplore(RobotCore robot, MasterRole mr) {
		super(robot);
		
		//create and add the states
		this.mr = mr;
		this.merge = new DestinationMerge(this.robot.getConfiguration());
		this.information = new HashMap<String, RobotDestinationCalculation>();
		
		Wlan wlan = (Wlan) this.d.getHardwareComponent(ComponentTypes.WLAN);
		this.visionRadius = wlan.getMeasurementRange();
	}
	
	@Override
	protected void addSupportedStates() {
		//no states needed...		
	}

	@Override
	protected void addHardwareComponents() {
		this.d.addDemandPair(ComponentTypes.WLAN, 1);
	}

	@Override
	public boolean action() throws Exception {
		//start all hardware components
		this.d.switchAllOn();
						
		//search near Explore Robots
		List<RobotCore> nearRobots = this.robot.getICommunicationAdapter().getNearRobots(this.visionRadius);
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
			if (nearRobot.hasActiveHardwareComponent(ComponentTypes.WLAN))// && nearRobot.hasHardwareComponent(Components.LOOKAROUNDSENSOR)) 
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
						merge.run(this.robot, nearRobot, null);
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
					merge.run(this.robot, nearRobot, rdc.newDest);
				}
			}
		}
		return false;
	}

	@Override
	public void initialiseBehaviour() {
		double maxAway = 0;
		//create information list with follower robots
		List<RobotRole> follower = this.mr.getFollowers();
			
		for (RobotRole rr : follower) {
			RobotCore core = rr.getRobotCore();
			if (core.hasHardwareComponent(ComponentTypes.WLAN) && core.hasHardwareComponent(ComponentTypes.LOOKAROUNDSENSOR))
			{
				//add Robot to Map
				information.put(core.getName(), new RobotDestinationCalculation(core.getName()));
				double away = Math.sqrt(core.getAccu().getMaxFieldGoes(core.getMinEnergie()));
				
				if (maxAway < away)
					maxAway = away;
			}
		}			
		
		this.calculationAway = (int) maxAway;		
	}

}

package de.tud.swt.cleaningrobots.behaviours;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.hardware.ComponentTypes;
import de.tud.swt.cleaningrobots.hardware.Wlan;
import de.tud.swt.cleaningrobots.merge.DestinationMerge;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.roles.MasterRole;
import de.tud.swt.cleaningrobots.util.AgentDestinationCalculation;

/**
 * Behavior that calculate new destinations for the wiper and has contact if the followers are loading.
 * 
 * @author Christopher Werner
 *
 */
public class MasterDestinationWipeBehaviour extends Behaviour {

	private MasterRole mr;

	private State STATE_HOOVE;
	private State STATE_WIPE;
	private State WORLDSTATE_WIPED;
	
	private int visionRadius;
	private int calculationAway;
	private DestinationMerge merge;	
	
	private Map<String, AgentDestinationCalculation> information;
	
	public MasterDestinationWipeBehaviour(AgentRole role) {
		super(role);
				
		this.mr = (MasterRole) role;
		this.merge = new DestinationMerge(this.agentCore.getConfiguration());
		this.information = new HashMap<String, AgentDestinationCalculation>();
		
		Wlan wlan = (Wlan) this.demand.getHardwareComponent(ComponentTypes.WLAN);
		this.visionRadius = wlan.getMeasurementRange();		
	}
	
	@Override
	protected void addSupportedStates() {
		//create and add the states
		this.STATE_HOOVE = agentCore.getConfiguration().createState("Hoove");
		this.STATE_WIPE = agentCore.getConfiguration().createState("Wipe");
		this.WORLDSTATE_WIPED = agentCore.getConfiguration().createState("Wiped");		
	}

	@Override
	protected void addHardwareComponents() {
		this.demand.addDemandPair(ComponentTypes.WLAN, 1);
	}

	@Override
	public boolean action() throws Exception {
		//start all hardware components
		this.demand.switchAllOn();
						
		//search near wipe Robots
		List<AgentCore> nearRobots = this.agentCore.getICommunicationAdapter().getNearRobots(this.visionRadius);
		nearRobots.remove(this.agentCore);
				
		for (AgentDestinationCalculation rdc : information.values()) {
			//set all NeedNew to false
			rdc.needNew = false;
			//change new and old destination if not near
			if (rdc.newDest != null)
			{
				boolean change = true;
				//look if in nearRobots
				for (AgentCore nearRobot : nearRobots) 
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
		
		for (AgentCore nearRobot : nearRobots) {
			//look if near robot has active WLAN and is in information and need new destination
			if (nearRobot.hasActiveHardwareComponent(ComponentTypes.WLAN))// && nearRobot.hasHardwareComponent(Components.LOOKAROUNDSENSOR)) 
			{
				//search same Robot
				for (AgentDestinationCalculation rdc : information.values()) {
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
		
		Map<String, AgentDestinationCalculation> result = this.agentCore.getWorld().getNextPassablePositionsByStateWithoutState(information, calculationAway, STATE_HOOVE, STATE_WIPE); 
		
		if (result == null && !this.agentCore.getWorld().containsWorldState(WORLDSTATE_WIPED))
			return false;
		
		if (result == null) {
			//set all destination to null that the robot could shut down
			for (AgentCore nearRobot : nearRobots) {
				for (AgentDestinationCalculation rdc : information.values()) {
					if (rdc.getName().equals(nearRobot.getName()))
					{
						merge.run(this.agentCore, nearRobot, null);
					}
				}
			}
			return false;
		}
		
		information = result; 
		
		//send new Information to nearRobots which needed new Information
		for (AgentCore nearRobot : nearRobots) {
			for (AgentDestinationCalculation rdc : information.values()) {
				if (rdc.getName().equals(nearRobot.getName()) && rdc.needNew)
				{
					merge.run(this.agentCore, nearRobot, rdc.newDest);
				}
			}
		}
		return false;
	}

	@Override
	public void initialiseBehaviour() {
		
		double maxAway = 0;
		//create information list with follower robots
		List<AgentRole> follower = this.mr.getFollowers();
			
		for (AgentRole rr : follower) {
			AgentCore core = rr.getRobotCore();
			if (core.hasHardwareComponent(ComponentTypes.WLAN) && core.hasHardwareComponent(ComponentTypes.WIPER))
			{
				//add Robot to Map
				information.put(core.getName(), new AgentDestinationCalculation(core.getName()));
				double away = Math.sqrt(core.getAccu().getMaxFieldGoes(core.getMinEnergie()));
				
				if (maxAway < away)
					maxAway = away;
			}
		}			
		
		this.calculationAway = (int) maxAway;		
	}
}

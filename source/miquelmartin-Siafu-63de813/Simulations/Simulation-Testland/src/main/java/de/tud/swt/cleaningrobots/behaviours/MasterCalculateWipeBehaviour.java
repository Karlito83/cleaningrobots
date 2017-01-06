package de.tud.swt.cleaningrobots.behaviours;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.hardware.ComponentTypes;
import de.tud.swt.cleaningrobots.merge.PathDestinationMerge;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.roles.MasterRole;
import de.tud.swt.cleaningrobots.util.PathWayMergeInformation;
import de.tud.swt.cleaningrobots.util.AgentDestinationCalculation;

/**
 * Behavior for master which search new destinations for wiper.
 * Always in contact to follower and send the data directly.
 * 
 * @author Christopher Werner
 *
 */
public class MasterCalculateWipeBehaviour extends Behaviour {

	private MasterRole mr;
	
	private State STATE_HOOVE;
	private State STATE_WIPE;	
	private State WORLDSTATE_WIPED;	
	private State WORLDSTATE_HOOVED;
		
	private int calculationAway;
	private PathDestinationMerge mfm;
	
	private Map<String, AgentDestinationCalculation> information;
	
	private boolean relative;
	
	public MasterCalculateWipeBehaviour(AgentRole role, boolean relative) {
		super(role);	
		
		this.mr = (MasterRole) role;
		this.relative = relative;
		this.mfm = new PathDestinationMerge(this.agentCore.getConfiguration());
		this.information = new HashMap<String, AgentDestinationCalculation>();		
	}
	
	@Override
	protected void addSupportedStates() {
		//create and add the states
		this.STATE_HOOVE = agentCore.getConfiguration().createState("Hoove");
		this.STATE_WIPE = agentCore.getConfiguration().createState("Wipe");		
		this.WORLDSTATE_WIPED = agentCore.getConfiguration().createState("Wiped");	
		this.WORLDSTATE_HOOVED = agentCore.getConfiguration().createState("Hooved");

		this.supportedStates.add(this.STATE_HOOVE);
		this.supportedStates.add(this.STATE_WIPE);	
	}

	@Override
	protected void addHardwareComponents() {
		this.demand.addDemandPair(ComponentTypes.WLAN, 1);
	}

	@Override
	public boolean action() throws Exception {
		//start all hardware components
		this.demand.switchAllOn();
									
		//search all wipe robots
		List<AgentCore> allRobots = this.agentCore.getICommunicationAdapter().getAllRobots();
		allRobots.remove(this.agentCore);
										
		for (AgentDestinationCalculation rdc : information.values()) {
			//set all NeedNew to false
			rdc.needNew = false;
			//change new and old destination
			if (rdc.newDest != null)
			{
				//set newDest to null and set oldDest
				rdc.oldDest = rdc.newDest;
				rdc.newDest = null;
			}
		}
								
		//search robots which are loading and not have a newDest and set Variable			
		boolean newOneFind = false;
								
		for (AgentCore oneRobot : allRobots) {
			//run Values and search the same robot
			for (AgentDestinationCalculation rdc : information.values()) {
				if (oneRobot.getName().equals(rdc.getName())) 
				{
					if (oneRobot.hasNewInformation() && oneRobot.getDestinationContainer().isAtLoadDestination())
					{
						newOneFind = true;
						rdc.needNew = true;
						oneRobot.setNewInformation(false);
					}
				}
			}			
		}
						
		//if new one find then calculate new destination and set it
		if (newOneFind) {
			Map<String, AgentDestinationCalculation> result = this.agentCore.getWorld().getNextPassablePositionsByStateWithoutState(information, calculationAway, STATE_HOOVE, STATE_WIPE);
							
			if (result != null) {			
				information = result; 
								
				//send new information
				for (AgentCore oneRobot : allRobots) {
					for (AgentDestinationCalculation rdc : information.values()) {
						if (rdc.getName().equals(oneRobot.getName()) && rdc.needNew)
						{
							PathWayMergeInformation path = new PathWayMergeInformation(rdc.newDest, agentCore.getWorld().getPath(rdc.newDest));
							mfm.run(this.agentCore, oneRobot, path);
							rdc.actualPosition = rdc.newDest;
						}
					}
				}
			} else {
				for (AgentDestinationCalculation rdc : information.values()) {
					if (rdc.needNew)
						rdc.finish = true;
				}
			}
		}
						
		for (AgentCore oneRobot : allRobots) {
			//search the same robot
			for (AgentDestinationCalculation rdc : information.values()) {
				if (oneRobot.getName().equals(rdc.getName())) 
				{
					if (oneRobot.hasNewInformation())
					{						
						Position nextWipePosition; 
						//proof if you need relative or not relative position
						if (relative)
							nextWipePosition = this.agentCore.getWorld().getNextPassableRelativePositionByStateWithoutState(rdc.actualPosition, rdc.oldDest, STATE_HOOVE, STATE_WIPE); 
						else
							nextWipePosition = this.agentCore.getWorld().getNextPassablePositionByStateWithoutState(rdc.actualPosition, STATE_HOOVE, STATE_WIPE);
									
						if(nextWipePosition != null){								
							//if the robot has a Accu proof the destination
							if (oneRobot.getAccu() != null)
							{
								//distance robot to destination
								int sizeOne = agentCore.getWorld().getPathFromTo(rdc.actualPosition, nextWipePosition).size();
								//distance destination to load station
								int sizeThree = agentCore.getWorld().getPathFromTo(nextWipePosition, agentCore.getPosition()).size();
								int size = sizeOne + sizeThree;
								size +=2;
								//if Accu is to low
								if (size * oneRobot.getActualEnergie() > oneRobot.getAccu().getRestKWh())
								{
									//Robot must load before drive to the destination
									if (rdc.actualPosition.equals(agentCore.getPosition()))
									{
										System.out.println("Robot erreicht keine Unknownposition mehr obwohl diese noch existiert!");
										rdc.finish = true;
									} else {
										PathWayMergeInformation path = new PathWayMergeInformation(agentCore.getPosition(), agentCore.getWorld().getPathFromTo(rdc.actualPosition, agentCore.getPosition()));
										mfm.run(this.agentCore, oneRobot, path);
										rdc.actualPosition = agentCore.getPosition();
									}
								} else {
									//robot has enough Accu he can drive to destination
									PathWayMergeInformation path = new PathWayMergeInformation(nextWipePosition, agentCore.getWorld().getPathFromTo(rdc.actualPosition, nextWipePosition));
									mfm.run(this.agentCore, oneRobot, path);
									rdc.actualPosition = nextWipePosition;
								}
							} else {
								PathWayMergeInformation path = new PathWayMergeInformation(nextWipePosition, agentCore.getWorld().getPathFromTo(rdc.actualPosition, nextWipePosition));
								mfm.run(this.agentCore, oneRobot, path);
								rdc.actualPosition = nextWipePosition;
							}
						}
						else 
						{
							//proof if the world is complete hooved
							if (this.agentCore.getWorld().containsWorldState(WORLDSTATE_HOOVED)) {
								this.agentCore.getWorld().addWorldState(WORLDSTATE_WIPED);
								if(!rdc.actualPosition.equals(agentCore.getPosition()))
								{
									//arrived at load station
									PathWayMergeInformation path = new PathWayMergeInformation(agentCore.getPosition(), agentCore.getWorld().getPathFromTo(rdc.actualPosition, agentCore.getPosition()));
									mfm.run(this.agentCore, oneRobot, path);
									rdc.actualPosition = agentCore.getPosition();
								} else {
									rdc.finish = true;
								}
							}
						}						
						//calculate a new position for the robot
						oneRobot.setNewInformation(false);
					}
				}
			}			
		}				
		return false;
	}
	
	public boolean isFinishWiping () {
		for (AgentDestinationCalculation rdc : information.values()) {
			if (!rdc.finish)
				return false;
		}
		if (this.agentCore.getWorld().containsWorldState(WORLDSTATE_WIPED))
		{
			for (AgentCore core : this.agentCore.getICommunicationAdapter().getAllRobots())
				core.getWorld().addWorldState(WORLDSTATE_WIPED);
			return true;
		} else {
			System.out.println("Roboter brauchen größeren Accu können Welt nicht mehr erkunden!");
			/*try {
				Thread.sleep(40000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
		return false;
	}

	@Override
	public void initialiseBehaviour() {
		
		double maxAway = 0;
		//search near Wipe Robots
		List<AgentRole> follower = this.mr.getFollowers();
					
		for (AgentRole rr : follower) {
			AgentCore core = rr.getRobotCore();
			if (core.hasHardwareComponent(ComponentTypes.WLAN) && core.hasHardwareComponent(ComponentTypes.WIPER))
			{
				//add Robot to Map
				AgentDestinationCalculation rdc = new AgentDestinationCalculation(core.getName());
				rdc.actualPosition = core.getPosition();
				information.put(core.getName(), rdc);
						
				double away = Math.sqrt(core.getAccu().getMaxFieldGoes(core.getMinEnergie()));
						
				if (maxAway < away)
					maxAway = away;
			}
		}
						
		calculationAway = (int) maxAway;		
	}

}

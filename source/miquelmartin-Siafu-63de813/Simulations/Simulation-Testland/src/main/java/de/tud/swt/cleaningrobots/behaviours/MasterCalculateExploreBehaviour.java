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
 * Behavior for master which search new destinations for explorer.
 * Always in contact to follower and send the data directly.
 * 
 * @author Christopher Werner
 *
 */
public class MasterCalculateExploreBehaviour extends Behaviour{

	private MasterRole mr;
	
	private State STATE_BLOCKED;
	private State STATE_FREE;	
	private State WORLDSTATE_DISCOVERED;	
	
	private int calculationAway;
	private PathDestinationMerge mfm;
	
	private Map<String, AgentDestinationCalculation> information;
	
	private boolean relative;
	
	public MasterCalculateExploreBehaviour(AgentRole role, boolean relative) {
		super(role);
				
		this.mr = (MasterRole) role;
		this.relative = relative;
		this.mfm = new PathDestinationMerge(this.agentCore.getConfiguration());
		this.information = new HashMap<String, AgentDestinationCalculation>();		
	}
	
	@Override
	protected void addSupportedStates() {
		//create and add the states
		this.STATE_BLOCKED = agentCore.getConfiguration().createState("Blocked");
		this.STATE_FREE = agentCore.getConfiguration().createState("Free");
		this.WORLDSTATE_DISCOVERED = agentCore.getConfiguration().createState("Discovered");

		supportedStates.add(STATE_BLOCKED);
		supportedStates.add(STATE_FREE);		
	}

	@Override
	protected void addHardwareComponents() {
		this.demand.addDemandPair(ComponentTypes.WLAN, 1);
	}

	@Override
	public boolean action() {
		
		//start all hardware components
		this.demand.switchAllOn();
				
		//search all explore robots
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
			Map<String, AgentDestinationCalculation> result = this.agentCore.getWorld().getNextUnknownFields(information, calculationAway);
			
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
						Position nextUnknownPosition; 
						
						//look if you need relative algorithm or not
						if (relative)	
							nextUnknownPosition = this.agentCore.getWorld().getNextUnknownRelativeFieldPosition(rdc.actualPosition, rdc.oldDest); 
						else
							nextUnknownPosition = this.agentCore.getWorld().getNextUnknownFieldPosition(rdc.actualPosition);
						
						if(nextUnknownPosition != null){								
							//if the robot has a Accu proof the destination
							if (oneRobot.getAccu() != null)
							{
								//distance robot to destination
								int sizeOne = agentCore.getWorld().getPathFromTo(rdc.actualPosition, nextUnknownPosition).size();
								//distance destination to load station
								int sizeThree = agentCore.getWorld().getPathFromTo(nextUnknownPosition, agentCore.getPosition()).size();
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
									PathWayMergeInformation path = new PathWayMergeInformation(nextUnknownPosition, agentCore.getWorld().getPathFromTo(rdc.actualPosition, nextUnknownPosition));
									mfm.run(this.agentCore, oneRobot, path);
									rdc.actualPosition = nextUnknownPosition;
								}
							} else {
								PathWayMergeInformation path = new PathWayMergeInformation(nextUnknownPosition, agentCore.getWorld().getPathFromTo(rdc.actualPosition, nextUnknownPosition));
								mfm.run(this.agentCore, oneRobot, path);
								rdc.actualPosition = nextUnknownPosition;
							}
						}
						else 
						{
							this.agentCore.getWorld().addWorldState(WORLDSTATE_DISCOVERED);
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
						//calculate a new position for the robot
						oneRobot.setNewInformation(false);
					}
				}
			}			
		}				
		return false;
	}
	
	public boolean isFinishDisscovering () {
		for (AgentDestinationCalculation rdc : information.values()) {
			if (!rdc.finish)
				return false;
		}
		if (this.agentCore.getWorld().containsWorldState(WORLDSTATE_DISCOVERED))
		{
			for (AgentCore core : this.agentCore.getICommunicationAdapter().getAllRobots())
				core.getWorld().addWorldState(WORLDSTATE_DISCOVERED);
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
		//search near Explore Robots
		List<AgentRole> follower = this.mr.getFollowers();
			
		for (AgentRole rr : follower) {
			AgentCore core = rr.getRobotCore();
			if (core.hasHardwareComponent(ComponentTypes.WLAN) && core.hasHardwareComponent(ComponentTypes.LOOKAROUNDSENSOR))
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
		
		this.calculationAway = (int) maxAway;		
	}
}

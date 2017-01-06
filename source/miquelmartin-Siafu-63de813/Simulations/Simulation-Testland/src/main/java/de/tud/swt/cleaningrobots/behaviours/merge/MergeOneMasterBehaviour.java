package de.tud.swt.cleaningrobots.behaviours.merge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.AgentKnowledge;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.hardware.ComponentTypes;
import de.tud.swt.cleaningrobots.hardware.Wlan;
import de.tud.swt.cleaningrobots.merge.Merge;
import de.tud.swt.cleaningrobots.merge.NewInformationFollowerMerge;
import de.tud.swt.cleaningrobots.merge.WorldEcoreModelMerge;
import de.tud.swt.cleaningrobots.merge.WorldMerge;
import de.tud.swt.cleaningrobots.roles.MasterRole;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;

/**
 * Behavior which realize the data exchange and integration
 * between a master and his followers.
 * Without the EMF model.
 * 
 * @author Christopher Werner
 *
 */
public class MergeOneMasterBehaviour extends Behaviour {
	
	private MasterRole mr;
	private Merge ma;
	private NewInformationFollowerMerge informationMerge;
	private int visionRadius;		
	private List<AgentRole> lastChange;
	
	public MergeOneMasterBehaviour (AgentRole role, boolean useModel) {
		super(role);
		
		this.mr = (MasterRole) role;
		this.lastChange = new ArrayList<AgentRole>();
		if (useModel)
		{
			this.ma = new WorldEcoreModelMerge(this.agentCore.getConfiguration());
		}
		else
		{
			this.ma = new WorldMerge(this.agentCore.getConfiguration());
		}
		this.informationMerge = new NewInformationFollowerMerge(this.agentCore.getConfiguration());
		
		Wlan wlan = (Wlan) this.demand.getHardwareComponent(ComponentTypes.WLAN);
		this.visionRadius = wlan.getMeasurementRange();			
	}
	
	@Override
	protected void addSupportedStates() {
		//no states needed...		
	}

	@Override
	protected void addHardwareComponents() {
		this.demand.addDemandPair(ComponentTypes.WLAN, 1);
	}

	@Override
	public boolean action() {
		
		//start all hardware components
		this.demand.switchAllOn();
		
		List<AgentCore> nearRobots = this.agentCore.getICommunicationAdapter().getNearRobots(this.visionRadius);
		nearRobots.remove(this.agentCore);
		
		//if no nearRobots end this behavior
		if (nearRobots.isEmpty())
			return false;
						
		//create the information maps for new robots with new information
		Map<AgentRole, ImportExportConfiguration> nearsNewInformation = new HashMap<AgentRole, ImportExportConfiguration>();
		Map<AgentRole, ImportExportConfiguration> nearsNoNewInformation = new HashMap<AgentRole, ImportExportConfiguration>();
		for (AgentCore nearRobot : nearRobots) {
			//could only communicate with near robots if they have active WLAN
			if (nearRobot.hasActiveHardwareComponent(ComponentTypes.WLAN)) {
				//near robot must be a follower
				List<AgentRole> frr = mr.getFollowers();
				for (AgentRole rr : frr)
				{
					if (rr.getRobotCore().equals(nearRobot))
					{
						if (rr.hasNewInformation())
						{
							//reset new information to false
							this.informationMerge.run(this.agentCore, nearRobot, rr);
							
							//Robot say that he has new Information
							//make the configuration file for export and import
							ImportExportConfiguration config = new ImportExportConfiguration();
							config.world = true;
							config.knownstates = true;
							config.knowledge = true;																		
							//search timestamp of last meeting
							for (AgentKnowledge rk : agentCore.getKnowledge()) {
								if (rk.getName().equals(nearRobot.getName())) {
									config.iteration = rk.getLastArrange();
								}											
							}
								
							//export and Import the Models
							ma.run(nearRobot, this.agentCore, config);
							
							//change the config for later export and import
							for (AgentKnowledge rk : agentCore.getKnowledge()) {
								if (rk.getName().equals(nearRobot.getName())) {
									config.knownStates = rk.getKnownStates();
								}											
							}
							nearsNewInformation.put(rr, config);
						} else {
							ImportExportConfiguration config = new ImportExportConfiguration();
							config.world = true;
							config.knownstates = true;
							config.knowledge = true;
							for (AgentKnowledge rk : agentCore.getKnowledge()) {
								if (rk.getName().equals(nearRobot.getName())) {
									config.iteration = rk.getLastArrange();
									config.knownStates = rk.getKnownStates();
								}											
							}
							nearsNoNewInformation.put(rr, config);								
						}
						break;
					}
				}				
			}
		}
		
		if (nearsNewInformation.isEmpty() && nearsNoNewInformation.isEmpty())
			return false;
		
		boolean newInfoForFollower = false;
		
		//look if the same robot is also a follower and has new information
		//if true clean last change to give all new information
		for (AgentRole rr : mr.getFollowers()) {
			if (rr.getRobotCore().equals(mr.getRobotCore()) && rr.hasNewInformation()) {
				rr.setNewInformation(false);
				newInfoForFollower = true;
				lastChange.clear();
			}
		}
		
		//add the model to all near robots
		if (!nearsNewInformation.isEmpty())
		{
			mr.setNewInformation(true);
			//System.out.println("LastChange: " + lastChange + " NewInfo: " + nearsNewInformation + " NoNewInfo: " + nearsNoNewInformation);
			if (nearsNewInformation.size() == 1)
			{
				//only import if not in last change and he is not the only one
				for (AgentRole rr : nearsNewInformation.keySet()) {
					//last change contains the follower
					if(!lastChange.contains(rr))
					{
						ma.run(this.agentCore, rr.getRobotCore(), nearsNewInformation.get(rr));
					}
				}
			} else {
				for (AgentRole rr : nearsNewInformation.keySet()) {
					//import the model to all near robots
					ma.run(this.agentCore, rr.getRobotCore(), nearsNewInformation.get(rr));
				}
			}
			for (AgentRole rr : nearsNoNewInformation.keySet()) {
				//import the model to all near robots
				ma.run(this.agentCore, rr.getRobotCore(), nearsNoNewInformation.get(rr));
			}	
			lastChange.clear();
			lastChange.addAll(nearsNewInformation.keySet());
			lastChange.addAll(nearsNoNewInformation.keySet());
		} else {
			//nearsNoNew could not be null
			if (newInfoForFollower)
			{
				for (AgentRole rr : nearsNoNewInformation.keySet()) {
					//import the model to all near robots
					ma.run(this.agentCore, rr.getRobotCore(), nearsNoNewInformation.get(rr));
					lastChange.clear();
					lastChange.addAll(nearsNoNewInformation.keySet());
				}
			}
			//TODO: case when the robots comes without new information
		}		
		return false;
	}

	@Override
	public void initialiseBehaviour() {
		//do not need to initialize something			
	}	
}

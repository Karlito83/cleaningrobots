package de.tud.swt.cleaningrobots.behaviours.merge;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.tud.swt.cleaningrobots.RobotKnowledge;
import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.hardware.ComponentTypes;
import de.tud.swt.cleaningrobots.hardware.Wlan;
import de.tud.swt.cleaningrobots.merge.Merge;
import de.tud.swt.cleaningrobots.merge.NewInformationFollowerMerge;
import de.tud.swt.cleaningrobots.merge.WorldEcoreModelMerge;
import de.tud.swt.cleaningrobots.merge.WorldMerge;
import de.tud.swt.cleaningrobots.roles.MasterRole;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;

/**
 * Old Behavior.
 * Behavior which realize the data exchange and integration
 * between all master and his followers.
 * With the EMF model.
 * 
 * @author Christopher Werner
 *
 */
public class MergeAllMasterBehaviour extends Behaviour {
	
	private Merge ma;
	private NewInformationFollowerMerge informationMerge;
	private int visionRadius;		
	private List<RobotRole> lastChange;
	
	public MergeAllMasterBehaviour(RobotRole role, boolean useModel) {
		super(role);
		
		this.lastChange = new ArrayList<RobotRole>();
		if (useModel)
		{
			this.ma = new WorldEcoreModelMerge(this.agentCore.getConfiguration());
		}
		else
		{
			this.ma = new WorldMerge(this.agentCore.getConfiguration());
		}
		this.ma = new WorldEcoreModelMerge(this.agentCore.getConfiguration());
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
				
		List<RobotCore> nearRobots = this.agentCore.getICommunicationAdapter().getNearRobots(this.visionRadius);
		nearRobots.remove(this.agentCore);
		
		//if no nearRobots end this behavior
		if (nearRobots.isEmpty())
			return false;
		
		Map<RobotRole, ImportExportConfiguration> nearsNewInformation = new HashMap<RobotRole, ImportExportConfiguration>();
		Map<RobotRole, ImportExportConfiguration> nearsNoNewInformation = new HashMap<RobotRole, ImportExportConfiguration>();
		for (RobotCore nearRobot : nearRobots) {
			//could only communicate with near robots if they have active WLAN
			if (nearRobot.hasActiveHardwareComponent(ComponentTypes.WLAN)) {
				//near robot must be a follower
				List<RobotRole> lrr = agentCore.getRoles();
				for (RobotRole rr : lrr)
				{
					if (rr instanceof MasterRole)
					{
						List<RobotRole> frr = ((MasterRole)rr).getFollowers();
						for (RobotRole fr : frr)
						{
							if (fr.getRobotCore().equals(nearRobot))
							{
								if (fr.hasNewInformation())
								{
									//reset new information to false
									this.informationMerge.run(this.agentCore, nearRobot, fr);
									
									//Robot say that he has new Information
									//make the configuration file for export and import
									ImportExportConfiguration config = new ImportExportConfiguration();
									config.world = true;
									config.knownstates = true;
									config.knowledge = true;																		
									//search timestamp of last meeting
									for (RobotKnowledge rk : agentCore.getKnowledge()) {
										if (rk.getName().equals(nearRobot.getName())) {
											config.iteration = rk.getLastArrange();
										}											
									}
									
									//export and Import the Models
									ma.run(nearRobot, this.agentCore, config);
									
									//change the configuration for later export and import
									for (RobotKnowledge rk : agentCore.getKnowledge()) {
										if (rk.getName().equals(nearRobot.getName())) {
											System.out.println(rk.getName() + " RK KnownStates: " + rk.getKnownStates());
											config.knownStates = rk.getKnownStates();
										}											
									}
									nearsNewInformation.put(fr, config);
								} else {
									
									ImportExportConfiguration config = new ImportExportConfiguration();
									config.world = true;
									config.knownstates = true;
									config.knowledge = true;
									for (RobotKnowledge rk : agentCore.getKnowledge()) {
										if (rk.getName().equals(nearRobot.getName())) {
											config.iteration = rk.getLastArrange();
											config.knownStates = rk.getKnownStates();
										}											
									}
									nearsNoNewInformation.put(fr, config);								
								}
								break;
							}
						}						
					}
				}				
			}
		}
		
		//add the model to the near robots
		if (nearsNewInformation.size() >= 1)
		{
			//System.out.println("LastChange: " + lastChange + " NewInfo: " + nearsNewInformation + " NoNewInfo: " + nearsNoNewInformation);
			if (nearsNewInformation.size() == 1)
			{
				//only import if not in last change list
				for (RobotRole fr : nearsNewInformation.keySet()) {
					if(!lastChange.contains(fr))
					{
						ma.run(this.agentCore, fr.getRobotCore(), nearsNewInformation.get(fr));
					}
				}
			} else {
				for (RobotRole fr : nearsNewInformation.keySet()) {
					//import all near robots the new model
					ma.run(this.agentCore, fr.getRobotCore(), nearsNewInformation.get(fr));
				}
			}
			for (RobotRole fr : nearsNoNewInformation.keySet()) {
				//import all near robots the new model
				ma.run(this.agentCore, fr.getRobotCore(), nearsNoNewInformation.get(fr));
			}	
			lastChange.clear();
			lastChange.addAll(nearsNewInformation.keySet());
			lastChange.addAll(nearsNoNewInformation.keySet());
			System.out.println("LastChange: " + lastChange);
		}
		
		return false;
	}

	@Override
	public void initialiseBehaviour() {
		//do not need to initialize something			
	}	
}

package de.tud.swt.cleaningrobots.behaviours;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.MasterRole;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotKnowledge;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.Wlan;
import de.tud.swt.cleaningrobots.merge.MergeAllWithoutModel;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;

/**
 * Behavior which realize the data exchange and integration
 * between a master and his followers.
 * Without the EMF model.
 * 
 * @author Christopher Werner
 *
 */
public class MergeMasterWithoutModel extends Behaviour {
	
	private MasterRole mr;
	private MergeAllWithoutModel ma;
	private int visionRadius;	
	
	private List<RobotRole> lastChange;
	
	public MergeMasterWithoutModel (RobotCore robot, MasterRole mr) {
		super(robot);
		
		this.mr = mr;
		this.lastChange = new ArrayList<RobotRole>();
		this.ma = new MergeAllWithoutModel(this.robot.configuration);
		
		Wlan wlan = (Wlan) this.d.getHardwareComponent(Components.WLAN);
		this.visionRadius = wlan.getVisionRadius();			
	}
	
	@Override
	protected void addSupportedStates() {
		//no states needed...		
	}

	@Override
	protected void addHardwareComponents() {
		this.d.addDemandPair(Components.WLAN, 1);
	}

	@Override
	public boolean action() {
		
		//start all hardware components
		this.d.switchAllOn();
		
		List<RobotCore> nearRobots = this.robot.getICommunicationAdapter().getNearRobots(this.visionRadius);
		nearRobots.remove(this.robot);
		
		//if no nearRobots end this behavior
		if (nearRobots.isEmpty())
			return false;
						
		//create the information maps for new robots with new information
		Map<RobotRole, ImportExportConfiguration> nearsNewInformation = new HashMap<RobotRole, ImportExportConfiguration>();
		Map<RobotRole, ImportExportConfiguration> nearsNoNewInformation = new HashMap<RobotRole, ImportExportConfiguration>();
		for (RobotCore nearRobot : nearRobots) {
			//could only communicate with near robots if they have active WLAN
			if (nearRobot.hasActiveHardwareComponent(Components.WLAN)) {
				//near robot must be a follower
				List<RobotRole> frr = mr.getFollowers();
				for (RobotRole rr : frr)
				{
					if (rr.getRobotCore().equals(nearRobot))
					{
						if (rr.hasNewInformation())
						{
							//set new information to false
							rr.setNewInformation(false);
							
							ma.newInformationMeasure(rr.getRobotCore().getName());
							//Robot say that he has new Information
							//make the configuration file for export and import
							ImportExportConfiguration config = new ImportExportConfiguration();
							config.world = true;
							config.knownstates = true;
							config.knowledge = true;																		
							//search timestamp of last meeting
							for (RobotKnowledge rk : robot.getKnowledge()) {
								if (rk.getName().equals(nearRobot.getName())) {
									config.iteration = rk.getLastArrange();
								}											
							}
								
							//export and Import the Models
							ma.importAllWithoutModel(nearRobot, this.robot, config);
							
							//change the config for later export and import
							for (RobotKnowledge rk : robot.getKnowledge()) {
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
							for (RobotKnowledge rk : robot.getKnowledge()) {
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
		for (RobotRole rr : mr.getFollowers()) {
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
				for (RobotRole rr : nearsNewInformation.keySet()) {
					//last change contains the follower
					if(!lastChange.contains(rr))
					{
						ma.importAllWithoutModel(this.robot, rr.getRobotCore(), nearsNewInformation.get(rr));
					}
				}
			} else {
				for (RobotRole rr : nearsNewInformation.keySet()) {
					//import the model to all near robots
					ma.importAllWithoutModel(this.robot, rr.getRobotCore(), nearsNewInformation.get(rr));
				}
			}
			for (RobotRole rr : nearsNoNewInformation.keySet()) {
				//import the model to all near robots
				ma.importAllWithoutModel(this.robot, rr.getRobotCore(), nearsNoNewInformation.get(rr));
			}	
			lastChange.clear();
			lastChange.addAll(nearsNewInformation.keySet());
			lastChange.addAll(nearsNoNewInformation.keySet());
		} else {
			//nearsNoNew could not be null
			if (newInfoForFollower)
			{
				for (RobotRole rr : nearsNoNewInformation.keySet()) {
					//import the model to all near robots
					ma.importAllWithoutModel(this.robot, rr.getRobotCore(), nearsNoNewInformation.get(rr));
					lastChange.addAll(nearsNoNewInformation.keySet());
				}
			}
			//TODO: case when the robots comes without new information
		}		
		return false;
	}	
}

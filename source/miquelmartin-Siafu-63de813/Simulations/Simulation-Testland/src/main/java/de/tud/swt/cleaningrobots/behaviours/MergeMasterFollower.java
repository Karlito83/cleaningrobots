package de.tud.swt.cleaningrobots.behaviours;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.eclipse.emf.ecore.EObject;

import de.tud.swt.cleaningrobots.RobotKnowledge;
import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.hardware.ComponentTypes;
import de.tud.swt.cleaningrobots.hardware.Wlan;
import de.tud.swt.cleaningrobots.merge.MergeAll;
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
public class MergeMasterFollower extends Behaviour {
	
	private MergeAll ma;
	private int visionRadius;		
	private List<RobotRole> lastChange;
	
	public MergeMasterFollower(RobotCore robot) {
		super(robot);
		
		this.lastChange = new ArrayList<RobotRole>();
		this.ma = new MergeAll(this.robot.configuration);
		
		Wlan wlan = (Wlan) this.d.getHardwareComponent(ComponentTypes.WLAN);
		this.visionRadius = wlan.getVisionRadius();			
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
	public boolean action() {
		
		//start all hardware components
		this.d.switchAllOn();
				
		List<RobotCore> nearRobots = this.robot.getICommunicationAdapter().getNearRobots(this.visionRadius);
		nearRobots.remove(this.robot);
		
		//if no nearRobots end this behavior
		if (nearRobots.isEmpty())
			return false;
		
		Map<RobotRole, ImportExportConfiguration> nearsNewInformation = new HashMap<RobotRole, ImportExportConfiguration>();
		Map<RobotRole, ImportExportConfiguration> nearsNoNewInformation = new HashMap<RobotRole, ImportExportConfiguration>();
		for (RobotCore nearRobot : nearRobots) {
			//could only communicate with near robots if they have active WLAN
			if (nearRobot.hasActiveHardwareComponent(ComponentTypes.WLAN)) {
				//near robot must be a follower
				List<RobotRole> lrr = robot.getRoles();
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
									//set new information to false
									fr.setNewInformation(false);
									
									ma.newInformationMeasure(fr.getRobotCore().getName());
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
									EObject model = nearRobot.exportModel(config);
									ma.importAllModel(model, this.robot, config);
									
									//change the configuration for later export and import
									for (RobotKnowledge rk : robot.getKnowledge()) {
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
									for (RobotKnowledge rk : robot.getKnowledge()) {
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
						EObject model = this.robot.exportModel(nearsNewInformation.get(fr));
						ma.importAllModel(model, fr.getRobotCore(), nearsNewInformation.get(fr));
					}
				}
			} else {
				for (RobotRole fr : nearsNewInformation.keySet()) {
					//import all near robots the new model
					EObject model = this.robot.exportModel(nearsNewInformation.get(fr));
					ma.importAllModel(model, fr.getRobotCore(), nearsNewInformation.get(fr));
				}
			}
			for (RobotRole fr : nearsNoNewInformation.keySet()) {
				//import all near robots the new model
				EObject model = this.robot.exportModel(nearsNoNewInformation.get(fr));
				ma.importAllModel(model, fr.getRobotCore(), nearsNoNewInformation.get(fr));
			}	
			lastChange.clear();
			lastChange.addAll(nearsNewInformation.keySet());
			lastChange.addAll(nearsNoNewInformation.keySet());
			System.out.println("LastChange: " + lastChange);
		}
		
		return false;
	}	
}

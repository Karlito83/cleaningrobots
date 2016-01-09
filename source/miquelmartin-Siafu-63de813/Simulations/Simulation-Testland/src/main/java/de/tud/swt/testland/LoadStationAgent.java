package de.tud.swt.testland;

import de.nec.nle.siafu.model.Position;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.MasterRole;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.goals.nonoptional.MasterExploreMasterGoal;
import de.tud.swt.cleaningrobots.goals.nonoptional.MasterHooveMasterGoal;
import de.tud.swt.cleaningrobots.goals.nonoptional.MasterWipeMasterGoal;
import de.tud.swt.cleaningrobots.goals.optional.CalculateExploreRobotPositionGoal;
import de.tud.swt.cleaningrobots.goals.optional.CalculateHooveRobotPositionGoal;
import de.tud.swt.cleaningrobots.goals.optional.CalculateWipeRobotPositionGoal;
import de.tud.swt.cleaningrobots.goals.optional.ExploreDumpGoal;
import de.tud.swt.cleaningrobots.goals.optional.LoadIfRobotWantGoal;
import de.tud.swt.cleaningrobots.goals.optional.MergeMasterGoal;
import de.tud.swt.cleaningrobots.hardware.LoadStation;
import de.tud.swt.cleaningrobots.hardware.Rechner;
import de.tud.swt.cleaningrobots.hardware.Wlan;

/**
 * Used with user interface in Siafu.
 * Create a load station with its hardware components which can be master and can add specific goals to him.
 * 
 * @author Christopher Werner
 *
 */
public class LoadStationAgent extends RobotAgent {

	public LoadStationAgent(Position start, String image, World world, Configuration configuration) {
		super(start, image, world);
		
		siafuWorld = world;
		cleaningRobot = new RobotCore(this, null, configuration);
		
		cleaningRobot.addHardwareComponent(new Rechner());
		cleaningRobot.addHardwareComponent(new LoadStation());
		cleaningRobot.addHardwareComponent(new Wlan());
	}
	
	/**
	 * Add the goal to calculate new discover destinations.
	 * @param mr the specific master role
	 */
	public void addCalculateExploreRobotPositionGoal (MasterRole mr) {
		CalculateExploreRobotPositionGoal crpg = new CalculateExploreRobotPositionGoal(cleaningRobot, mr);
		if (crpg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(crpg);
		}
	}
	
	/**
	 * Add the goal to calculate new hoove destinations.
	 * @param mr the specific master role
	 */
	public void addCalculateHooveRobotPositionGoal (MasterRole mr) {
		CalculateHooveRobotPositionGoal crpg = new CalculateHooveRobotPositionGoal(cleaningRobot, mr);
		if (crpg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(crpg);
		}
	}
	
	/**
	 * Add the goal to calculate new wipe destinations.
	 * @param mr the specific master role
	 */
	public void addCalculateWipeRobotPositionGoal (MasterRole mr) {
		CalculateWipeRobotPositionGoal crpg = new CalculateWipeRobotPositionGoal(cleaningRobot, mr);
		if (crpg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(crpg);
		}
	}
	
	/**
	 * Add the goal to load robots.
	 */
	public void addLoadIfRobotWantGoal () {		
		LoadIfRobotWantGoal lirwg = new LoadIfRobotWantGoal(cleaningRobot);		
		if (lirwg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(lirwg);
		}
	}
	
	/**
	 * Add the goal for an xml and png output of the current map.
	 */
	public void addExploreDumpGoal () {
		cleaningRobot.addGoal(new ExploreDumpGoal(cleaningRobot));
	}
	
	/**
	 * Add the goal do merge data between master and follower.
	 * @param mr the specific master role
	 */
	public void addMasterMerge (MasterRole mr) {
		MergeMasterGoal mmg = new MergeMasterGoal(cleaningRobot, mr);
		if (mmg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(mmg);
		}
	}
	
	/**
	 * Add goal for the complete management from the master for explore agents. 
	 * @param mr
	 * @param relative
	 */
	public void addMasterExploreGoals (MasterRole mr, boolean relative) {
		MasterExploreMasterGoal mmg = new MasterExploreMasterGoal(cleaningRobot, mr, relative);		
		if (mmg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(mmg);
		}
	}
	
	/**
	 * Add goal for the complete management from the master for hoove agents. 
	 * @param mr
	 * @param relative
	 */
	public void addMasterHooveGoal (MasterRole mr, boolean relative) {
		MasterHooveMasterGoal mmg = new MasterHooveMasterGoal(cleaningRobot, mr, relative);		
		if (mmg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(mmg);
		}
	}
	
	/**
	 * Add goal for the complete management from the master for wipe agents. 
	 * @param mr
	 * @param relative
	 */
	public void addMasterWipeGoal (MasterRole mr, boolean relative) {
		MasterWipeMasterGoal mmg = new MasterWipeMasterGoal(cleaningRobot, mr, relative);		
		if (mmg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(mmg);
		}
	}

}

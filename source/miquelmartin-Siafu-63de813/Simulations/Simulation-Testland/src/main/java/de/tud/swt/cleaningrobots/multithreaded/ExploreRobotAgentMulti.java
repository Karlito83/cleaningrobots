package de.tud.swt.cleaningrobots.multithreaded;

import de.nec.nle.siafu.model.MultiWorld;
import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.goals.MasterGoal;
import de.tud.swt.cleaningrobots.goals.nonoptional.ExploreLoadGoal;
import de.tud.swt.cleaningrobots.goals.nonoptional.MasterExploreRobotGoal;
import de.tud.swt.cleaningrobots.goals.nonoptional.WithoutMasterExploreGoal;
import de.tud.swt.cleaningrobots.goals.optional.ExploreDumpGoal;
import de.tud.swt.cleaningrobots.goals.optional.WlanLoadIfRobotWantMergeGoal;
import de.tud.swt.cleaningrobots.goals.optional.WlanOnGoal;
import de.tud.swt.cleaningrobots.hardware.Accu;
import de.tud.swt.cleaningrobots.hardware.LookAroundSensor;
import de.tud.swt.cleaningrobots.hardware.Motor;
import de.tud.swt.cleaningrobots.hardware.Rechner;
import de.tud.swt.cleaningrobots.hardware.Wlan;
import de.tud.swt.cleaningrobots.model.Position;

/**
 * Used without user interface in Siafu.
 * Create a explore agent with its hardware components and can add specific goals to him.
 * 
 * @author Christopher Werner
 *
 */
public class ExploreRobotAgentMulti extends RobotAgentMulti {

	public ExploreRobotAgentMulti(Position start, MultiWorld world, Configuration configuration) {
		super(start, world);

		cleaningRobot = new RobotCore(this, new Accu(48.0), configuration); //new Accu(0, 1000)
		
		cleaningRobot.addHardwareComponent(new Rechner());
		cleaningRobot.addHardwareComponent(new Wlan());
		cleaningRobot.addHardwareComponent(new Motor());
		cleaningRobot.addHardwareComponent(new LookAroundSensor());				
	}
	
	/**
	 * Add the goals for search a new relative destination.
	 */
	public void addRelativeStandardGoals () {		
		ExploreLoadGoal elg = new ExploreLoadGoal(cleaningRobot, true);	
		WlanLoadIfRobotWantMergeGoal wlirwmg = new WlanLoadIfRobotWantMergeGoal(cleaningRobot);
		
		MasterGoal mg = new MasterGoal(cleaningRobot);
		mg.subGoals.add(elg);
		mg.subGoals.add(wlirwmg);
		
		if (mg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(mg);
		}
	} 
	
	/**
	 * Add the goals for search a new random destination.
	 */
	public void addRandomStandardGoals () {		
		ExploreLoadGoal elg = new ExploreLoadGoal(cleaningRobot, false);
		WlanLoadIfRobotWantMergeGoal wlirwmg = new WlanLoadIfRobotWantMergeGoal(cleaningRobot);
		
		MasterGoal mg = new MasterGoal(cleaningRobot);
		mg.subGoals.add(elg);
		mg.subGoals.add(wlirwmg);
		
		if (mg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(mg);
		}
	}
	
	/**
	 * Add the goals where is a complete communication with the master.
	 */
	public void addMasterExploreGoals () {
		MasterExploreRobotGoal merg = new MasterExploreRobotGoal(cleaningRobot);		
		if (merg.isHardwareCorrect()) 
		{
			cleaningRobot.addGoal(merg);
		}
	}
	
	/**
	 * Add the goals where no master is needed.
	 */
	public void addWithoutMasterConfiguration () {		
		WithoutMasterExploreGoal wmg = new WithoutMasterExploreGoal(cleaningRobot);
		if (wmg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(wmg);
		}
	}
	
	/**
	 * Add the goal for an xml and png output of the current map.
	 */
	public void addExploreDumpGoal () {
		cleaningRobot.addGoal(new ExploreDumpGoal(cleaningRobot));
	}
	
	/**
	 * Add the goal for always active WLAN.
	 */
	public void addWlanOnGoal () {
		cleaningRobot.addGoal(new WlanOnGoal(cleaningRobot));
	}
}

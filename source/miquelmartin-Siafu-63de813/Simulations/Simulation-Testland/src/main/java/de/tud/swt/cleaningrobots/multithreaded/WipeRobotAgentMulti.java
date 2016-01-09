package de.tud.swt.cleaningrobots.multithreaded;

import de.nec.nle.siafu.model.MultiWorld;
import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.goals.MasterGoal;
import de.tud.swt.cleaningrobots.goals.nonoptional.MasterWipeRobotGoal;
import de.tud.swt.cleaningrobots.goals.nonoptional.WipeLoadGoal;
import de.tud.swt.cleaningrobots.goals.nonoptional.WithoutMasterWipeGoal;
import de.tud.swt.cleaningrobots.goals.optional.ExploreDumpGoal;
import de.tud.swt.cleaningrobots.goals.optional.WlanLoadIfRobotWantMergeGoal;
import de.tud.swt.cleaningrobots.goals.optional.WlanOnGoal;
import de.tud.swt.cleaningrobots.hardware.Accu;
import de.tud.swt.cleaningrobots.hardware.Motor;
import de.tud.swt.cleaningrobots.hardware.Rechner;
import de.tud.swt.cleaningrobots.hardware.Wiper;
import de.tud.swt.cleaningrobots.hardware.Wlan;
import de.tud.swt.cleaningrobots.model.Position;

/**
 * Used without user interface in Siafu.
 * Create a wipe agent with its hardware components and can add specific goals to him.
 * 
 * @author Christopher Werner
 *
 */
public class WipeRobotAgentMulti extends RobotAgentMulti {

	public WipeRobotAgentMulti(Position start, MultiWorld world, Configuration configuration) {
		super(start, world);

		cleaningRobot = new RobotCore(this, new Accu(48.0), configuration); //new Accu(0, 1000)
		
		cleaningRobot.addHardwareComponent(new Rechner());
		cleaningRobot.addHardwareComponent(new Wlan());
		cleaningRobot.addHardwareComponent(new Motor());
		cleaningRobot.addHardwareComponent(new Wiper());		
	}
	
	/**
	 * Add the goals for search a new relative destination.
	 */
	public void addRelativeStandardGoals () {		
		WipeLoadGoal wlg = new WipeLoadGoal(cleaningRobot, true);
		WlanLoadIfRobotWantMergeGoal wlmmg = new WlanLoadIfRobotWantMergeGoal(cleaningRobot);
		
		MasterGoal mg = new MasterGoal(cleaningRobot);
		mg.subGoals.add(wlg);
		mg.subGoals.add(wlmmg);
		
		if (mg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(mg);
		}
	}
	
	/**
	 * Add the goals for search a new random destination.
	 */
	public void addRandomStandardGoals () {
		WipeLoadGoal wlg = new WipeLoadGoal(cleaningRobot, false);
		WlanLoadIfRobotWantMergeGoal wlmmg = new WlanLoadIfRobotWantMergeGoal(cleaningRobot);
		
		MasterGoal mg = new MasterGoal(cleaningRobot);
		mg.subGoals.add(wlg);
		mg.subGoals.add(wlmmg);
		
		if (mg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(mg);
		}
	}
	
	/**
	 * Add the goals where is a complete communication with the master.
	 */
	public void addMasterWipeGoals () {
		MasterWipeRobotGoal mrg = new MasterWipeRobotGoal(cleaningRobot);		
		if (mrg.isHardwareCorrect()) 
		{
			cleaningRobot.addGoal(mrg);
		}
	}
	
	/**
	 * Add the goals where no master is needed.
	 */
	public void addWithoutMasterConfiguration () {		
		WithoutMasterWipeGoal wmg = new WithoutMasterWipeGoal(cleaningRobot);			
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

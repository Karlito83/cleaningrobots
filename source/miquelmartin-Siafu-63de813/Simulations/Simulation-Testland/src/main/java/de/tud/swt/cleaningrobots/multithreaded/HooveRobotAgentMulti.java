package de.tud.swt.cleaningrobots.multithreaded;

import de.nec.nle.siafu.model.MultiWorld;
import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.goals.MasterGoal;
import de.tud.swt.cleaningrobots.goals.nonoptional.HooveLoadGoal;
import de.tud.swt.cleaningrobots.goals.nonoptional.MasterHooveRobotGoal;
import de.tud.swt.cleaningrobots.goals.nonoptional.WithoutMasterHooveGoal;
import de.tud.swt.cleaningrobots.goals.optional.ExploreDumpGoal;
import de.tud.swt.cleaningrobots.goals.optional.WlanLoadIfRobotWantMergeGoal;
import de.tud.swt.cleaningrobots.goals.optional.WlanOnGoal;
import de.tud.swt.cleaningrobots.hardware.Accu;
import de.tud.swt.cleaningrobots.hardware.Hoover;
import de.tud.swt.cleaningrobots.hardware.Motor;
import de.tud.swt.cleaningrobots.hardware.Rechner;
import de.tud.swt.cleaningrobots.hardware.Wlan;
import de.tud.swt.cleaningrobots.model.Position;

/**
 * Used without user interface in Siafu.
 * Create a hoove agent with its hardware components and can add specific goals to him.
 * 
 * @author Christopher Werner
 *
 */
public class HooveRobotAgentMulti extends RobotAgentMulti {
	
	public HooveRobotAgentMulti(String name, Position start, MultiWorld world, Configuration configuration) {
		super(name, start, world);

		cleaningRobot = new RobotCore(name, this, new Accu(48.0), configuration); //new Accu(0, 1000)
		
		cleaningRobot.addHardwareComponent(new Rechner());
		cleaningRobot.addHardwareComponent(new Wlan());
		cleaningRobot.addHardwareComponent(new Motor());
		cleaningRobot.addHardwareComponent(new Hoover());
	}
	
	/**
	 * Add the goals for search a new relative destination.
	 */
	public void addRelativeStandardGoals () {		
		HooveLoadGoal hlg = new HooveLoadGoal(cleaningRobot, true);
		WlanLoadIfRobotWantMergeGoal wlmmg = new WlanLoadIfRobotWantMergeGoal(cleaningRobot);
		
		MasterGoal mg = new MasterGoal(cleaningRobot);
		mg.subGoals.add(hlg);
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
		HooveLoadGoal hlg = new HooveLoadGoal(cleaningRobot, false);
		WlanLoadIfRobotWantMergeGoal wlmmg = new WlanLoadIfRobotWantMergeGoal(cleaningRobot);
		
		MasterGoal mg = new MasterGoal(cleaningRobot);
		mg.subGoals.add(hlg);
		mg.subGoals.add(wlmmg);
		
		if (mg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(mg);
		}
	}
	
	/**
	 * Add the goals where is a complete communication with the master.
	 */
	public void addMasterHooveGoals () {
		MasterHooveRobotGoal mrg = new MasterHooveRobotGoal(cleaningRobot);			
		if (mrg.isHardwareCorrect()) 
		{
			cleaningRobot.addGoal(mrg);
		}
	}
	
	/**
	 * Add the goals where no master is needed.
	 */
	public void addWithoutMasterConfiguration () {		
		WithoutMasterHooveGoal wmg = new WithoutMasterHooveGoal(cleaningRobot);
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

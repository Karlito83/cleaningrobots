package de.tud.swt.cleaningrobots.multithreaded;

import de.nec.nle.siafu.model.MultiWorld;
import de.tud.evaluation.WorkingConfiguration;
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

public class HooveRobotAgentMulti extends RobotAgentMulti {
	
	public HooveRobotAgentMulti(Position start, MultiWorld world, WorkingConfiguration configuration) {
		super(start, world);

		cleaningRobot = new RobotCore(this, new Accu(48.0), configuration); //new Accu(0, 1000)
		
		cleaningRobot.addHardwareComponent(new Rechner());
		cleaningRobot.addHardwareComponent(new Wlan());
		cleaningRobot.addHardwareComponent(new Motor());
		cleaningRobot.addHardwareComponent(new Hoover());
	}
	
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
	
	//Goals for the MasterExploreFactory
	public void addMasterHooveGoals () {
		MasterHooveRobotGoal mrg = new MasterHooveRobotGoal(cleaningRobot);			
		if (mrg.isHardwareCorrect()) 
		{
			cleaningRobot.addGoal(mrg);
		}
	}
	
	//without Master Configuration
	public void addWithoutMasterConfiguration () {		
		WithoutMasterHooveGoal wmg = new WithoutMasterHooveGoal(cleaningRobot);
		if (wmg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(wmg);
		}
	}
	
	public void addExploreDumpGoal () {
		cleaningRobot.addGoal(new ExploreDumpGoal(cleaningRobot));
	}
	
	public void addWlanOnGoal () {
		cleaningRobot.addGoal(new WlanOnGoal(cleaningRobot));
	}
}

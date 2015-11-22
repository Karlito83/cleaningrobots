package de.tud.swt.testland;

import de.nec.nle.siafu.model.Position;
import de.nec.nle.siafu.model.World;
import de.tud.evaluation.WorkingConfiguration;
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

public class WipeRobotAgent extends RobotAgent {

	public WipeRobotAgent(Position start, String image, World world, WorkingConfiguration configuration) {
		super(start, image, world);

		siafuWorld = world;
		cleaningRobot = new RobotCore(this, new Accu(48.0), configuration); //new Accu(0, 1000)
		
		cleaningRobot.addHardwareComponent(new Rechner());
		cleaningRobot.addHardwareComponent(new Wlan());
		cleaningRobot.addHardwareComponent(new Motor());
		cleaningRobot.addHardwareComponent(new Wiper());		
		
		//System.out.println("Name: " + cleaningRobot.getName() + " : States: " + cleaningRobot.getSupportedStates());
	}
	
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
	
	//Goals for the MasterExploreFactory
	public void addMasterWipeGoals () {
		MasterWipeRobotGoal mrg = new MasterWipeRobotGoal(cleaningRobot);		
		if (mrg.isHardwareCorrect()) 
		{
			cleaningRobot.addGoal(mrg);
		}
	}
	
	//without Master Configuration
	public void addWithoutMasterConfiguration () {		
		WithoutMasterWipeGoal wmg = new WithoutMasterWipeGoal(cleaningRobot);			
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

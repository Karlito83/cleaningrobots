package de.tud.swt.testland;

import de.nec.nle.siafu.model.Position;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.AgentNavigationAdapter;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.goals.MasterGoal;
import de.tud.swt.cleaningrobots.goals.nonoptional.ExploreLoadGoal;
import de.tud.swt.cleaningrobots.goals.nonoptional.MasterExploreRobotGoal;
import de.tud.swt.cleaningrobots.goals.nonoptional.WithoutMasterExploreGoal;
import de.tud.swt.cleaningrobots.goals.optional.ExploreDumpGoal;
import de.tud.swt.cleaningrobots.goals.optional.WlanLoadIfRobotWantMergeGoal;
import de.tud.swt.cleaningrobots.hardware.Accu;
import de.tud.swt.cleaningrobots.hardware.LookAroundSensor;
import de.tud.swt.cleaningrobots.hardware.Motor;
import de.tud.swt.cleaningrobots.hardware.Rechner;
import de.tud.swt.cleaningrobots.hardware.Wlan;

public class ExploreRobotAgent extends RobotAgent {

	public ExploreRobotAgent(Position start, String image, World world) {
		super(start, image, world);

		siafuWorld = world;
		cleaningRobot = new RobotCore(this, new AgentNavigationAdapter(this, siafuWorld), this, new Accu(48.0)); //new Accu(0, 1000)
		
		cleaningRobot.addHardwareComponent(new Rechner());
		cleaningRobot.addHardwareComponent(new Wlan());
		cleaningRobot.addHardwareComponent(new Motor());
		cleaningRobot.addHardwareComponent(new LookAroundSensor());				
	}
	
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
		cleaningRobot.addGoal(new ExploreDumpGoal(cleaningRobot));
	} 
	
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
		cleaningRobot.addGoal(new ExploreDumpGoal(cleaningRobot));
	}
	
	//Goals for the MasterExploreFactory
	public void addMasterExploreGoals () {
		MasterExploreRobotGoal merg = new MasterExploreRobotGoal(cleaningRobot);		
		if (merg.isHardwareCorrect()) 
		{
			cleaningRobot.addGoal(merg);
		}
	}
	
	//without Master Configuration
	public void addWithoutMasterConfiguration () {		
		WithoutMasterExploreGoal wmg = new WithoutMasterExploreGoal(cleaningRobot);
		if (wmg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(wmg);
		}
	}
	
	public void addExploreDumpGoal () {
		cleaningRobot.addGoal(new ExploreDumpGoal(cleaningRobot));
	}
}

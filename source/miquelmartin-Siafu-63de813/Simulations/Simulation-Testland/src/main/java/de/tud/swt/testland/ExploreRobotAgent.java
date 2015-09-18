package de.tud.swt.testland;

import de.nec.nle.siafu.model.Position;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.AgentNavigationAdapter;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.goals.MasterGoal;
import de.tud.swt.cleaningrobots.goals.nonoptional.ExploreLoadGoal;
import de.tud.swt.cleaningrobots.goals.nonoptional.ExploreRelativeLoadGoal;
import de.tud.swt.cleaningrobots.goals.optional.ExploreDumpGoal;
import de.tud.swt.cleaningrobots.goals.optional.WlanLoadIfRobotWantMergeGoal;
import de.tud.swt.cleaningrobots.goals.optional.WlanLoadMasterMergeGoal;
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
		
		//System.out.println("Name: " + cleaningRobot.getName() + " : States: " + cleaningRobot.getSupportedStates() + " Roles: " + cleaningRobot.getRoles());		
	}
	
	public void addStandardRoles () {
		
		ExploreRelativeLoadGoal erlg = new ExploreRelativeLoadGoal(cleaningRobot);
		//ExploreLoadGoal elg = new ExploreLoadGoal(cleaningRobot);
		ExploreDumpGoal edg = new ExploreDumpGoal(cleaningRobot);
		
		WlanLoadIfRobotWantMergeGoal wlirwmg = new WlanLoadIfRobotWantMergeGoal(cleaningRobot);
		//WlanLoadMasterMergeGoal wlmmg = new WlanLoadMasterMergeGoal(cleaningRobot);
		
		MasterGoal mg = new MasterGoal(cleaningRobot);
		mg.subGoals.add(erlg);
		//mg.subGoals.add(elg);
		//mg.subGoals.add(wlmmg);
		mg.subGoals.add(wlirwmg);
		mg.subGoals.add(edg);
		
		if (mg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(mg);
		}
	} 
}

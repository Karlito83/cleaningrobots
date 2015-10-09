package de.tud.swt.testland;

import de.nec.nle.siafu.model.Position;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.AgentNavigationAdapter;
import de.tud.swt.cleaningrobots.MasterRole;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.goals.MasterGoal;
import de.tud.swt.cleaningrobots.goals.nonoptional.MasterExploreMasterGoal;
import de.tud.swt.cleaningrobots.goals.nonoptional.MasterExploreRandomMasterGoal;
import de.tud.swt.cleaningrobots.goals.optional.CalculateRobotPositionGoal;
import de.tud.swt.cleaningrobots.goals.optional.ExploreDumpGoal;
import de.tud.swt.cleaningrobots.goals.optional.LoadIfRobotWantGoal;
import de.tud.swt.cleaningrobots.goals.optional.LoadRobotGoal;
import de.tud.swt.cleaningrobots.goals.optional.MergeMasterFollowerGoal;
import de.tud.swt.cleaningrobots.goals.optional.MergeMasterGoal;
import de.tud.swt.cleaningrobots.hardware.LoadStation;
import de.tud.swt.cleaningrobots.hardware.Rechner;
import de.tud.swt.cleaningrobots.hardware.Wlan;

public class LoadStationAgent extends RobotAgent {

	public LoadStationAgent(Position start, String image, World world) {
		super(start, image, world);

		siafuWorld = world;
		cleaningRobot = new RobotCore(this, new AgentNavigationAdapter(this, siafuWorld), this, null);
		
		cleaningRobot.addHardwareComponent(new Rechner());
		cleaningRobot.addHardwareComponent(new LoadStation());
		cleaningRobot.addHardwareComponent(new Wlan());
				
		//System.out.println("Name: " + cleaningRobot.getName() + " : States: " + cleaningRobot.getSupportedStates() + " Roles: " + cleaningRobot.getRoles());
		
	}
	
	public void addStandardGoals () {
		
		CalculateRobotPositionGoal crpg = new CalculateRobotPositionGoal(cleaningRobot);
		//LoadRobotGoal lrg = new LoadRobotGoal(cleaningRobot);
		LoadIfRobotWantGoal lirwg = new LoadIfRobotWantGoal(cleaningRobot);
		ExploreDumpGoal edg = new ExploreDumpGoal(cleaningRobot);
		
		MasterGoal mg = new MasterGoal(cleaningRobot);
		//mg.subGoals.add(lrg);
		mg.subGoals.add(lirwg);
		mg.subGoals.add(crpg);		
		mg.subGoals.add(edg);
		
		if (mg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(mg);
		}
	}
	
	public void addNonCalculateStandardGoals () {
		
		LoadIfRobotWantGoal lirwg = new LoadIfRobotWantGoal(cleaningRobot);
		ExploreDumpGoal edg = new ExploreDumpGoal(cleaningRobot);
		
		MasterGoal mg = new MasterGoal(cleaningRobot);
		mg.subGoals.add(lirwg);	
		mg.subGoals.add(edg);
		
		if (mg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(mg);
		}
	}
	
	public void addLoadGoal () {
		
		LoadIfRobotWantGoal lirwg = new LoadIfRobotWantGoal(cleaningRobot);
		
		if (lirwg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(lirwg);
		}
	}
	
	public void addMasterFollower () {
		//MergeMasterFollowerGoal mmfg = new MergeMasterFollowerGoal(cleaningRobot);
		cleaningRobot.addGoal(new MergeMasterFollowerGoal(cleaningRobot));
	}
	
	public void addMasterMerge (MasterRole mr) {
		cleaningRobot.addGoal(new MergeMasterGoal(cleaningRobot, mr));
	}
	
	public void addMasterExploreGoals (MasterRole mr) {
		MasterExploreMasterGoal memg = new MasterExploreMasterGoal(cleaningRobot, mr);
		LoadIfRobotWantGoal lirwg = new LoadIfRobotWantGoal(cleaningRobot);
		
		if (memg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(memg);
		}
		if (lirwg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(lirwg);
		}
		cleaningRobot.addGoal(new ExploreDumpGoal(cleaningRobot));
	}
	
	public void addMasterExploreRandomGoals (MasterRole mr) {
		MasterExploreRandomMasterGoal memg = new MasterExploreRandomMasterGoal(cleaningRobot, mr);
		LoadIfRobotWantGoal lirwg = new LoadIfRobotWantGoal(cleaningRobot);
		
		if (memg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(memg);
		}
		if (lirwg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(lirwg);
		}
		cleaningRobot.addGoal(new ExploreDumpGoal(cleaningRobot));
	}

}

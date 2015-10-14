package de.tud.swt.testland;

import de.nec.nle.siafu.model.Position;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.AgentNavigationAdapter;
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
	
	//calculate position goals
	public void addCalculateExploreRobotPositionGoal (MasterRole mr) {
		CalculateExploreRobotPositionGoal crpg = new CalculateExploreRobotPositionGoal(cleaningRobot, mr);
		if (crpg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(crpg);
		}
	}
	
	public void addCalculateHooveRobotPositionGoal (MasterRole mr) {
		CalculateHooveRobotPositionGoal crpg = new CalculateHooveRobotPositionGoal(cleaningRobot, mr);
		if (crpg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(crpg);
		}
	}
	
	public void addCalculateWipeRobotPositionGoal (MasterRole mr) {
		CalculateWipeRobotPositionGoal crpg = new CalculateWipeRobotPositionGoal(cleaningRobot, mr);
		if (crpg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(crpg);
		}
	}
	
	//Load and dump goals
	public void addLoadIfRobotWantGoal () {		
		LoadIfRobotWantGoal lirwg = new LoadIfRobotWantGoal(cleaningRobot);		
		if (lirwg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(lirwg);
		}
	}
	
	public void addExploreDumpGoal () {
		cleaningRobot.addGoal(new ExploreDumpGoal(cleaningRobot));
	}
	
	//merge goals
	/*public void addMasterFollower () {
		MergeMasterFollowerGoal mmfg = new MergeMasterFollowerGoal(cleaningRobot);
		if (mmfg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(mmfg);
		}
	}*/
	
	public void addMasterMerge (MasterRole mr) {
		MergeMasterGoal mmg = new MergeMasterGoal(cleaningRobot, mr);
		if (mmg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(mmg);
		}
	}
	
	//Goals for the Master Explore Factory
	public void addMasterExploreGoals (MasterRole mr, boolean relative) {
		MasterExploreMasterGoal mmg = new MasterExploreMasterGoal(cleaningRobot, mr, relative);		
		if (mmg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(mmg);
		}
	}
	
	public void addMasterHooveGoal (MasterRole mr, boolean relative) {
		MasterHooveMasterGoal mmg = new MasterHooveMasterGoal(cleaningRobot, mr, relative);		
		if (mmg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(mmg);
		}
	}
	
	public void addMasterWipeGoal (MasterRole mr, boolean relative) {
		MasterWipeMasterGoal mmg = new MasterWipeMasterGoal(cleaningRobot, mr, relative);		
		if (mmg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(mmg);
		}
	}

}

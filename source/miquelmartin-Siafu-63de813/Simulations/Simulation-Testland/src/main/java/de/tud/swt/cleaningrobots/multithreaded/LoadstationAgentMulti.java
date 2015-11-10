package de.tud.swt.cleaningrobots.multithreaded;

import de.nec.nle.siafu.model.MultiWorld;
import de.tud.evaluation.WorkingConfiguration;
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
import de.tud.swt.cleaningrobots.model.Position;

public class LoadstationAgentMulti extends RobotAgentMulti {

	public LoadstationAgentMulti (Position start, MultiWorld world, WorkingConfiguration configuration) {
		super(start, world);

		cleaningRobot = new RobotCore(this, null, configuration);
		
		cleaningRobot.addHardwareComponent(new Rechner());
		cleaningRobot.addHardwareComponent(new LoadStation());
		cleaningRobot.addHardwareComponent(new Wlan());
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

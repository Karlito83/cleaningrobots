package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.goals.MasterGoal;
import de.tud.swt.cleaningrobots.goals.nonoptional.ExploreLoadGoal;
import de.tud.swt.cleaningrobots.goals.optional.WlanLoadIfAgentWantMergeGoal;

/**
 * Add the goals for search a new random destination.
 */
public class ExplorerRandomFollowerRole extends FollowerRole {

	public ExplorerRandomFollowerRole(AgentCore robotCore, MasterRole master) {
		super(robotCore, master);
	}

	@Override
	public boolean createGoals() {
		
		ExploreLoadGoal elg = new ExploreLoadGoal(this, false);
		WlanLoadIfAgentWantMergeGoal wlirwmg = new WlanLoadIfAgentWantMergeGoal(this);
		
		MasterGoal mg = new MasterGoal(core);
		mg.subGoals.add(elg);
		mg.subGoals.add(wlirwmg);
		
		if (mg.isHardwareCorrect())
		{
			this.addGoals(mg);
		}
		else
		{
			throw new RuntimeException("Hardeware is not correct for this goal!");
		}
		return mg.isHardwareCorrect();
	}

}

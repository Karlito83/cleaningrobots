package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.goals.MasterGoal;
import de.tud.swt.cleaningrobots.goals.nonoptional.HooveLoadGoal;
import de.tud.swt.cleaningrobots.goals.optional.WlanLoadIfAgentWantMergeGoal;

/**
 * Add the goals for search a new relative destination.
 */
public class HooverRelativeFollowerRole extends FollowerRole {

	public HooverRelativeFollowerRole(AgentCore robotCore, MasterRole master) {
		super(robotCore, master);
	}

	@Override
	public boolean createGoals() {
		
		HooveLoadGoal hlg = new HooveLoadGoal(this, true);
		WlanLoadIfAgentWantMergeGoal wlmmg = new WlanLoadIfAgentWantMergeGoal(this);
		
		MasterGoal mg = new MasterGoal(core);
		mg.subGoals.add(hlg);
		mg.subGoals.add(wlmmg);
		
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

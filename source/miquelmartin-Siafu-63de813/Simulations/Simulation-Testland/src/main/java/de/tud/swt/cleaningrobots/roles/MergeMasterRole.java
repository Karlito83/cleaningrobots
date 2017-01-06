package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.goals.optional.MergeMasterGoal;

/**
 * Add the goal do merge data between master and follower.
 */
public class MergeMasterRole extends MasterRole {

	public MergeMasterRole(AgentCore robotCore) {
		super(robotCore);
	}

	@Override
	public boolean createGoals() {
		
		MergeMasterGoal mmg = new MergeMasterGoal(this);
		if (mmg.isHardwareCorrect())
		{
			this.addGoals(mmg);
		}
		else
		{
			throw new RuntimeException("Hardeware is not correct for this goal!");
		}
		return mmg.isHardwareCorrect();
	}

}

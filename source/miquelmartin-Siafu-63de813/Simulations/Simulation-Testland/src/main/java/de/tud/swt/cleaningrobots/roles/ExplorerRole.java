package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.goals.nonoptional.WithoutMasterExploreGoal;

/**
 * Add the goals where no master is needed.
 */
public class ExplorerRole extends AgentRole {

	public ExplorerRole(AgentCore robotCore) {
		super(robotCore);
	}

	@Override
	public boolean createGoals() {

		WithoutMasterExploreGoal wmg = new WithoutMasterExploreGoal(this);
		if (wmg.isHardwareCorrect())
		{
			this.addGoals(wmg);
		}
		else
		{
			throw new RuntimeException("Hardeware is not correct for this goal!");
		}
		return wmg.isHardwareCorrect();
	}

}

package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.goals.nonoptional.WithoutMasterExploreGoal;

/**
 * Add the goals where no master is needed.
 */
public class ExplorerRole extends RobotRole {

	public ExplorerRole(RobotCore robotCore) {
		super(robotCore);
	}

	@Override
	public boolean createGoals() {

		WithoutMasterExploreGoal wmg = new WithoutMasterExploreGoal(core);
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

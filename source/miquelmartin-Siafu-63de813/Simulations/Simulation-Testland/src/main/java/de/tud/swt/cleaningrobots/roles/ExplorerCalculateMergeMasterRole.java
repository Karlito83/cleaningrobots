package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.goals.optional.CalculateExploreRobotPositionGoal;

/**
 * Add the goal to calculate new discover destinations for all followers.
 */
public class ExplorerCalculateMergeMasterRole extends MergeMasterRole {

	public ExplorerCalculateMergeMasterRole(RobotCore robotCore) {
		super(robotCore);
	}
	
	@Override
	public boolean createGoals() {
		boolean result = super.createGoals();
		
		CalculateExploreRobotPositionGoal crpg = new CalculateExploreRobotPositionGoal(core, this);
		if (crpg.isHardwareCorrect())
		{
			this.addGoals(crpg);
		}
		else
		{
			throw new RuntimeException("Hardeware is not correct for this goal!");
		}
		return result && crpg.isHardwareCorrect();
	}

}

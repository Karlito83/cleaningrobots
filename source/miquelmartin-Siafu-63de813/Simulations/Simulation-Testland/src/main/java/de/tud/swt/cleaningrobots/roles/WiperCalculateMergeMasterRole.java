package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.goals.optional.CalculateWipeRobotPositionGoal;

/**
 * Add the goal to calculate new wipe destinations for all followers.
 */
public class WiperCalculateMergeMasterRole extends MergeMasterRole {

	public WiperCalculateMergeMasterRole(RobotCore robotCore) {
		super(robotCore);
	}
	
	@Override
	public boolean createGoals() {
		boolean result = super.createGoals();
		
		CalculateWipeRobotPositionGoal crpg = new CalculateWipeRobotPositionGoal(core, this);
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

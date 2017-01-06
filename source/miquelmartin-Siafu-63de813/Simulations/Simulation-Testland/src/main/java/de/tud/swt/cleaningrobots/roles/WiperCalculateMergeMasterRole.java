package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.goals.optional.CalculateWipeAgentPositionGoal;

/**
 * Add the goal to calculate new wipe destinations for all followers.
 */
public class WiperCalculateMergeMasterRole extends MergeMasterRole {

	public WiperCalculateMergeMasterRole(AgentCore robotCore) {
		super(robotCore);
	}
	
	@Override
	public boolean createGoals() {
		boolean result = super.createGoals();
		
		CalculateWipeAgentPositionGoal crpg = new CalculateWipeAgentPositionGoal(this);
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

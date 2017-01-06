package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.goals.optional.CalculateExploreAgentPositionGoal;

/**
 * Add the goal to calculate new discover destinations for all followers.
 */
public class ExplorerCalculateMergeMasterRole extends MergeMasterRole {

	public ExplorerCalculateMergeMasterRole(AgentCore robotCore) {
		super(robotCore);
	}
	
	@Override
	public boolean createGoals() {
		boolean result = super.createGoals();
		
		CalculateExploreAgentPositionGoal crpg = new CalculateExploreAgentPositionGoal(this);
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

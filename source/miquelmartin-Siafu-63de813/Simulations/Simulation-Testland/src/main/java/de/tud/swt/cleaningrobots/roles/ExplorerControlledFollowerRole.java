package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.goals.nonoptional.MasterExploreAgentGoal;

/**
 * Add the goals where is a complete communication with the master.
 */
public class ExplorerControlledFollowerRole extends FollowerRole {

	public ExplorerControlledFollowerRole(AgentCore robotCore, MasterRole master) {
		super(robotCore, master);
	}

	@Override
	public boolean createGoals() {
		
		MasterExploreAgentGoal merg = new MasterExploreAgentGoal(this);		
		if (merg.isHardwareCorrect()) 
		{
			this.addGoals(merg);
		}
		else
		{
			throw new RuntimeException("Hardeware is not correct for this goal!");
		}
		return merg.isHardwareCorrect();
	}

}

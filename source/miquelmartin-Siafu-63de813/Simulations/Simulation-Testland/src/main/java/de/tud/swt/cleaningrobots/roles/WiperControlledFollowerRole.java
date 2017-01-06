package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.goals.nonoptional.MasterWipeAgentGoal;

/**
 * Add the goals where is a complete communication with the master.
 */
public class WiperControlledFollowerRole extends FollowerRole {

	public WiperControlledFollowerRole(AgentCore robotCore, MasterRole master) {
		super(robotCore, master);
	}

	@Override
	public boolean createGoals() {
		
		MasterWipeAgentGoal mrg = new MasterWipeAgentGoal(this);		
		if (mrg.isHardwareCorrect()) 
		{
			this.addGoals(mrg);
		}
		else
		{
			throw new RuntimeException("Hardeware is not correct for this goal!");
		}
		return mrg.isHardwareCorrect();
	}

}

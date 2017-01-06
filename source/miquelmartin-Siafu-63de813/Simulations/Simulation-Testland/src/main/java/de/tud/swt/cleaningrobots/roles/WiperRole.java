package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.goals.nonoptional.WithoutMasterWipeGoal;

/**
 * Add the goals where no master is needed.
 */
public class WiperRole extends AgentRole {

	public WiperRole(AgentCore robotCore) {
		super(robotCore);
	}

	@Override
	public boolean createGoals() {
		
		WithoutMasterWipeGoal wmg = new WithoutMasterWipeGoal(this);			
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

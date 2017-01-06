package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.goals.nonoptional.WithoutMasterHooveGoal;

/**
 * Add the goals where no master is needed.
 */
public class HooverRole extends AgentRole {

	public HooverRole(AgentCore robotCore) {
		super(robotCore);
	}

	@Override
	public boolean createGoals() {
		
		WithoutMasterHooveGoal wmg = new WithoutMasterHooveGoal(this);
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

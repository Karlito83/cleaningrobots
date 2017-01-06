package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.goals.nonoptional.MasterWipeMasterGoal;

/**
 * Add goal for the complete management from the master for wipe agents.
 */
public class WiperControlMasterRole extends MasterRole {

	public WiperControlMasterRole(AgentCore robotCore) {
		super(robotCore);
	}

	@Override
	public boolean createGoals() {
		// TODO change relative
		MasterWipeMasterGoal mmg = new MasterWipeMasterGoal(this, false);		
		if (mmg.isHardwareCorrect())
		{
			this.addGoals(mmg);
		}
		else
		{
			throw new RuntimeException("Hardeware is not correct for this goal!");
		}
		return mmg.isHardwareCorrect();
	}

}

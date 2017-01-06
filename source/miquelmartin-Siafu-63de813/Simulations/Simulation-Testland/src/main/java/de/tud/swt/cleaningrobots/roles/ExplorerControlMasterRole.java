package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.goals.nonoptional.MasterExploreMasterGoal;

/**
 * Add goal for the complete management from the master for explore agents.
 */
public class ExplorerControlMasterRole extends MasterRole {

	public ExplorerControlMasterRole(AgentCore robotCore) {
		super(robotCore);
	}

	@Override
	public boolean createGoals() {
		// TODO change relative
		MasterExploreMasterGoal mmg = new MasterExploreMasterGoal(this, false);		
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

package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.goals.nonoptional.MasterHooveMasterGoal;

/**
 * Add goal for the complete management from the master for hoove agents.
 */
public class HooverControlMasterRole extends MasterRole {

	public HooverControlMasterRole(AgentCore robotCore) {
		super(robotCore);
	}

	@Override
	public boolean createGoals() {
		// TODO change relative
		MasterHooveMasterGoal mmg = new MasterHooveMasterGoal(this, false);		
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

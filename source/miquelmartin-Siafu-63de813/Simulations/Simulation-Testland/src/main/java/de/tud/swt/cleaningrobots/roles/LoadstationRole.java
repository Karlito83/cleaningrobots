package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.goals.optional.LoadIfRobotWantGoal;

/**
 * Add the goal to load robots.
 */
public class LoadstationRole extends AgentRole {

	public LoadstationRole(AgentCore robotCore) {
		super(robotCore);
	}

	@Override
	public boolean createGoals() {
		
		LoadIfRobotWantGoal lirwg = new LoadIfRobotWantGoal(this);		
		if (lirwg.isHardwareCorrect())
		{
			this.addGoals(lirwg);
		}
		else
		{
			throw new RuntimeException("Hardeware is not correct for this goal!");
		}
		return lirwg.isHardwareCorrect();
	}

}

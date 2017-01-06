package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.goals.optional.PngDumpGoal;

/**
 * Add the goal for an PNG output of the current map.
 */
public class LoggingPictureRole extends AgentRole {

	public LoggingPictureRole(AgentCore robotCore) {
		super(robotCore);
	}

	@Override
	public boolean createGoals() {
		
		this.addGoals(new PngDumpGoal(this));
		return true;
	}

}

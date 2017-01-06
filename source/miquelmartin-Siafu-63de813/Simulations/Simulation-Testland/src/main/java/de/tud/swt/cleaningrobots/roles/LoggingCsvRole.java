package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.goals.optional.CsvDumpGoal;

/**
 * Add the goal for an PNG output of the current map.
 */
public class LoggingCsvRole extends AgentRole {

	public LoggingCsvRole(AgentCore robotCore) {
		super(robotCore);
	}

	@Override
	public boolean createGoals() {
		
		this.addGoals(new CsvDumpGoal(this));
		return true;
	}

}

package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.goals.optional.CsvDumpGoal;

/**
 * Add the goal for an PNG output of the current map.
 */
public class LoggingCsvRole extends RobotRole {

	public LoggingCsvRole(RobotCore robotCore) {
		super(robotCore);
	}

	@Override
	public boolean createGoals() {
		
		this.addGoals(new CsvDumpGoal(this));
		return true;
	}

}

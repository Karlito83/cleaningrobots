package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.goals.optional.PngDumpGoal;

/**
 * Add the goal for an PNG output of the current map.
 */
public class LoggingPictureRole extends RobotRole {

	public LoggingPictureRole(RobotCore robotCore) {
		super(robotCore);
	}

	@Override
	public boolean createGoals() {
		
		this.addGoals(new PngDumpGoal(this));
		return true;
	}

}

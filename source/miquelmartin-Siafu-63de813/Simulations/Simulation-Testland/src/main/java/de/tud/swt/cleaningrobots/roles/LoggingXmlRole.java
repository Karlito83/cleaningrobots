package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.goals.optional.ExploreDumpGoal;

/**
 * Add the goal for a XML output of the current map.
 */
public class LoggingXmlRole extends RobotRole {

	public LoggingXmlRole(RobotCore robotCore) {
		super(robotCore);
	}

	@Override
	public boolean createGoals() {
		
		this.addGoals(new ExploreDumpGoal(core));
		return true;
	}

}

package de.tud.swt.cleaningrobots.goals;

import de.tud.swt.cleaningrobots.RobotCore;

/**
 * Is a specific sub goal which is non optional and must be implemented.
 * 
 * @author Christopher Werner
 *
 */
public abstract class NonOptionalGoal extends SubGoal{

	public NonOptionalGoal(RobotCore robot) {
		super(robot, false);
	}

}

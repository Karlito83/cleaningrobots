package de.tud.swt.cleaningrobots.goals;

import de.tud.swt.cleaningrobots.RobotCore;

public abstract class NonOptionalGoal extends SubGoal{

	public NonOptionalGoal(RobotCore robot) {
		super(robot, false);
	}

}

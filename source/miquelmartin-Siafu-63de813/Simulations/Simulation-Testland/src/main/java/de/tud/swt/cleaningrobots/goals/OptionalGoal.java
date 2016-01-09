package de.tud.swt.cleaningrobots.goals;

import de.tud.swt.cleaningrobots.RobotCore;

/**
 * Is a specific sub goal which is optional and abstract and must be implemented.
 * 
 * @author Christopher Werner
 *
 */
public abstract class OptionalGoal extends SubGoal{

	public OptionalGoal(RobotCore robot) {
		super(robot, true);
	}

	@Override
	public boolean preCondition() {
		//always return true
		return true;
	}

	@Override
	public boolean postCondition() {
		//always return false because it should not be finished
		return false;
	}

}

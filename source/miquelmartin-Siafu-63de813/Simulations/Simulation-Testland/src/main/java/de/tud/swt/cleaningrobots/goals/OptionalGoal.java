package de.tud.swt.cleaningrobots.goals;

import de.tud.swt.cleaningrobots.RobotRole;

/**
 * Is a specific sub goal which is optional and abstract and must be implemented.
 * 
 * @author Christopher Werner
 *
 */
public abstract class OptionalGoal extends SubGoal{

	public OptionalGoal(RobotRole role) {
		super(role, true);
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

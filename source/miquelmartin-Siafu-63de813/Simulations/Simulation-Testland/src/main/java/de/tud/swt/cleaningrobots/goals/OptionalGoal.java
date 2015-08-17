package de.tud.swt.cleaningrobots.goals;

import de.tud.swt.cleaningrobots.RobotCore;

public abstract class OptionalGoal extends SubGoal{

	public OptionalGoal(RobotCore robot) {
		super(robot, true);
	}

	@Override
	public boolean preCondition() {
		//immer true zurück geben
		return true;
	}

	@Override
	public boolean postCondition() {
		//immer false zurück geben da ergebnis eigentlich nie erreicht
		return false;
	}

}

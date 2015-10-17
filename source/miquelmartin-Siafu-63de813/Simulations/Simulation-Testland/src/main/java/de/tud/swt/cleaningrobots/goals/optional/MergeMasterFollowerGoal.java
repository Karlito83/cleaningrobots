package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.MergeMasterFollower;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

public class MergeMasterFollowerGoal extends OptionalGoal {

	public MergeMasterFollowerGoal(RobotCore robot) {
		super(robot);

		MergeMasterFollower mm = new MergeMasterFollower(robot);
		System.out.println("Correct Load: " + mm.isHardwarecorrect());
		if (mm.isHardwarecorrect()) {
			behaviours.add(mm);
			//robot.addBehaviour(a);
		} else {
			correct = false;
		}
	}
}
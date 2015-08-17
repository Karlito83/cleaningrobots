package de.tud.swt.cleaningrobots.goals;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.MergeAllRonny;

public class MergeRobotsGoal extends OptionalGoal {

	public MergeRobotsGoal(RobotCore robot) {
		super(robot);
		
		MergeAllRonny a = new MergeAllRonny(robot);
		System.out.println("Correct Merge: " + a.isHardwarecorrect());
		if (a.isHardwarecorrect()) {
			behaviours.add(a);
			//robot.addBehaviour(a);
		} else {
			correct = false;
		}
	}

}

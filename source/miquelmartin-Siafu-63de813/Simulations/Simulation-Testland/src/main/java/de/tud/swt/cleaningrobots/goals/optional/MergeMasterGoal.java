package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.MasterRole;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.MergeMaster;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

public class MergeMasterGoal extends OptionalGoal {

	public MergeMasterGoal(RobotCore robot, MasterRole mr) {
		super(robot);

		MergeMaster mm = new MergeMaster(robot, mr);
		System.out.println("Correct Load: " + mm.isHardwarecorrect());
		if (mm.isHardwarecorrect()) {
			behaviours.add(mm);
			//robot.addBehaviour(a);
		} else {
			correct = false;
		}
	}

}

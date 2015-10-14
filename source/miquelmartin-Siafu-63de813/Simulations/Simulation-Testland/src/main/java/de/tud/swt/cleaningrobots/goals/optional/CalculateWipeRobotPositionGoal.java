package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.MasterRole;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.MasterDestinationWipe;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

public class CalculateWipeRobotPositionGoal extends OptionalGoal {

	public CalculateWipeRobotPositionGoal(RobotCore robot, MasterRole mr) {
		super(robot);
		
		MasterDestinationWipe mm = new MasterDestinationWipe(robot, mr);
		System.out.println("Correct Load: " + mm.isHardwarecorrect());
		if (mm.isHardwarecorrect()) {
			behaviours.add(mm);
			//robot.addBehaviour(a);
		} else {
			correct = false;
		}
	}
}

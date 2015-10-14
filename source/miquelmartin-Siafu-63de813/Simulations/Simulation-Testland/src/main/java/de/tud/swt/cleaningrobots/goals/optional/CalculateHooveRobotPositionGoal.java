package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.MasterRole;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.MasterDestinationHoove;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

public class CalculateHooveRobotPositionGoal extends OptionalGoal {

	public CalculateHooveRobotPositionGoal(RobotCore robot, MasterRole mr) {
		super(robot);
		
		MasterDestinationHoove mm = new MasterDestinationHoove(robot, mr);
		System.out.println("Correct Load: " + mm.isHardwarecorrect());
		if (mm.isHardwarecorrect()) {
			behaviours.add(mm);
			//robot.addBehaviour(a);
		} else {
			correct = false;
		}
	}
}

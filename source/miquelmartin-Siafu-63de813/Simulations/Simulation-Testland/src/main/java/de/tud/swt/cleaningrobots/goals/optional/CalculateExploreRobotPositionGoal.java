package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.MasterRole;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.MasterDestinationExplore;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

public class CalculateExploreRobotPositionGoal extends OptionalGoal {

	public CalculateExploreRobotPositionGoal(RobotCore robot, MasterRole mr) {
		super(robot);
		
		MasterDestinationExplore mm = new MasterDestinationExplore(robot, mr);
		System.out.println("Correct Load: " + mm.isHardwarecorrect());
		if (mm.isHardwarecorrect()) {
			behaviours.add(mm);
			//robot.addBehaviour(a);
		} else {
			correct = false;
		}
	}

}

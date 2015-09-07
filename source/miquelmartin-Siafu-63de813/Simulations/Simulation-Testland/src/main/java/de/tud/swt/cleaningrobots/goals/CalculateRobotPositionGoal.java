package de.tud.swt.cleaningrobots.goals;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.MasterDestinationExplore;

public class CalculateRobotPositionGoal extends OptionalGoal{

	public CalculateRobotPositionGoal(RobotCore robot) {
		super(robot);
		
		MasterDestinationExplore mm = new MasterDestinationExplore(robot);
		System.out.println("Correct Load: " + mm.isHardwarecorrect());
		if (mm.isHardwarecorrect()) {
			behaviours.add(mm);
			//robot.addBehaviour(a);
		} else {
			correct = false;
		}
	}

}

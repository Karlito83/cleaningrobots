package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.MasterDestinationExplore;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;
import de.tud.swt.cleaningrobots.roles.MasterRole;

/**
 * Optional goal which calculate new discover destinations for followers which divide the robots in the world. 
 * 
 * @author Christopher Werner
 *
 */
public class CalculateExploreRobotPositionGoal extends OptionalGoal {

	public CalculateExploreRobotPositionGoal(RobotCore robot, MasterRole mr) {
		super(robot);
				
		MasterDestinationExplore mm = new MasterDestinationExplore(robot, mr);
		System.out.println("Correct Load: " + mm.isHardwarecorrect());
		if (mm.isHardwarecorrect()) {
			behaviours.add(mm);
		} else {
			correct = false;
		}
	}

}

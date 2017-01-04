package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.behaviours.MasterDestinationExplore;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

/**
 * Optional goal which calculate new discover destinations for followers which divide the robots in the world. 
 * 
 * @author Christopher Werner
 *
 */
public class CalculateExploreRobotPositionGoal extends OptionalGoal {

	public CalculateExploreRobotPositionGoal(RobotRole role) {
		super(role);
				
		MasterDestinationExplore mm = new MasterDestinationExplore(role);
		System.out.println("Correct Load: " + mm.isHardwarecorrect());
		if (mm.isHardwarecorrect()) {
			behaviours.add(mm);
		} else {
			correct = false;
		}
	}

}

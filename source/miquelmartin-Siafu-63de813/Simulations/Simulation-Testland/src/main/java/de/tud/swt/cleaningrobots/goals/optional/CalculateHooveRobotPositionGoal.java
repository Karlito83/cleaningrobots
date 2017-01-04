package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.behaviours.MasterDestinationHoove;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

/**
 * Optional goal which calculate new hoove destinations for followers which divide the robots in the world. 
 * 
 * @author Christopher Werner
 *
 */
public class CalculateHooveRobotPositionGoal extends OptionalGoal {

	public CalculateHooveRobotPositionGoal(RobotRole role) {
		super(role);
		
		MasterDestinationHoove mm = new MasterDestinationHoove(role);
		System.out.println("Correct Load: " + mm.isHardwarecorrect());
		if (mm.isHardwarecorrect()) {
			behaviours.add(mm);
		} else {
			correct = false;
		}
	}
}

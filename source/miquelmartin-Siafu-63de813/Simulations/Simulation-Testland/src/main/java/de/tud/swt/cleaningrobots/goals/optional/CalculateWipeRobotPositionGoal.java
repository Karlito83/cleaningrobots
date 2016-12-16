package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.MasterDestinationWipe;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;
import de.tud.swt.cleaningrobots.roles.MasterRole;

/**
 * Optional goal which calculate new wipe destinations for followers which divide the robots in the world. 
 * 
 * @author Christopher Werner
 *
 */
public class CalculateWipeRobotPositionGoal extends OptionalGoal {

	public CalculateWipeRobotPositionGoal(RobotCore robot, MasterRole mr) {
		super(robot);
		
		MasterDestinationWipe mm = new MasterDestinationWipe(robot, mr);
		System.out.println("Correct Load: " + mm.isHardwarecorrect());
		if (mm.isHardwarecorrect()) {
			behaviours.add(mm);
		} else {
			correct = false;
		}
	}
}

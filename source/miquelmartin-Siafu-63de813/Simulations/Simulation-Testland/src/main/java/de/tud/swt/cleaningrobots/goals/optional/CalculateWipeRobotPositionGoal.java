package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.RobotRole;
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

	public CalculateWipeRobotPositionGoal(RobotRole role) {
		super(role);
		
		MasterDestinationWipe mm = new MasterDestinationWipe(getRobotCore(), (MasterRole) role);
		System.out.println("Correct Load: " + mm.isHardwarecorrect());
		if (mm.isHardwarecorrect()) {
			behaviours.add(mm);
		} else {
			correct = false;
		}
	}
}

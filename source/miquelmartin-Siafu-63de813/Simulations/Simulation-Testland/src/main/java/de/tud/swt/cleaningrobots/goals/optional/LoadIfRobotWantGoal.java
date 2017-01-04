package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.behaviours.LoadIfRobotWantBehaviour;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

/**
 * Optional goal which load robots around if they want. 
 * 
 * @author Christopher Werner
 *
 */
public class LoadIfRobotWantGoal extends OptionalGoal {

	public LoadIfRobotWantGoal(RobotRole role) {
		super(role);
				
		LoadIfRobotWantBehaviour l = new LoadIfRobotWantBehaviour(role);
		System.out.println("Correct Load: " + l.isHardwarecorrect());
		if (l.isHardwarecorrect()) {
			behaviours.add(l);
		} else {
			correct = false;
		}
	}
}

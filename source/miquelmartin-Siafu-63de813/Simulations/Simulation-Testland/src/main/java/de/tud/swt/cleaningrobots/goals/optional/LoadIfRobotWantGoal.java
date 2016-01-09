package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.LoadIfRobotWantBehaviour;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

/**
 * Optional goal which load robots around if they want. 
 * 
 * @author Christopher Werner
 *
 */
public class LoadIfRobotWantGoal extends OptionalGoal {

	public LoadIfRobotWantGoal(RobotCore robot) {
		super(robot);
				
		LoadIfRobotWantBehaviour l = new LoadIfRobotWantBehaviour(robot);
		System.out.println("Correct Load: " + l.isHardwarecorrect());
		if (l.isHardwarecorrect()) {
			behaviours.add(l);
		} else {
			correct = false;
		}
	}
}

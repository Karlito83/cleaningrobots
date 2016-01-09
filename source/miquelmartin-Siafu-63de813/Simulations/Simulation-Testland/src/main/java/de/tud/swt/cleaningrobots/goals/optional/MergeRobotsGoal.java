package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.MergeAllOfNearBehaviour;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

/**
 * Optional goal which make the merge between robots with the EMF model. 
 * 
 * @author Christopher Werner
 *
 */
public class MergeRobotsGoal extends OptionalGoal {

	public MergeRobotsGoal(RobotCore robot) {
		super(robot);
		
		MergeAllOfNearBehaviour a = new MergeAllOfNearBehaviour(robot);
		System.out.println("Correct Merge: " + a.isHardwarecorrect());
		if (a.isHardwarecorrect()) {
			behaviours.add(a);
		} else {
			correct = false;
		}
	}

}

package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.behaviours.MergeAllOfNearWithoutModel;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

/**
 * Optional goal which make the merge between robots without using the EMF model. 
 * 
 * @author Christopher Werner
 *
 */
public class MergeRobotsWithoutModelGoal extends OptionalGoal {

	public MergeRobotsWithoutModelGoal(RobotRole role) {
		super(role);
		
		MergeAllOfNearWithoutModel a = new MergeAllOfNearWithoutModel(getRobotCore());
		System.out.println("Correct Merge: " + a.isHardwarecorrect());
		if (a.isHardwarecorrect()) {
			behaviours.add(a);
		} else {
			correct = false;
		}
	}
}

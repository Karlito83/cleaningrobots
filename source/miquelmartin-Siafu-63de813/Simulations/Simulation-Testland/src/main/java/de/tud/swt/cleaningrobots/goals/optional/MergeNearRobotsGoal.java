package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.behaviours.merge.MergeAllNearBehaviour;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

/**
 * Optional goal which make the merge between robots with the EMF model. 
 * 
 * @author Christopher Werner
 *
 */
public class MergeNearRobotsGoal extends OptionalGoal {

	public MergeNearRobotsGoal(RobotRole role) {
		super(role);
		
		MergeAllNearBehaviour a = new MergeAllNearBehaviour(role, false);
		System.out.println("Correct Merge: " + a.isHardwarecorrect());
		if (a.isHardwarecorrect()) {
			behaviours.add(a);
		} else {
			correct = false;
		}
	}

}

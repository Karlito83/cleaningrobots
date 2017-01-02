package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.behaviours.MergeMasterFollower;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

/**
 * Old Goal.
 * Optional goal which make the merge between a master and his followers with the EMF model. 
 * 
 * @author Christopher Werner
 *
 */
public class MergeMasterFollowerGoal extends OptionalGoal {

	public MergeMasterFollowerGoal(RobotRole role) {
		super(role);

		MergeMasterFollower mm = new MergeMasterFollower(getRobotCore());
		System.out.println("Correct Load: " + mm.isHardwarecorrect());
		if (mm.isHardwarecorrect()) {
			behaviours.add(mm);
		} else {
			correct = false;
		}
	}
}

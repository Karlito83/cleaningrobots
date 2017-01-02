package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.behaviours.MergeMasterWithoutModel;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;
import de.tud.swt.cleaningrobots.roles.MasterRole;

/**
 * Optional goal which make the merge between a master and his followers without using the EMF model. 
 * 
 * @author Christopher Werner
 *
 */
public class MergeMasterGoal extends OptionalGoal {

	public MergeMasterGoal(RobotRole role) {
		super(role);

		MergeMasterWithoutModel mm = new MergeMasterWithoutModel(getRobotCore(), (MasterRole) role, false);
		System.out.println("Correct Load: " + mm.isHardwarecorrect());
		if (mm.isHardwarecorrect()) {
			behaviours.add(mm);
			//robot.addBehaviour(a);
		} else {
			correct = false;
		}
	}

}

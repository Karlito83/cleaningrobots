package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.behaviours.merge.MergeOneMasterBehaviour;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

/**
 * Optional goal which make the merge between a master and his followers without using the EMF model. 
 * 
 * @author Christopher Werner
 *
 */
public class MergeMasterGoal extends OptionalGoal {

	public MergeMasterGoal(AgentRole role) {
		super(role);

		MergeOneMasterBehaviour mm = new MergeOneMasterBehaviour(role, false);
		System.out.println("Correct Load: " + mm.isHardwarecorrect());
		if (mm.isHardwarecorrect()) {
			behaviours.add(mm);
			//robot.addBehaviour(a);
		} else {
			correct = false;
		}
	}

}

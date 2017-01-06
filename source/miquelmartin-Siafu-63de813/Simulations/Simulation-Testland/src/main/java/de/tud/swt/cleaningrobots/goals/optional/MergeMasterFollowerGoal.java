package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.behaviours.merge.MergeAllMasterBehaviour;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

/**
 * Old Goal.
 * Optional goal which make the merge between a master and his followers with the EMF model. 
 * 
 * @author Christopher Werner
 *
 */
public class MergeMasterFollowerGoal extends OptionalGoal {

	public MergeMasterFollowerGoal(AgentRole role) {
		super(role);

		MergeAllMasterBehaviour mm = new MergeAllMasterBehaviour(role, false);
		System.out.println("Correct Load: " + mm.isHardwarecorrect());
		if (mm.isHardwarecorrect()) {
			behaviours.add(mm);
		} else {
			correct = false;
		}
	}
}

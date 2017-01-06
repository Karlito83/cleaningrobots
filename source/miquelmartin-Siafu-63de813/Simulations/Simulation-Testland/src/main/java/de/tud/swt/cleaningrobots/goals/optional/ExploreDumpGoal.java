package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.behaviours.DumpModelBehaviour;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

/**
 * Could always run and always be finished and save the images for reconstruction
 * 
 * @author Christopher Werner
 *
 */
public class ExploreDumpGoal extends OptionalGoal {

	public ExploreDumpGoal(AgentRole role) {
		super(role);
		
		DumpModelBehaviour b = new DumpModelBehaviour(role);
		System.out.println("Correct Dump: " + b.isHardwarecorrect());
		if (b.isHardwarecorrect()) {
			behaviours.add(b);
		} else {
			correct = false;
		}
	}

}

package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.behaviours.LoadIfAgentWantBehaviour;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

/**
 * Optional goal which load robots around if they want. 
 * 
 * @author Christopher Werner
 *
 */
public class LoadIfRobotWantGoal extends OptionalGoal {

	public LoadIfRobotWantGoal(AgentRole role) {
		super(role);
				
		LoadIfAgentWantBehaviour l = new LoadIfAgentWantBehaviour(role);
		System.out.println("Correct Load: " + l.isHardwarecorrect());
		if (l.isHardwarecorrect()) {
			behaviours.add(l);
		} else {
			correct = false;
		}
	}
}

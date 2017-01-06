package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.behaviours.MasterDestinationWipeBehaviour;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

/**
 * Optional goal which calculate new wipe destinations for followers which divide the robots in the world. 
 * 
 * @author Christopher Werner
 *
 */
public class CalculateWipeAgentPositionGoal extends OptionalGoal {

	public CalculateWipeAgentPositionGoal(AgentRole role) {
		super(role);
		
		MasterDestinationWipeBehaviour mm = new MasterDestinationWipeBehaviour(role);
		System.out.println("Correct Load: " + mm.isHardwarecorrect());
		if (mm.isHardwarecorrect()) {
			behaviours.add(mm);
		} else {
			correct = false;
		}
	}
}

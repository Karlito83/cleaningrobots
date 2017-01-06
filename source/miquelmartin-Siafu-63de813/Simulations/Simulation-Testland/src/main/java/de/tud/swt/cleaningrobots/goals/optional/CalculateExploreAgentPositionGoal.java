package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.behaviours.MasterDestinationExploreBehaviour;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

/**
 * Optional goal which calculate new discover destinations for followers which divide the robots in the world. 
 * 
 * @author Christopher Werner
 *
 */
public class CalculateExploreAgentPositionGoal extends OptionalGoal {

	public CalculateExploreAgentPositionGoal(AgentRole role) {
		super(role);
				
		MasterDestinationExploreBehaviour mm = new MasterDestinationExploreBehaviour(role);
		System.out.println("Correct Load: " + mm.isHardwarecorrect());
		if (mm.isHardwarecorrect()) {
			behaviours.add(mm);
		} else {
			correct = false;
		}
	}

}

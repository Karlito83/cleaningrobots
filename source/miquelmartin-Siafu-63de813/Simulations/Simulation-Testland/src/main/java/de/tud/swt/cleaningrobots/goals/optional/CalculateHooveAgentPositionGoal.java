package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.behaviours.MasterDestinationHooveBehaviour;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

/**
 * Optional goal which calculate new hoove destinations for followers which divide the robots in the world. 
 * 
 * @author Christopher Werner
 *
 */
public class CalculateHooveAgentPositionGoal extends OptionalGoal {

	public CalculateHooveAgentPositionGoal(AgentRole role) {
		super(role);
		
		MasterDestinationHooveBehaviour mm = new MasterDestinationHooveBehaviour(role);
		System.out.println("Correct Load: " + mm.isHardwarecorrect());
		if (mm.isHardwarecorrect()) {
			behaviours.add(mm);
		} else {
			correct = false;
		}
	}
}

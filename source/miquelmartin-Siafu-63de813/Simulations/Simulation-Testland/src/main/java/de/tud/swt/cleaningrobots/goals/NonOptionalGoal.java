package de.tud.swt.cleaningrobots.goals;

import de.tud.swt.cleaningrobots.AgentRole;

/**
 * Is a specific sub goal which is non optional and must be implemented.
 * 
 * @author Christopher Werner
 *
 */
public abstract class NonOptionalGoal extends SubGoal{

	public NonOptionalGoal(AgentRole role) {
		super(role, false);
	}

}

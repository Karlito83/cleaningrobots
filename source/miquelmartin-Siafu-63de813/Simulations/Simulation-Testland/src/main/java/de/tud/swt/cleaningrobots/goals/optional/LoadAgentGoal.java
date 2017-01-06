package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.behaviours.LoadBehaviour;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

/**
 * Optional Goal which load all robots around it
 * 
 * @author Christopher Werner
 *
 */
public class LoadAgentGoal extends OptionalGoal {

	public LoadAgentGoal(AgentRole role) {
		super(role);
				
		LoadBehaviour l = new LoadBehaviour(role);
		System.out.println("Correct Load: " + l.isHardwarecorrect());
		if (l.isHardwarecorrect()) {
			behaviours.add(l);
			//robot.addBehaviour(l);
		} else {
			correct = false;
		}
	}	

}

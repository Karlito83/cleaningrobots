package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.behaviours.LoadWlanActivateBehaviour;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

/**
 * Optional goal which start the WLAN on the robot, when he is loading. 
 * 
 * @author Christopher Werner
 *
 */
public class WlanLoadMasterMergeGoal extends OptionalGoal {

	public WlanLoadMasterMergeGoal(AgentRole role) {
		super(role);
		
		LoadWlanActivateBehaviour lab = new LoadWlanActivateBehaviour(role);
		System.out.println("Correct WlanActive: " + lab.isHardwarecorrect());
		if (lab.isHardwarecorrect()) {
			behaviours.add(lab);
		} else {
			correct = false;
		}
	}

}

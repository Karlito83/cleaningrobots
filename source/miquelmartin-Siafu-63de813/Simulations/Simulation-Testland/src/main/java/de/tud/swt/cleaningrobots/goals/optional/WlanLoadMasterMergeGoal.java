package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.LoadWlanActivateBehaviour;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

/**
 * Optional goal which start the WLAN on the robot, when he is loading. 
 * 
 * @author Christopher Werner
 *
 */
public class WlanLoadMasterMergeGoal extends OptionalGoal {

	public WlanLoadMasterMergeGoal(RobotCore robot) {
		super(robot);
		
		LoadWlanActivateBehaviour lab = new LoadWlanActivateBehaviour(robot);
		System.out.println("Correct WlanActive: " + lab.isHardwarecorrect());
		if (lab.isHardwarecorrect()) {
			behaviours.add(lab);
		} else {
			correct = false;
		}
	}

}

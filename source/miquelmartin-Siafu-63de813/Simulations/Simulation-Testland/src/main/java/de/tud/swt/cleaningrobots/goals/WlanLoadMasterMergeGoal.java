package de.tud.swt.cleaningrobots.goals;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.LoadWlanActivateBehaviour;

public class WlanLoadMasterMergeGoal extends OptionalGoal {

	public WlanLoadMasterMergeGoal(RobotCore robot) {
		super(robot);
		
		LoadWlanActivateBehaviour lab = new LoadWlanActivateBehaviour(robot);
		System.out.println("Correct WlanActive: " + lab.isHardwarecorrect());
		if (lab.isHardwarecorrect()) {
			//robot.addBehaviour(b);
			behaviours.add(lab);
		} else {
			correct = false;
		}
	}

}

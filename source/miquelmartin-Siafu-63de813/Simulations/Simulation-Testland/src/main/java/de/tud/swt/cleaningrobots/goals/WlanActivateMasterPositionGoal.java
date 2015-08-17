package de.tud.swt.cleaningrobots.goals;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.LoadPositionWlanActivateBehaviour;

public class WlanActivateMasterPositionGoal extends OptionalGoal {

	public WlanActivateMasterPositionGoal (RobotCore robot) {
		super(robot);
		
		LoadPositionWlanActivateBehaviour lab = new LoadPositionWlanActivateBehaviour(robot);
		System.out.println("Correct WlanActive: " + lab.isHardwarecorrect());
		if (lab.isHardwarecorrect()) {
			//robot.addBehaviour(b);
			behaviours.add(lab);
		} else {
			correct = false;
		}
	}
}

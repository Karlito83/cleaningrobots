package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.WlanOnBehaviour;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

public class WlanOnGoal extends OptionalGoal {

	public WlanOnGoal(RobotCore robot) {
		super(robot);
		
		WlanOnBehaviour wo = new WlanOnBehaviour(robot);
		System.out.println("Correct WlanActive: " + wo.isHardwarecorrect());
		if (wo.isHardwarecorrect()) {
			behaviours.add(wo);
		} else {
			correct = false;
		}
	}

}

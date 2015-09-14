package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.LoadIfAtLoadStationBehaviour;
import de.tud.swt.cleaningrobots.behaviours.LoadWlanActivateBehaviour;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

public class WlanLoadIfRobotWantMergeGoal extends OptionalGoal {

	public WlanLoadIfRobotWantMergeGoal(RobotCore robot) {
		super(robot);
				
		LoadIfAtLoadStationBehaviour lialsb = new LoadIfAtLoadStationBehaviour(robot);
		System.out.println("Correct LoadIfAtLoadStation: " + lialsb.isHardwarecorrect());
		if (lialsb.isHardwarecorrect()) {
			//robot.addBehaviour(b);
			behaviours.add(lialsb);
		} else {
			correct = false;
		}
		
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

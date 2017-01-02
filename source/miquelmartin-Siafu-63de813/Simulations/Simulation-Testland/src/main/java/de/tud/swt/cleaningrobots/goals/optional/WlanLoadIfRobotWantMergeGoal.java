package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.behaviours.LoadIfAtLoadStationBehaviour;
import de.tud.swt.cleaningrobots.behaviours.LoadWlanActivateBehaviour;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

/**
 * Optional goal which load a robot and start the WLAN on the robot, when he is at the load station. 
 * 
 * @author Christopher Werner
 *
 */
public class WlanLoadIfRobotWantMergeGoal extends OptionalGoal {

	public WlanLoadIfRobotWantMergeGoal(RobotRole role) {
		super(role);
				
		LoadIfAtLoadStationBehaviour lialsb = new LoadIfAtLoadStationBehaviour(getRobotCore());
		System.out.println("Correct LoadIfAtLoadStation: " + lialsb.isHardwarecorrect());
		if (lialsb.isHardwarecorrect()) {
			//robot.addBehaviour(b);
			behaviours.add(lialsb);
		} else {
			correct = false;
		}
		
		LoadWlanActivateBehaviour lab = new LoadWlanActivateBehaviour(getRobotCore());
		System.out.println("Correct WlanActive: " + lab.isHardwarecorrect());
		if (lab.isHardwarecorrect()) {
			//robot.addBehaviour(b);
			behaviours.add(lab);
		} else {
			correct = false;
		}
	}

}

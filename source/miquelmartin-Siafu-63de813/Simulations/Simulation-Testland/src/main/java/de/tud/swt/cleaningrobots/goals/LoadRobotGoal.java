package de.tud.swt.cleaningrobots.goals;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.LoadBehaviour;

/**
 * Optional Goal which load all robots around it
 * 
 * @author ChrissiMobil
 *
 */
public class LoadRobotGoal extends OptionalGoal {

	public LoadRobotGoal(RobotCore robot) {
		super(robot);
				
		LoadBehaviour l = new LoadBehaviour(robot);
		System.out.println("Correct Load: " + l.isHardwarecorrect());
		if (l.isHardwarecorrect()) {
			behaviours.add(l);
			//robot.addBehaviour(l);
		} else {
			correct = false;
		}
	}	

}

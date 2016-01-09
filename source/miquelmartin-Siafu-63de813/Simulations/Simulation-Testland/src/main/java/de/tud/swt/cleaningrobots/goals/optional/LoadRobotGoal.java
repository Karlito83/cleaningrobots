package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.LoadBehaviour;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

/**
 * Optional Goal which load all robots around it
 * 
 * @author Christopher Werner
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

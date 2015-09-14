package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.DumpModelBehaviour;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

/**
 * Could always run and always be finished and save the images vor reconstruction
 * 
 * @author ChrissiMobil
 *
 */
public class ExploreDumpGoal extends OptionalGoal {

	public ExploreDumpGoal(RobotCore robot) {
		super(robot);
		
		DumpModelBehaviour b = new DumpModelBehaviour(robot);
		System.out.println("Correct Dump: " + b.isHardwarecorrect());
		if (b.isHardwarecorrect()) {
			//robot.addBehaviour(b);
			behaviours.add(b);
		} else {
			correct = false;
		}
	}

}

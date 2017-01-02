package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.behaviours.DumpCSVModelBehaviour;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

/**
 * Could always run and always be finished and save the images for reconstruction
 * 
 * @author Christopher Werner
 *
 */
public class CsvDumpGoal extends OptionalGoal {

	public CsvDumpGoal(RobotRole role) {
		super(role);
		
		DumpCSVModelBehaviour b = new DumpCSVModelBehaviour(getRobotCore());
		System.out.println("Correct Csv Dump: " + b.isHardwarecorrect());
		if (b.isHardwarecorrect()) {
			behaviours.add(b);
		} else {
			correct = false;
		}
	}

}

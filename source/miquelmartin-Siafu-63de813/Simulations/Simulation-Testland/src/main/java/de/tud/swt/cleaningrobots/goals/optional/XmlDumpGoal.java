package de.tud.swt.cleaningrobots.goals.optional;

import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.behaviours.DumpXmlModelBehaviour;
import de.tud.swt.cleaningrobots.goals.OptionalGoal;

/**
 * Could always run and always be finished and save the images for reconstruction
 * 
 * @author Christopher Werner
 *
 */
public class XmlDumpGoal extends OptionalGoal {

	public XmlDumpGoal(AgentRole role) {
		super(role);
		
		DumpXmlModelBehaviour b = new DumpXmlModelBehaviour(role);
		System.out.println("Correct Dump: " + b.isHardwarecorrect());
		if (b.isHardwarecorrect()) {
			behaviours.add(b);
		} else {
			correct = false;
		}
	}

}

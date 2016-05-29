package de.tud.swt.cleaningrobots.merge;

import de.tud.swt.cleaningrobots.Configuration;

public class InformationMerge extends Merge {

	public InformationMerge(Configuration configuration) {
		super(configuration);
	}
	
	/**
	 * Measurement if robot comes with new information.
	 * @param name
	 */
	public void run (String name) {
		this.preRun(name, "New Info");
		em.addKnowledgeIntegerNumber(1);
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(name.getBytes().length);
		this.postRun();
	}

}

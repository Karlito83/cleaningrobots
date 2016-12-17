package de.tud.swt.cleaningrobots.merge;

import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.RobotCore;

public class PositionInformationMerge extends Merge {

	public PositionInformationMerge(Configuration configuration) {
		super(configuration);
	}

	/**
	 * Only Methods that saves the data exchange if a new destination is calculated.
	 * @param name
	 */
	/*public void sendDestination (String name) {
		//measurement
		this.preRun(name, "SendNewDest");
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(name.getBytes().length);
		em.addWorldPositionCount(1);
		this.postRun();
	}*/

	@Override
	protected void action(RobotCore from, RobotCore to, Object object) {
		//measurement
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(to.getName().getBytes().length);
		em.addWorldPositionCount(1);
		
	}
}

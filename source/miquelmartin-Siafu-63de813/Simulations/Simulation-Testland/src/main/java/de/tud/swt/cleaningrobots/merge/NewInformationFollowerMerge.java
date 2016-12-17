package de.tud.swt.cleaningrobots.merge;

import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;

public class NewInformationFollowerMerge extends Merge {

	public NewInformationFollowerMerge(Configuration configuration) {
		super(configuration);
	}

	@Override
	protected void action(RobotCore from, RobotCore to, Object object) {
		RobotRole rr = (RobotRole) object;	
		rr.setNewInformation(false);
		//measurement
		em.addKnowledgeIntegerNumber(1);
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(to.getName().getBytes().length);
	}

}

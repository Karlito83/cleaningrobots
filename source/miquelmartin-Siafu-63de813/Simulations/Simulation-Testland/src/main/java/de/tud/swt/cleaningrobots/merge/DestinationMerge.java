package de.tud.swt.cleaningrobots.merge;

import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.model.Position;

public class DestinationMerge extends Merge {

	public DestinationMerge(Configuration configuration) {
		super(configuration);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void action(RobotCore from, RobotCore to, Object object) {
		if (object == null)
		{
			to.getDestinationContainer().setDestination(null, true);
			//measurement
			em.addKnowledgeStringNumber(1);
			em.addKnowledgeStringByteNumber(to.getName().getBytes().length);
			em.addWorldIntegerNumber(1);
		}
		else
		{			
			Position destination = (Position) object;
			to.getDestinationContainer().setDestination(destination, true);
			//measurement
			em.addKnowledgeStringNumber(1);
			em.addKnowledgeStringByteNumber(to.getName().getBytes().length);
			em.addWorldPositionCount(1);
		}
	}
}

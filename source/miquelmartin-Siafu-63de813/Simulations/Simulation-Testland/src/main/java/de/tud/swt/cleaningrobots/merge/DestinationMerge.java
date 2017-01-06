package de.tud.swt.cleaningrobots.merge;

import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.model.Position;

/**
 * The Master send a new Destination Position to the Follower and saves the measurement.
 * 
 * @author Christopher Werner
 *
 */
public class DestinationMerge extends Merge {

	public DestinationMerge(Configuration configuration) {
		super(configuration);
	}

	@Override
	protected void action(AgentCore from, AgentCore to, Object object) {
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

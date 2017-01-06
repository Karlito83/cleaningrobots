package de.tud.swt.cleaningrobots.merge;

import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.util.PathWayMergeInformation;

/**
 * Send the new Destination and the path to the Follower and measure the informations.
 * 
 * @author Christopher Werner
 *
 */
public class PathDestinationMerge extends Merge {

	public PathDestinationMerge(Configuration configuration) {
		super(configuration);
	}
	
	@Override
	protected void action(AgentCore from, AgentCore to, Object object) {
		PathWayMergeInformation pathway = (PathWayMergeInformation) object;
		to.getDestinationContainer().setDestinationAndPath(pathway.getPath(), pathway.getDestination());
		//measurement
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(from.getName().getBytes().length);
		em.addWorldPositionCount(pathway.getPath().size());		
	}

}

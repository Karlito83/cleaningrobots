package de.tud.swt.cleaningrobots.merge;

import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.util.PathWayMergeInformation;

public class PathDestinationMerge extends Merge {

	public PathDestinationMerge(Configuration configuration) {
		super(configuration);
	}
	
	/**
	 * Send the new Destination and the path to the Follower and measure the informations.
	 */
	@Override
	protected void action(RobotCore from, RobotCore to, Object object) {
		PathWayMergeInformation pathway = (PathWayMergeInformation) object;
		to.getDestinationContainer().setDestinationAndPath(pathway.getPath(), pathway.getDestination());
		//measurement
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(from.getName().getBytes().length);
		em.addWorldPositionCount(pathway.getPath().size());		
	}

}

package de.tud.swt.cleaningrobots.merge;

import java.util.List;

import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.model.Position;

public class DestinationMerge extends Merge {

	public DestinationMerge(Configuration configuration) {
		super(configuration);
	}
	
	/**
	 * Send the new Destination to the Follower and measure the informations.
	 * @param name
	 * @param follower
	 * @param path
	 * @param destination
	 */
	public void run (String name, RobotCore follower, List<Position> path, Position destination) {
		follower.getDestinationContainer().setDestinationAndPath(path, destination);
		//measurement
		this.preRun(name, follower.getName());
		em.addKnowledgeStringNumber(1);
		em.addKnowledgeStringByteNumber(name.getBytes().length);
		em.addWorldPositionCount(path.size());
		this.postRun();
	}

}

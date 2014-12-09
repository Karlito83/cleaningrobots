package de.nec.nle.siafu.testland;

import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.Position;
import de.nec.nle.siafu.model.World;

public class CleaningRobotAgent extends Agent{

	private World world;
	

	public CleaningRobotAgent(Position start, String image, World world) {
		super(start, image, world);
		this.world = world;
	}
}

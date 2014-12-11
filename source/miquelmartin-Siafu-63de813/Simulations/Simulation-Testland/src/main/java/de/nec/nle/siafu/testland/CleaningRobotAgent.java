package de.nec.nle.siafu.testland;

import cleaningrobots.CleaningrobotsFactory;
import cleaningrobots.Map;
import cleaningrobots.Robot;
import cleaningrobots.impl.CleaningrobotsFactoryImpl;
import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.Position;
import de.nec.nle.siafu.model.World;

public class CleaningRobotAgent extends Agent {
	
	Robot cleaningRobot;
	World siafuWorld;
	Map robotsWorld;
	
	

	public CleaningRobotAgent(Position start, String image, World world) {
		super(start, image, world);
		
		CleaningrobotsFactory factory = CleaningrobotsFactory.eINSTANCE;
		this.siafuWorld = world;
		this.cleaningRobot = factory.createRobot();
		this.robotsWorld = factory.createMap();
		
		this.robotsWorld.setXdim(siafuWorld.getWidth());
		this.robotsWorld.setYdim(siafuWorld.getHeight());
		
		this.cleaningRobot.setMap(robotsWorld);
		
	}

}

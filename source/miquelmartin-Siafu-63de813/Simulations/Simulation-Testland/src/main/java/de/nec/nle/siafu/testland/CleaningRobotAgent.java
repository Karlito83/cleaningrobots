package de.nec.nle.siafu.testland;

import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.Place;
import de.nec.nle.siafu.model.Position;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.IPositionProvider;
import de.tud.swt.cleaningrobots.Robot;

public class CleaningRobotAgent extends Agent implements IPositionProvider {

	Robot cleaningRobot;
	World siafuWorld;

	public CleaningRobotAgent(Position start, String image, World world) {
		super(start, image, world);
		siafuWorld = world;
		cleaningRobot = new Robot(this);
	}

	@Override
	public void wander() {
		// TODO Auto-generated method stub
		super.wander();
	}

	public Robot getCleaningRobot() {
		return this.cleaningRobot;
	}

	@Override
	public void setName(String name) {
		super.setName(name);
		this.cleaningRobot.setName(name);
	}

	public de.tud.swt.cleaningrobots.Position getPosition() {
		de.tud.swt.cleaningrobots.Position result = new de.tud.swt.cleaningrobots.Position(
				this.getPos().getCol(), this.getPos().getRow());
		return result;
	}
	
	@Override
	public Place getDestination() {
		// TODO Auto-generated method stub
		return super.getDestination();
	}
	
	@Override
	public void setDestination(Place destination) {
		// TODO Auto-generated method stub
		super.setDestination(destination);
	}

}

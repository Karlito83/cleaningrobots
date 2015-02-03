package de.nec.nle.siafu.testland;

import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.Place;
import de.nec.nle.siafu.model.Position;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.INavigationController;
import de.tud.swt.cleaningrobots.IPositionProvider;
import de.tud.swt.cleaningrobots.Robot;
import de.tud.swt.cleaningrobots.behaviours.CleanBehaviour;
import de.tud.swt.cleaningrobots.behaviours.DiscoverBehaviour;
import de.tud.swt.cleaningrobots.behaviours.MoveBehaviour;

public class CleaningRobotAgent extends Agent implements IPositionProvider{

	Robot cleaningRobot;
	World siafuWorld;
	
	public CleaningRobotAgent(Position start, String image, World world) {
		super(start, image, world);
		siafuWorld = world;
		cleaningRobot = new Robot(this, new AgentNavigationAdapter(this, siafuWorld));
		cleaningRobot.addSensor(new BlockedOnlySensor(siafuWorld, this));
		cleaningRobot.addBehaviour(new MoveBehaviour(cleaningRobot));
		cleaningRobot.addBehaviour(new DiscoverBehaviour(cleaningRobot));
		this.setSpeed(1);
	}

	@Override
	public void wander() {
		// TODO Auto-generated method stub
		cleaningRobot.action();
		//super.wander();
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
	public void setSpeed(int speed) {
		//ignore..
	}
}

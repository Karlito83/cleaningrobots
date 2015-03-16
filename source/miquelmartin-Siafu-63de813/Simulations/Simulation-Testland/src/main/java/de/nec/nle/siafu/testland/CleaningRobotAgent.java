package de.nec.nle.siafu.testland;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.Position;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.ICommunicationProvider;
import de.tud.swt.cleaningrobots.IPositionProvider;
import de.tud.swt.cleaningrobots.Robot;
import de.tud.swt.cleaningrobots.behaviours.DiscoverBehaviour;
import de.tud.swt.cleaningrobots.behaviours.DumpModelBehaviour;
import de.tud.swt.cleaningrobots.behaviours.MoveBehaviour;

public class CleaningRobotAgent extends Agent implements IPositionProvider, ICommunicationProvider{

	private static final int CONST_VISIONRADIUS_NEAR_ROBOTS = 10;
	private Robot cleaningRobot;
	private World siafuWorld;
	private Logger logger = LogManager.getRootLogger();
	
	public CleaningRobotAgent(Position start, String image, World world) {
		super(start, image, world);
		siafuWorld = world;
		cleaningRobot = new Robot(this, new AgentNavigationAdapter(this, siafuWorld), this);
		
		cleaningRobot.addSensor(new BlockedOnlySensor(siafuWorld, this));
		
		cleaningRobot.addBehaviour(new DumpModelBehaviour(cleaningRobot));
		cleaningRobot.addBehaviour(new MoveBehaviour(cleaningRobot));
		cleaningRobot.addBehaviour(new DiscoverBehaviour(cleaningRobot));
		this.setSpeed(1);
	}

	@Override
	public void wander() {
		cleaningRobot.action();
	}

	public Robot getCleaningRobot() {
		return this.cleaningRobot;
	}

	@Override
	public void setName(String name) {
		super.setName(name);
		this.cleaningRobot.setName(name);
	}

	@Override
	public de.tud.swt.cleaningrobots.model.Position getPosition() {
		de.tud.swt.cleaningrobots.model.Position result = new de.tud.swt.cleaningrobots.model.Position(
				this.getPos().getCol(), this.getPos().getRow());
		return result;
	}
	
	@Override
	public void setSpeed(int speed) {
		//ignore..
	}

	@Override
	public List<Robot> getNearRobots() {
		List <Robot> result = new LinkedList<Robot>(); 
		
		for (Agent nearAgent : this.siafuWorld.getPeople())
		{
			// Just in case...
			if (nearAgent instanceof CleaningRobotAgent){
				int xdiff = this.getPos().getCol() - nearAgent.getPos().getCol();
				int ydiff = this.getPos().getRow() - nearAgent.getPos().getRow();
				xdiff = xdiff < 0 ? -xdiff : xdiff;
				ydiff = ydiff < 0 ? -ydiff : ydiff;
				if (xdiff < CONST_VISIONRADIUS_NEAR_ROBOTS && ydiff < CONST_VISIONRADIUS_NEAR_ROBOTS){
					result.add(((CleaningRobotAgent) nearAgent).getCleaningRobot());
				}
			}
		}
		logger.debug(cleaningRobot + " has " + result.size() + " neighbours to communicate with...");
		return result;
	}
}

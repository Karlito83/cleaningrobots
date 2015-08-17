package de.tud.swt.testland;

import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.Position;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.ICommunicationProvider;
import de.tud.swt.cleaningrobots.IPositionProvider;
import de.tud.swt.cleaningrobots.RobotCore;

public class RobotAgent extends Agent implements IPositionProvider, ICommunicationProvider {

	//protected static final int CONST_VISIONRADIUS_NEAR_ROBOTS = 10;
	private boolean finish;
	
	protected RobotCore cleaningRobot;
	protected World siafuWorld;
	protected Logger logger = LogManager.getRootLogger();
	
	public RobotAgent(Position start, String image, World world) {		
		super(start, image, world);
				
		this.setSpeed(1);
	}	
	
	@Override
	public void wander() {
		finish = cleaningRobot.action();
	}
	
	public boolean isFinish () {
		return finish;
	}

	public RobotCore getRobot() {
		return this.cleaningRobot;
	}

	@Override
	public void setName(String name) {
		super.setName(name);
		this.cleaningRobot.setName(name);
	}
	
	@Override
	public void setSpeed(int speed) {
		//ignore..
	}

	@Override
	public List<RobotCore> getNearRobots(int visionRadius) {
		List <RobotCore> result = new LinkedList<RobotCore>(); 
		
		//System.out.println("People size: "  + this.siafuWorld.getPeople().size());
		
		for (Agent nearAgent : this.siafuWorld.getPeople())
		{
			// Just in case...
			if (nearAgent instanceof RobotAgent){
				int xdiff = this.getPos().getCol() - nearAgent.getPos().getCol();
				int ydiff = this.getPos().getRow() - nearAgent.getPos().getRow();
				//System.out.println("Col: " + this.getPos().getCol() + " : " + 
				//		nearAgent.getPos().getCol() + " Row: " + this.getPos().getRow() + 
				//		" : " + nearAgent.getPos().getRow());
				xdiff = xdiff < 0 ? -xdiff : xdiff;
				ydiff = ydiff < 0 ? -ydiff : ydiff;
				//System.out.println("xDiff: " + xdiff + " yDiff: " + ydiff);
				if (xdiff <= visionRadius && ydiff <= visionRadius){
					result.add(((RobotAgent) nearAgent).getRobot());
				}
			}
		}
		//System.out.println("Size: " + result.size());
		logger.debug(cleaningRobot + " has " + result.size() + " neighbours to communicate with...");
		return result;
	}
	
	@Override
	public List<RobotCore> getAllRobots() {
		List <RobotCore> result = new LinkedList<RobotCore>(); 
				
		for (Agent nearAgent : this.siafuWorld.getPeople())
		{
			if (nearAgent instanceof RobotAgent){
				result.add(((RobotAgent) nearAgent).getRobot());
			}
		}
		return result;
	}
	
	@Override
	public de.tud.swt.cleaningrobots.model.Position getPosition() {
		de.tud.swt.cleaningrobots.model.Position result = new de.tud.swt.cleaningrobots.model.Position(
				this.getPos().getCol(), this.getPos().getRow());
		return result;
	}
}

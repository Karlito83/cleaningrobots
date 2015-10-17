package de.tud.swt.testland;

import java.util.LinkedList;
import java.util.List;

import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.Position;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.ICommunicationProvider;
import de.tud.swt.cleaningrobots.IPositionProvider;
import de.tud.swt.cleaningrobots.RobotCore;

public class RobotAgent extends Agent implements IPositionProvider, ICommunicationProvider {

	private boolean finish;
	
	protected RobotCore cleaningRobot;
	protected World siafuWorld;
	
	public RobotAgent(Position start, String image, World world) {		
		super(start, image, world);
				
		this.setSpeed(1);
	}	
	
	@Override
	public void wander() {
		this.finish = this.cleaningRobot.action();
	}
	
	public boolean isFinish () {
		return this.finish;
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
		
		for (Agent nearAgent : this.siafuWorld.getPeople())
		{
			//If there are near Robots
			if (nearAgent instanceof RobotAgent){
				if (Math.abs(this.getPos().getCol() - nearAgent.getPos().getCol()) <= visionRadius 
						&& Math.abs(this.getPos().getRow() - nearAgent.getPos().getRow()) <= visionRadius){
					result.add(((RobotAgent) nearAgent).getRobot());
				}
			}
		}
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
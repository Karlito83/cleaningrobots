package de.tud.swt.cleaningrobots.multithreaded;

import java.util.LinkedList;
import java.util.List;

import de.nec.nle.siafu.model.MultiAgent;
import de.nec.nle.siafu.model.MultiWorld;
import de.tud.swt.cleaningrobots.ICommunicationProvider;
import de.tud.swt.cleaningrobots.IPositionProvider;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.model.Position;

public class RobotAgentMulti extends MultiAgent implements IPositionProvider, ICommunicationProvider {

	private boolean finish;
	
	protected RobotCore cleaningRobot;	
	
	public RobotAgentMulti (Position start, MultiWorld world) {
		super(start.getX(), start.getY(), world);
	}
	
	public void wander() {
		this.finish = this.cleaningRobot.action();
	}
	
	public boolean isFinish () {
		return this.finish;
	}

	public RobotCore getRobot() {
		return this.cleaningRobot;
	}

	public void setName(String name) {
		this.name = name;
		this.cleaningRobot.setName(name);
	}

	@Override
	public List<RobotCore> getNearRobots(int visionRadius) {
		List <RobotCore> result = new LinkedList<RobotCore>(); 
		
		for (MultiAgent nearAgent : this.siafuWorld.getPeople())
		{
			//If there are near Robots
			if (Math.abs(this.getCol() - nearAgent.getCol()) <= visionRadius 
					&& Math.abs(this.getRow() - nearAgent.getRow()) <= visionRadius){
				result.add(((RobotAgentMulti)nearAgent).getRobot());
			}
		}
		return result;
	}
	
	@Override
	public List<RobotCore> getAllRobots() {
		List <RobotCore> result = new LinkedList<RobotCore>(); 
				
		for (MultiAgent nearAgent : this.siafuWorld.getPeople())
		{
			result.add(((RobotAgentMulti)nearAgent).getRobot());
		}
		return result;
	}
	
	@Override
	public Position getPosition() {
		return new Position(getCol(), getRow());
	}
}

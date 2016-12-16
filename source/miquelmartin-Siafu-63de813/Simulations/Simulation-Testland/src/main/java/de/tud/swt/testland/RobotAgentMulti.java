package de.tud.swt.testland;

import java.util.LinkedList;
import java.util.List;

import de.nec.nle.siafu.model.MultiAgent;
import de.nec.nle.siafu.model.MultiWorld;
import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.ICommunicationAdapter;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.hardware.Accu;
import de.tud.swt.cleaningrobots.model.Position;

/**
 * Without user interface in Siafu.
 * The Robotagent can work with the objects in Siafu and represents the Position in the map.
 * 
 * @author Christopher Werner
 *
 */
public class RobotAgentMulti extends MultiAgent implements ICommunicationAdapter, IRobotAgent {

	private boolean finish;
	
	protected RobotCore cleaningRobot;	
	
	public RobotAgentMulti (String name, Position start, MultiWorld world, Configuration configuration) {
		super(name, start.getX(), start.getY(), world);
		
		this.cleaningRobot = new RobotCore(name, this, new Accu(48.0), configuration);
	}
	
	public void wander() {
		this.finish = this.cleaningRobot.action();
	}
	
	/**
	 * Return if it is finished.
	 * @return
	 */
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
	public void setPosition(Position position) {
		setCol(position.getX());
		setRow(position.getY());		
	}

	@Override
	public boolean isWall(int row, int col) {
		return siafuWorld.isAWall(row, col);
	}
	
	@Override
	public Position getPosition() {
		return new Position(getCol(), getRow());
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
	public void setSpeed(int speed) {
		// do nothing		
	}
}

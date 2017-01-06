package de.tud.swt.testland;

import java.util.LinkedList;
import java.util.List;

import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.Position;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.ISimulatorAdapter;
import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.hardware.Accu;

/**
 * With user interface in Siafu.
 * The Robotagent can work with the objects in Siafu and represents the Position in the map.
 * 
 * @author Christopher Werner
 *
 */
public class SiafuAgent extends Agent implements ISimulatorAdapter, ISimulatorAgent {

	private boolean finish;	
	protected AgentCore cleaningRobot;
	private World siafuWorld;
	
	public SiafuAgent(String name, Position start, String image, World world, Configuration configuration) {		
		super(name, start, image, world);
		
		this.cleaningRobot = new AgentCore(name, this, new Accu(48.0), configuration);
		this.siafuWorld = world;
	}	
		
	@Override
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

	public AgentCore getRobot() {
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
	public List<AgentCore> getNearRobots(int visionRadius) {
		List <AgentCore> result = new LinkedList<AgentCore>(); 
		
		for (Agent nearAgent : this.siafuWorld.getPeople())
		{
			//If there are near Robots
			if (nearAgent instanceof SiafuAgent){
				if (Math.abs(this.getPos().getCol() - nearAgent.getPos().getCol()) <= visionRadius 
						&& Math.abs(this.getPos().getRow() - nearAgent.getPos().getRow()) <= visionRadius){
					result.add(((SiafuAgent) nearAgent).getRobot());
				}
			}
		}
		return result;
	}
	
	@Override
	public List<AgentCore> getAllRobots() {
		List <AgentCore> result = new LinkedList<AgentCore>(); 
				
		for (Agent nearAgent : this.siafuWorld.getPeople())
		{
			if (nearAgent instanceof SiafuAgent){
				result.add(((SiafuAgent) nearAgent).getRobot());
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
	
	@Override
	public void setPosition(de.tud.swt.cleaningrobots.model.Position position) {
		Position siafuPosition = new Position(position.getY(), position.getX());
		setPos(siafuPosition);
	}

	@Override
	public boolean isWall(int row, int col) {
		return siafuWorld.isAWall(new de.nec.nle.siafu.model.Position(row, col));
	}
}

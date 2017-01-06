package de.tud.swt.cleaningrobots;

import java.util.List;

import de.tud.swt.cleaningrobots.model.Position;

/**
 * Creates the interface between the simulator and the robot core.
 * 
 * @author Christopher Werner
 *
 */
public interface ISimulatorAdapter {

	/**
	 * Set the postion of the robot in the simulation.
	 * @param position
	 */
	public void setPosition(Position position);
	
	/**
	 * Proof if the position is passable or not.
	 * @param row
	 * @param col
	 * @return
	 */
	public boolean isWall(int row, int col);
	
	/**
	 * Get the position of the robot from the simulation.
	 * @return
	 */
	public Position getPosition();
	
	/**
	 * Get all robot cores which are in a specific area.
	 * @param visionRadius
	 * @return
	 */
	public List<AgentCore> getNearRobots(int visionRadius);
	
	/**
	 * Get all robot core from the test case.
	 * @return
	 */
	public List<AgentCore> getAllRobots();
}

package de.tud.swt.cleaningrobots.multithreaded;

import java.util.ArrayList;

import de.nec.nle.siafu.model.MultiAgent;
import de.nec.nle.siafu.model.MultiWorld;
import de.tud.evaluation.WorkingConfiguration;
import de.tud.swt.cleaningrobots.model.Position;

public abstract class RobotFactoryMulti {
	
	private int counter;
	protected WorkingConfiguration configuration;
	private int row;
	private int col;
	
	public RobotFactoryMulti (WorkingConfiguration configuration) {
		this.configuration = configuration;
		this.col = 139;
		this.row = 133;
		counter = 0;
	}
	
	/**
	 * Create a loadstation agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	protected LoadstationAgentMulti createLoadStationAgent(MultiWorld world) {
		LoadstationAgentMulti agent = new LoadstationAgentMulti(new Position(col,row), world, configuration);

		counter++;
		agent.setName("Robbi_" + counter);
		
		return agent;
	}
	
	/**
	 * Create a explore agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	protected ExploreRobotAgentMulti createExploreAgent(MultiWorld world) {
		ExploreRobotAgentMulti agent = new ExploreRobotAgentMulti(new Position(col,row), world, configuration);

		counter++;
		agent.setName("Robbi_" + counter);

		return agent;
	}	
	
	/**
	 * Create a wipe agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	protected WipeRobotAgentMulti createWipeAgent(MultiWorld world) {
		WipeRobotAgentMulti agent = new WipeRobotAgentMulti(new Position(col,row), world, configuration);

		counter++;
		agent.setName("Robbi_" + counter);

		return agent;
	}
	
	/**
	 * Create a hoove agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	protected HooveRobotAgentMulti createHooveAgent(MultiWorld world) {
		HooveRobotAgentMulti agent = new HooveRobotAgentMulti(new Position(col,row), world, configuration);

		counter++;
		agent.setName("Robbi_" + counter);

		return agent;
	}
		
	/**
	 * Create a number of random agents.
	 * 
	 * @param world
	 *            the world where the agents will dwell
	 * @return an ArrayList with the created agents
	 */
	public abstract ArrayList<MultiAgent> createRobots(MultiWorld world);

}

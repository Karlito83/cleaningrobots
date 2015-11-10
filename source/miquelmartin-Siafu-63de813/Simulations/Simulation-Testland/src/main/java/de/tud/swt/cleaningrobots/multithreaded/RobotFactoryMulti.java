package de.tud.swt.cleaningrobots.multithreaded;

import java.util.ArrayList;

import de.nec.nle.siafu.model.MultiAgent;
import de.nec.nle.siafu.model.MultiWorld;
import de.tud.evaluation.WorkingConfiguration;
import de.tud.swt.cleaningrobots.model.Position;

public abstract class RobotFactoryMulti {
	
	private int counter;
	protected WorkingConfiguration configuration;
	private Position position;
	
	public RobotFactoryMulti (WorkingConfiguration configuration) {
		this.configuration = configuration;
		//X: 139 Y: 133 Rechteckig
		//X: 199 Y: 206 Fakultät
		switch (configuration.map) {
			case 0:  this.position = new Position(139, 133);
					 break;
	        case 1:  this.position = new Position(139, 133);
	                 break;	            
	        case 2:  this.position = new Position(199, 206);
	                 break;
	        case 3:  this.position = new Position(199, 206);
	                 break;
	        default: this.position = new Position(139, 133);
	                 break;
		}
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
		LoadstationAgentMulti agent = new LoadstationAgentMulti(position, world, configuration);

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
		ExploreRobotAgentMulti agent = new ExploreRobotAgentMulti(position, world, configuration);

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
		WipeRobotAgentMulti agent = new WipeRobotAgentMulti(position, world, configuration);

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
		HooveRobotAgentMulti agent = new HooveRobotAgentMulti(position, world, configuration);

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
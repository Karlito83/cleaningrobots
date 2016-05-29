package de.tud.swt.testland;

import java.util.ArrayList;

import de.nec.nle.siafu.exceptions.PlaceNotFoundException;
import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.Configuration;

/**
 * With user interface in Siafu.
 * Create the different robot type and create the different test case only abstract class.
 * 
 * @author Christopher Werner
 *
 */
public abstract class RobotFactory {
	
	private int counter;
	protected Configuration configuration;
	
	public RobotFactory (Configuration configuration) {
		this.configuration = configuration;
		counter = 0;
	}
	
	/**
	 * Create only a loadstation without rechner.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	protected OnlyLoadStationAgent createLoadStation(final World world) {
		try {
			counter++;
			
			OnlyLoadStationAgent agent = new OnlyLoadStationAgent("Robbi_" + counter, world
					.getRandomPlaceOfType("Center").getPos(), "Master",
					world, configuration);

			return agent;
		} catch (PlaceNotFoundException e) {
			throw new RuntimeException(
					"You didn't define the right type of places", e);
		}
	}
	
	/**
	 * Create a loadstation agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	protected LoadStationAgent createLoadStationAgent(final World world) {
		try {
			counter++;
			
			LoadStationAgent agent = new LoadStationAgent("Robbi_" + counter, world
					.getRandomPlaceOfType("Center").getPos(), "Master",
					world, configuration);
			
			return agent;
		} catch (PlaceNotFoundException e) {
			throw new RuntimeException(
					"You didn't define the right type of places", e);
		}
	}
	
	/**
	 * Create a explore agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	protected ExploreRobotAgent createExploreAgent(final World world) {
		try {
			counter++;
			
			ExploreRobotAgent agent = new ExploreRobotAgent("Robbi_" + counter, world
					.getRandomPlaceOfType("Center").getPos(), "HumanMagenta",
					world, configuration);

			return agent;
		} catch (PlaceNotFoundException e) {
			throw new RuntimeException(
					"You didn't define the right type of places", e);
		}
	}	
	
	/**
	 * Create a wipe agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	protected WipeRobotAgent createWipeAgent(final World world) {
		try {
			counter++;
			
			WipeRobotAgent agent = new WipeRobotAgent("Robbi_" + counter, world
					.getRandomPlaceOfType("Center").getPos(), "HumanYellow",
					world, configuration);

			return agent;
		} catch (PlaceNotFoundException e) {
			throw new RuntimeException(
					"You didn't define the right type of places", e);
		}
	}
	
	/**
	 * Create a hoove agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	protected HooveRobotAgent createHooveAgent(final World world) {
		try {
			counter++;
			
			HooveRobotAgent agent = new HooveRobotAgent("Robbi_" + counter, world
					.getRandomPlaceOfType("Center").getPos(), "HumanGreen",
					world, configuration);

			return agent;
		} catch (PlaceNotFoundException e) {
			throw new RuntimeException(
					"You didn't define the right type of places", e);
		}
	}
		
	/**
	 * Create a number of random agents.
	 * 
	 * @param world
	 *            the world where the agents will dwell
	 * @return an ArrayList with the created agents
	 */
	public abstract ArrayList<Agent> createRobots(World world);

}

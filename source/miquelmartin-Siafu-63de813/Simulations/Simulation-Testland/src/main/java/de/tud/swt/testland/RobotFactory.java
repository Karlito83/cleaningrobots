package de.tud.swt.testland;

import java.util.ArrayList;

import de.nec.nle.siafu.exceptions.PlaceNotFoundException;
import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.World;

public abstract class RobotFactory {
	
	private int counter;
	
	public RobotFactory () {
		counter = 0;
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

			LoadStationAgent agent = new LoadStationAgent(world
					.getRandomPlaceOfType("Center").getPos(), "Master",
					world);

			counter++;
			agent.setName("Robbi_" + counter);
			
			return agent;
		} catch (PlaceNotFoundException e) {
			throw new RuntimeException(
					"You didn't define the \"Nowhere\" type of places", e);
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

			ExploreRobotAgent agent = new ExploreRobotAgent(world
					.getRandomPlaceOfType("Center").getPos(), "HumanMagenta",
					world);

			counter++;
			agent.setName("Robbi_" + counter);

			return agent;
		} catch (PlaceNotFoundException e) {
			throw new RuntimeException(
					"You didn't define the \"Center\" type of places", e);
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

			WipeRobotAgent agent = new WipeRobotAgent(world
					.getRandomPlaceOfType("Center").getPos(), "HumanYellow",
					world);

			counter++;
			agent.setName("Robbi_" + counter);

			return agent;
		} catch (PlaceNotFoundException e) {
			throw new RuntimeException(
					"You didn't define the \"Center\" type of places", e);
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

			HooveRobotAgent agent = new HooveRobotAgent(world
					.getRandomPlaceOfType("Center").getPos(), "HumanGreen",
					world);

			counter++;
			agent.setName("Robbi_" + counter);

			return agent;
		} catch (PlaceNotFoundException e) {
			throw new RuntimeException(
					"You didn't define the \"Center\" type of places", e);
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

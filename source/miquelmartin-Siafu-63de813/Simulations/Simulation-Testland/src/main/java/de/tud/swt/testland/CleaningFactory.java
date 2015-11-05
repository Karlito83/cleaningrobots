package de.tud.swt.testland;

import java.util.ArrayList;

import de.nec.nle.siafu.exceptions.PlaceNotFoundException;
import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.World;
import de.tud.evaluation.WorkingConfiguration;

public class CleaningFactory extends RobotFactory {

	private int counter;
	private int amount;
	
	public CleaningFactory (WorkingConfiguration configuration)
	{
		super(configuration);
		counter = 0;
		amount = 3;
	}
	
	/**
	 * Create a random agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	private Agent createRandomAgent(final World world) {
		try {

			RobotAgent agent = new RobotAgent(world
					.getRandomPlaceOfType("Nowhere").getPos(), "HumanMagenta",
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
	 * Create a number of random agents.
	 * 
	 * @param world
	 *            the world where the agents will dwell
	 * @return an ArrayList with the created agents
	 */
	@Override
	public ArrayList<Agent> createRobots(World world) {
		ArrayList<Agent> population = new ArrayList<Agent>(amount);
		for (int i = 0; i < amount; i++) {
			population.add(createRandomAgent(world));
		}
		return population;
	}
}

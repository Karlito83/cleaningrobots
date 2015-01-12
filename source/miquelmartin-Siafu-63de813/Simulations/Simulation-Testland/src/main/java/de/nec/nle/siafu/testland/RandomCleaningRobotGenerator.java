package de.nec.nle.siafu.testland;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import cleaningrobots.State;
import de.nec.nle.siafu.exceptions.PlaceNotFoundException;
import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.Overlay;
import de.nec.nle.siafu.model.World;
import de.nec.nle.siafu.types.Publishable;

public class RandomCleaningRobotGenerator {
	/**
	 * A random number generator.
	 */
	private static Random rand = new Random();
	private static int counter = 0;

	private static State stateClean, stateDirty, stateFree, stateBlocked,
			stateWet, stateDry;

	public RandomCleaningRobotGenerator() {
		/*
		 * stateClean = CleaningrobotsFactory.eINSTANCE.createState();
		 * stateDirty = CleaningrobotsFactory.eINSTANCE.createState(); stateFree
		 * = CleaningrobotsFactory.eINSTANCE.createState(); stateBlocked =
		 * CleaningrobotsFactory.eINSTANCE.createState(); stateDry =
		 * CleaningrobotsFactory.eINSTANCE.createState(); stateWet =
		 * CleaningrobotsFactory.eINSTANCE.createState();
		 * 
		 * stateClean.setName("Clean"); stateDirty.setName("Dirty");
		 * stateFree.setName("Free"); stateBlocked.setName("Blocked");
		 * stateDry.setName("Dry"); stateWet.setName("Wet");
		 * 
		 * stateDirty.getTransition().add(stateClean);
		 * stateWet.getTransition().add(stateDry);
		 */
	}

	/**
	 * Create a random agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	public Agent createRandomAgent(final World world) {
		try {

			CleaningRobotAgent agent = new CleaningRobotAgent(world
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
	 * @param amount
	 *            the amount of agents to create
	 * @param world
	 *            the world where the agents will dwell
	 * @return an ArrayList with the created agents
	 */
	public ArrayList<Agent> createRandomPopulation(final int amount,
			final World world) {
		ArrayList<Agent> population = new ArrayList<Agent>(amount);
		for (int i = 0; i < amount; i++) {
			population.add(createRandomAgent(world));
		}
		return population;
	}

	/**
	 * Create a random info field for the agents. In this case, the field's
	 * empty.
	 * 
	 * @param world
	 *            the world the agent lives in
	 * @return the info field
	 */
	public Map<String, Publishable> createRandomInfo(final World world) {
		Map<String, Publishable> info = new HashMap<String, Publishable>();
		return info;
	}

}

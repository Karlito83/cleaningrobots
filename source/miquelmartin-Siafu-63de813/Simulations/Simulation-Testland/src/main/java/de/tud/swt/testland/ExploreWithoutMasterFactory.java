package de.tud.swt.testland;

import java.util.ArrayList;

import de.nec.nle.siafu.exceptions.PlaceNotFoundException;
import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.World;

public class ExploreWithoutMasterFactory extends RobotFactory {

	private int counter;
	
	public ExploreWithoutMasterFactory ()
	{
		Constants.configuration = 0;
		Constants.NEW_FIELD_COUNT = 0;
		counter = 0;
	}
	
	/**
	 * Create a loadstation agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	private LoadStationAgent createLoadStationAgent(final World world) {
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
	private ExploreRobotAgent createExploreAgent(final World world) {
		try {

			ExploreRobotAgent agent = new ExploreRobotAgent(world
					.getRandomPlaceOfType("Center").getPos(), "HumanMagenta",
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
		
		ArrayList<Agent> population = new ArrayList<Agent>();	
		
		//loadstation agent
		LoadStationAgent lsa = createLoadStationAgent(world);
		lsa.addLoadGoal();
		population.add(lsa);
		
		//explore agents
		for (int i = 0; i < Constants.NUMBER_EXPLORE_AGENTS; i++) {
			ExploreRobotAgent era = createExploreAgent(world);
			era.addRonnyConfiguration();
			population.add(era);
		}
		
		if (Constants.NUMBER_EXPLORE_AGENTS > 0) {
			for (int i = 0; i < Constants.NUMBER_HOOVE_AGENTS; i++) {
				
			}
		}
		
		if (Constants.NUMBER_HOOVE_AGENTS > 0) {
			for (int i = 0; i < Constants.NUMBER_WIPE_AGENTS; i++) {
				
			}
		}
		
		return population;
	}
}

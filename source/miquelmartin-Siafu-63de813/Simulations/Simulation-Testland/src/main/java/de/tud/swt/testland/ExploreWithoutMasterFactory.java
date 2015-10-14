package de.tud.swt.testland;

import java.util.ArrayList;

import de.nec.nle.siafu.exceptions.PlaceNotFoundException;
import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.World;
import de.tud.evaluation.EvaluationConstants;

public class ExploreWithoutMasterFactory extends RobotFactory {

	private int counter;
	
	public ExploreWithoutMasterFactory ()
	{
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
	 * Create a wipe agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	private WipeRobotAgent createWipeAgent(final World world) {
		try {

			WipeRobotAgent agent = new WipeRobotAgent(world
					.getRandomPlaceOfType("Center").getPos(), "HumanYellow",
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
	 * Create a hoove agent.
	 * 
	 * @param world
	 *            the world to create it in
	 * @return the new agent
	 */
	private HooveRobotAgent createHooveAgent(final World world) {
		try {

			HooveRobotAgent agent = new HooveRobotAgent(world
					.getRandomPlaceOfType("Center").getPos(), "HumanGreen",
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
		lsa.addLoadIfRobotWantGoal();
		population.add(lsa);
		
		if (EvaluationConstants.NUMBER_EXPLORE_AGENTS > 0) {
			
			//explore agents
			for (int i = 0; i < EvaluationConstants.NUMBER_EXPLORE_AGENTS; i++) {
				ExploreRobotAgent era = createExploreAgent(world);
				era.addWithoutMasterConfiguration();
				population.add(era);
			}
			
			if (EvaluationConstants.NUMBER_HOOVE_AGENTS > 0) {
				
				//hoove agents
				for (int i = 0; i < EvaluationConstants.NUMBER_HOOVE_AGENTS; i++) {
					HooveRobotAgent hra = createHooveAgent(world);
					hra.addWithoutMasterConfiguration();
					population.add(hra);
				}
				
				if (EvaluationConstants.NUMBER_WIPE_AGENTS > 0) {
					
					//wipe agents
					for (int i = 0; i < EvaluationConstants.NUMBER_WIPE_AGENTS; i++) {
						WipeRobotAgent wra = createWipeAgent(world);
						wra.addWithoutMasterConfiguration();
						population.add(wra);
					}
				}
			}			
		}
		
		return population;
	}
}

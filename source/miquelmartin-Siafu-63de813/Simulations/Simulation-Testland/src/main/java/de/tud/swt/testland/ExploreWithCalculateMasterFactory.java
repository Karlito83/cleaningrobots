package de.tud.swt.testland;

import java.util.ArrayList;

import de.nec.nle.siafu.exceptions.PlaceNotFoundException;
import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.FollowerRole;
import de.tud.swt.cleaningrobots.MasterRole;

public class ExploreWithCalculateMasterFactory extends RobotFactory {

	private int counter;
	
	public ExploreWithCalculateMasterFactory ()
	{
		Constants.configuration = 3;
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
		population.add(lsa);
		
		MasterRole mre = new MasterRole(lsa.getRobot());
		mre.addRole(mre);
		
		lsa.addMasterMerge(mre);
		lsa.addStandardGoals();

		//explore agents
		for (int i = 0; i < Constants.NUMBER_EXPLORE_AGENTS; i++) {
			ExploreRobotAgent era = createExploreAgent(world);
			era.addNonRelativeStandardGoals();
			population.add(era);		
			
			FollowerRole fre = new FollowerRole(era.getRobot());
			fre.addRole(fre);
			fre.master = mre;
			mre.getFollowers().add(fre);
		}
		
		/*//E1
		ExploreRobotAgent era1 = createExploreAgent(world);
		era1.addNonRelativeStandardGoals();
		population.add(era1);		
		
		FollowerRole fre1 = new FollowerRole(era1.getRobot());
		fre1.addRole(fre1);
		fre1.master = mre;
		mre.getFollowers().add(fre1);
		
		//E2
		ExploreRobotAgent era2 = createExploreAgent(world);
		era2.addNonRelativeStandardGoals();
		population.add(era2);
		
		FollowerRole fre2 = new FollowerRole(era2.getRobot());
		fre2.addRole(fre2);
		fre2.master = mre;
		mre.getFollowers().add(fre2);
		
		//E3
		ExploreRobotAgent era3 = createExploreAgent(world);
		era3.addNonRelativeStandardGoals();
		population.add(era3);
		
		FollowerRole fre3 = new FollowerRole(era3.getRobot());
		fre3.addRole(fre3);
		fre3.master = mre;
		mre.getFollowers().add(fre3);*/
		
		for (Agent a: population)
		{
			RobotAgent ra = (RobotAgent) a;
			System.out.println("Name: " + ra.cleaningRobot.getName() + " Roles: " + ra.cleaningRobot.getRoles());
		}
		return population;
	}
}

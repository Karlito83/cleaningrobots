package de.tud.swt.testland;

import java.util.ArrayList;

import de.nec.nle.siafu.exceptions.PlaceNotFoundException;
import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.World;
import de.tud.evaluation.EvaluationConstants;
import de.tud.swt.cleaningrobots.FollowerRole;
import de.tud.swt.cleaningrobots.MasterRole;

public class MasterExploreFactory extends RobotFactory {

	private int counter;
	private boolean relative;
	
	public MasterExploreFactory ()
	{
		EvaluationConstants.NEW_FIELD_COUNT = 0;
		this.counter = 0;
		this.relative = false;
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
		population.add(lsa);
		
		if (EvaluationConstants.NUMBER_EXPLORE_AGENTS > 0) {
			MasterRole mre = new MasterRole(lsa.getRobot());
			mre.addRole(mre);			
			lsa.addMasterExploreGoals(mre, relative);
			
			//explore agents
			for (int i = 0; i < EvaluationConstants.NUMBER_EXPLORE_AGENTS; i++) {
				ExploreRobotAgent era = createExploreAgent(world);
				era.addMasterExploreGoals();
				population.add(era);		
				
				FollowerRole fre = new FollowerRole(era.getRobot());
				fre.addRole(fre);
				fre.master = mre;
				mre.getFollowers().add(fre);
			}
			
			if (EvaluationConstants.NUMBER_HOOVE_AGENTS > 0) {
				MasterRole mrh = new MasterRole(lsa.getRobot());
				mrh.addRole(mrh);
				lsa.addMasterHooveGoal(mrh, relative);
				
				//hoove agents
				for (int i = 0; i < EvaluationConstants.NUMBER_HOOVE_AGENTS; i++) {
					HooveRobotAgent hra = createHooveAgent(world);
					hra.addMasterHooveGoals();
					population.add(hra);
					
					FollowerRole frh = new FollowerRole(hra.getRobot());
					frh.addRole(frh);
					frh.master = mrh;
					mrh.getFollowers().add(frh);
				}
								
				if (EvaluationConstants.NUMBER_WIPE_AGENTS > 0) {
					MasterRole mrw = new MasterRole(lsa.getRobot());
					mrw.addRole(mrw);
					lsa.addMasterWipeGoal(mrw, relative);
					
					//wipe agents
					for (int i = 0; i < EvaluationConstants.NUMBER_WIPE_AGENTS; i++) {
						WipeRobotAgent wra = createWipeAgent(world);
						wra.addMasterWipeGoals();
						population.add(wra);
						
						FollowerRole frw = new FollowerRole(wra.getRobot());
						frw.addRole(frw);
						frw.master = mrw;
						mrw.getFollowers().add(frw);
					}
				}
				
			}			
			lsa.addLoadIfRobotWantAndExploreGoal();
		}		
				
		for (Agent a: population)
		{
			RobotAgent ra = (RobotAgent) a;
			System.out.println("Name: " + ra.cleaningRobot.getName() + " Roles: " + ra.cleaningRobot.getRoles());
		}
		return population;
	}
}

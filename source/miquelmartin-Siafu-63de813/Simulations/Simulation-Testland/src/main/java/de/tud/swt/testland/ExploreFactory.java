package de.tud.swt.testland;

import java.util.ArrayList;

import de.nec.nle.siafu.exceptions.PlaceNotFoundException;
import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.FollowerRole;
import de.tud.swt.cleaningrobots.MasterRole;

public class ExploreFactory extends RobotFactory {

	private int counter;
	
	public ExploreFactory ()
	{
		Constants.configuration = 4;
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
		population.add(lsa);
		
		//MasterRole mrw = new MasterRole(lsa.getRobot());
		//mrw.addRole(mrw);
		
		//lsa.addMasterMerge(mrh);
		lsa.addStandardGoals();
		
		if (Constants.NUMBER_EXPLORE_AGENTS > 0) {
			MasterRole mre = new MasterRole(lsa.getRobot());
			mre.addRole(mre);			
			lsa.addMasterMerge(mre);
			//explore agents
			for (int i = 0; i < Constants.NUMBER_EXPLORE_AGENTS; i++) {
				ExploreRobotAgent era = createExploreAgent(world);
				era.addStandardGoals();
				population.add(era);		
				
				FollowerRole fre = new FollowerRole(era.getRobot());
				fre.addRole(fre);
				fre.master = mre;
				mre.getFollowers().add(fre);
			}
		
			//hoove agents
			if (Constants.NUMBER_HOOVE_AGENTS > 0) {
				MasterRole mrh = new MasterRole(lsa.getRobot());
				mrh.addRole(mrh);
				lsa.addMasterMerge(mrh);
				for (int i = 0; i < Constants.NUMBER_HOOVE_AGENTS; i++) {
					HooveRobotAgent hra = createHooveAgent(world);
					hra.addStandardGoals();
					population.add(hra);
					
					FollowerRole frh = new FollowerRole(hra.getRobot());
					frh.addRole(frh);
					frh.master = mrh;
					mrh.getFollowers().add(frh);
				}
				mrh.getFollowers().add(mre);
								
				//wipe agents
				if (Constants.NUMBER_WIPE_AGENTS > 0) {
					MasterRole mrw = new MasterRole(lsa.getRobot());
					mrw.addRole(mrw);
					lsa.addMasterMerge(mrw);
					for (int i = 0; i < Constants.NUMBER_WIPE_AGENTS; i++) {
						WipeRobotAgent wra = createWipeAgent(world);
						wra.addStandardGoals();
						population.add(wra);
						
						FollowerRole frw = new FollowerRole(wra.getRobot());
						frw.addRole(frw);
						frw.master = mrw;
						mrw.getFollowers().add(frw);
					}
					mrw.getFollowers().add(mrh);
				}
				
			}
		}
		
		/*//E1
		ExploreRobotAgent era1 = createExploreAgent(world);
		era1.addStandardGoals();
		population.add(era1);		
		
		FollowerRole fre1 = new FollowerRole(era1.getRobot());
		fre1.addRole(fre1);
		fre1.master = mre;
		mre.getFollowers().add(fre1);
		
		//E2
		ExploreRobotAgent era2 = createExploreAgent(world);
		era2.addStandardGoals();
		population.add(era2);
		
		FollowerRole fre2 = new FollowerRole(era2.getRobot());
		fre2.addRole(fre2);
		fre2.master = mre;
		mre.getFollowers().add(fre2);
		
		//E3
		ExploreRobotAgent era3 = createExploreAgent(world);
		era3.addStandardGoals();
		population.add(era3);
		
		FollowerRole fre3 = new FollowerRole(era3.getRobot());
		fre3.addRole(fre3);
		fre3.master = mre;
		mre.getFollowers().add(fre3);*/
		
		//hoove agents
		/*HooveRobotAgent hra = createHooveAgent(world);
		hra.addStandardGoals();
		population.add(hra);
		
		FollowerRole frh1 = new FollowerRole(hra.getRobot());
		frh1.addRole(frh1);
		frh1.master = mrh;
		mrh.getFollowers().add(frh1);
		mrh.getFollowers().add(mre);*/
		
		//wipe agents
		/*WipeRobotAgent wra = createWipeAgent(world);
		wra.addStandardGoals();
		population.add(wra);
		
		FollowerRole frw1 = new FollowerRole(wra.getRobot());
		frw1.addRole(frw1);
		frw1.master = mrw;
		mrw.getFollowers().add(frw1);
		mrw.getFollowers().add(mrh);*/
		
		/*for (int i = 0; i < amount; i++) {
			population.add(createExploreAgent(world));
		}
		population.add(createHooveAgent(world));*/
		for (Agent a: population)
		{
			RobotAgent ra = (RobotAgent) a;
			System.out.println("Name: " + ra.cleaningRobot.getName() + " Roles: " + ra.cleaningRobot.getRoles());
		}
		//population.add(createWipeAgent(world));
		return population;
	}

}

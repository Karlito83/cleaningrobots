package de.tud.swt.testland;

import java.util.ArrayList;

import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.World;
import de.tud.evaluation.WorkingConfiguration;

public class ExploreWithoutMasterFactory extends RobotFactory {
	
	public ExploreWithoutMasterFactory (WorkingConfiguration configuration)
	{
		super(configuration);
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
		
		boolean proof;
		
		if (configuration.number_explore_agents > 0) {
			
			proof = false;
			if (configuration.number_hoove_agents > 0) {
				proof = true;
			}
			
			//explore agents
			for (int i = 0; i < configuration.number_explore_agents; i++) {
				ExploreRobotAgent era = createExploreAgent(world);
				era.addWithoutMasterConfiguration();
				if (proof) {
					era.addWlanOnGoal();
					proof = false;
				}
				population.add(era);
			}
			
			if (configuration.number_hoove_agents > 0) {
				
				proof = false;
				if (configuration.number_wipe_agents > 0) {
					proof = true;
				}
				
				//hoove agents
				for (int i = 0; i < configuration.number_hoove_agents; i++) {
					HooveRobotAgent hra = createHooveAgent(world);
					hra.addWithoutMasterConfiguration();
					if (proof) {
						hra.addWlanOnGoal();
						proof = false;
					}
					population.add(hra);
				}
				
				if (configuration.number_wipe_agents > 0) {
					
					//wipe agents
					for (int i = 0; i < configuration.number_wipe_agents; i++) {
						WipeRobotAgent wra = createWipeAgent(world);
						wra.addWithoutMasterConfiguration();
						population.add(wra);
					}
				}
			}			
		}
		
		//example output for master follower relation
		for (Agent a: population)
		{
			RobotAgent ra = (RobotAgent) a;
			System.out.println("Name: " + ra.cleaningRobot.getName() + " Roles: " + ra.cleaningRobot.getRoles() + " States: " + ra.cleaningRobot.getSupportedStates());
		}
		
		return population;
	}
}

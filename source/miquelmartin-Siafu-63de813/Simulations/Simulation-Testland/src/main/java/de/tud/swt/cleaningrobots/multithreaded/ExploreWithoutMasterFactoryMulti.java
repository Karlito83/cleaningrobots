package de.tud.swt.cleaningrobots.multithreaded;

import java.util.ArrayList;

import de.nec.nle.siafu.model.MultiAgent;
import de.nec.nle.siafu.model.MultiWorld;
import de.tud.evaluation.WorkingConfiguration;

public class ExploreWithoutMasterFactoryMulti extends RobotFactoryMulti {
	
	public ExploreWithoutMasterFactoryMulti (WorkingConfiguration configuration)
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
	public ArrayList<MultiAgent> createRobots(MultiWorld world) {
		
		ArrayList<MultiAgent> population = new ArrayList<MultiAgent>();	
		
		//loadstation agent
		LoadStationMulti lsa = createLoadStation(world);
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
				ExploreRobotAgentMulti era = createExploreAgent(world);
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
					HooveRobotAgentMulti hra = createHooveAgent(world);
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
						WipeRobotAgentMulti wra = createWipeAgent(world);
						wra.addWithoutMasterConfiguration();
						population.add(wra);
					}
				}
			}			
		}
		
		//example output for master follower relation
		for (MultiAgent a: population)
		{
			RobotAgentMulti ra = (RobotAgentMulti) a;
			System.out.println("Name: " + ra.cleaningRobot.getName() + " Roles: " + ra.cleaningRobot.getRoles() + " States: " + ra.cleaningRobot.getSupportedStates());
		}
		
		return population;
	}
}

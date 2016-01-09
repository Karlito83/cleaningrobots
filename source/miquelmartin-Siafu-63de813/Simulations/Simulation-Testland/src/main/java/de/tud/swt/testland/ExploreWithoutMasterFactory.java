package de.tud.swt.testland;

import java.util.ArrayList;

import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.Configuration;

/**
 * With user interface from Siafu. 
 * Create the robots with the specific goals for the test case without a master robot 
 * and merge data between them.
 * 
 * @author Christopher Werner
 *
 */
public class ExploreWithoutMasterFactory extends RobotFactory {
	
	public ExploreWithoutMasterFactory (Configuration configuration)
	{
		super(configuration);
	}
	
	/**
	 * Create the number of agents from the working configuration.
	 * 
	 * @param world
	 *            the world where the agents will work
	 * @return an ArrayList with the created agents
	 */
	@Override
	public ArrayList<Agent> createRobots(World world) {
		
		ArrayList<Agent> population = new ArrayList<Agent>();	
		
		//load station agent
		OnlyLoadStationAgent lsa = createLoadStation(world);
		lsa.addLoadIfRobotWantGoal();
		population.add(lsa);
		
		boolean proof;
		
		if (configuration.wc.number_explore_agents > 0) {
			
			proof = false;
			if (configuration.wc.number_hoove_agents > 0) {
				proof = true;
			}
			
			//explore agents
			for (int i = 0; i < configuration.wc.number_explore_agents; i++) {
				ExploreRobotAgent era = createExploreAgent(world);
				era.addWithoutMasterConfiguration();
				if (proof) {
					era.addWlanOnGoal();
					proof = false;
				}
				population.add(era);
			}
			
			if (configuration.wc.number_hoove_agents > 0) {
				
				proof = false;
				if (configuration.wc.number_wipe_agents > 0) {
					proof = true;
				}
				
				//hoove agents
				for (int i = 0; i < configuration.wc.number_hoove_agents; i++) {
					HooveRobotAgent hra = createHooveAgent(world);
					hra.addWithoutMasterConfiguration();
					if (proof) {
						hra.addWlanOnGoal();
						proof = false;
					}
					population.add(hra);
				}
				
				if (configuration.wc.number_wipe_agents > 0) {
					
					//wipe agents
					for (int i = 0; i < configuration.wc.number_wipe_agents; i++) {
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

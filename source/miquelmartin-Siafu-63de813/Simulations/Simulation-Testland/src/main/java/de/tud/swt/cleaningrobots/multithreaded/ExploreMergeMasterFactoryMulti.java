package de.tud.swt.cleaningrobots.multithreaded;

import java.util.ArrayList;

import de.nec.nle.siafu.model.MultiAgent;
import de.nec.nle.siafu.model.MultiWorld;
import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.FollowerRole;
import de.tud.swt.cleaningrobots.MasterRole;

/**
 * Without user interface from Siafu. 
 * Create the robots with the specific goals for the test case where the master has followers 
 * and merge data between them.
 * 
 * @author Christopher Werner
 *
 */
public class ExploreMergeMasterFactoryMulti extends RobotFactoryMulti {

	public ExploreMergeMasterFactoryMulti (Configuration configuration)
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
	public ArrayList<MultiAgent> createRobots(MultiWorld world) {
		
		ArrayList<MultiAgent> population = new ArrayList<MultiAgent>();	
		
		//loadstation agent
		LoadstationAgentMulti lsa = createLoadStationAgent(world);
		population.add(lsa);
		
		if (configuration.wc.number_explore_agents > 0) {
			MasterRole mre = new MasterRole(lsa.getRobot());
			mre.addRole(mre);			
			
			lsa.addMasterMerge(mre);
			
			//explore agents
			for (int i = 0; i < configuration.wc.number_explore_agents; i++) {
				ExploreRobotAgentMulti era = createExploreAgent(world);
				era.addRandomStandardGoals();
				population.add(era);		
				
				FollowerRole fre = new FollowerRole(era.getRobot(), mre);
				fre.addRole(fre);
				mre.getFollowers().add(fre);
			}
		
			if (configuration.wc.number_hoove_agents > 0) {
				MasterRole mrh = new MasterRole(lsa.getRobot());
				mrh.addRole(mrh);
				
				lsa.addMasterMerge(mrh);

				//hoove agents
				for (int i = 0; i < configuration.wc.number_hoove_agents; i++) {
					HooveRobotAgentMulti hra = createHooveAgent(world);
					hra.addRandomStandardGoals();
					population.add(hra);
					
					FollowerRole frh = new FollowerRole(hra.getRobot(), mrh);
					frh.addRole(frh);
					mrh.getFollowers().add(frh);
				}
				mrh.getFollowers().add(mre);
							
				if (configuration.wc.number_wipe_agents > 0) {
					MasterRole mrw = new MasterRole(lsa.getRobot());
					mrw.addRole(mrw);
					
					lsa.addMasterMerge(mrw);
					
					//wipe agents
					for (int i = 0; i < configuration.wc.number_wipe_agents; i++) {
						WipeRobotAgentMulti wra = createWipeAgent(world);
						wra.addRandomStandardGoals();
						population.add(wra);
						
						FollowerRole frw = new FollowerRole(wra.getRobot(), mrw);
						frw.addRole(frw);
						mrw.getFollowers().add(frw);
					}
					mrw.getFollowers().add(mrh);
				}				
			}			
			lsa.addLoadIfRobotWantGoal();
		}
		
		//example output for correct robots
		for (MultiAgent a: population)
		{
			RobotAgentMulti ra = (RobotAgentMulti) a;
			System.out.println("Name: " + ra.cleaningRobot.getName() + " Roles: " + ra.cleaningRobot.getRoles() + " States: " + ra.cleaningRobot.getSupportedStates());
		}
		return population;
	}
}

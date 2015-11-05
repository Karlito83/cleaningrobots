package de.tud.swt.cleaningrobots.multithreaded;

import java.util.ArrayList;

import de.nec.nle.siafu.model.MultiAgent;
import de.nec.nle.siafu.model.MultiWorld;
import de.tud.evaluation.WorkingConfiguration;
import de.tud.swt.cleaningrobots.FollowerRole;
import de.tud.swt.cleaningrobots.MasterRole;

public class ExploreMergeMasterFactoryMulti extends RobotFactoryMulti {

	public ExploreMergeMasterFactoryMulti (WorkingConfiguration configuration)
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
		LoadstationAgentMulti lsa = createLoadStationAgent(world);
		population.add(lsa);
		
		if (configuration.number_explore_agents > 0) {
			MasterRole mre = new MasterRole(lsa.getRobot());
			mre.addRole(mre);			
			
			lsa.addMasterMerge(mre);
			
			//explore agents
			for (int i = 0; i < configuration.number_explore_agents; i++) {
				ExploreRobotAgentMulti era = createExploreAgent(world);
				era.addRandomStandardGoals();
				population.add(era);		
				
				FollowerRole fre = new FollowerRole(era.getRobot());
				fre.addRole(fre);
				fre.master = mre;
				mre.getFollowers().add(fre);
			}
		
			if (configuration.number_hoove_agents > 0) {
				MasterRole mrh = new MasterRole(lsa.getRobot());
				mrh.addRole(mrh);
				
				lsa.addMasterMerge(mrh);

				//hoove agents
				for (int i = 0; i < configuration.number_hoove_agents; i++) {
					HooveRobotAgentMulti hra = createHooveAgent(world);
					hra.addRandomStandardGoals();
					population.add(hra);
					
					FollowerRole frh = new FollowerRole(hra.getRobot());
					frh.addRole(frh);
					frh.master = mrh;
					mrh.getFollowers().add(frh);
				}
				mrh.getFollowers().add(mre);
							
				if (configuration.number_wipe_agents > 0) {
					MasterRole mrw = new MasterRole(lsa.getRobot());
					mrw.addRole(mrw);
					
					lsa.addMasterMerge(mrw);
					
					//wipe agents
					for (int i = 0; i < configuration.number_wipe_agents; i++) {
						WipeRobotAgentMulti wra = createWipeAgent(world);
						wra.addRandomStandardGoals();
						population.add(wra);
						
						FollowerRole frw = new FollowerRole(wra.getRobot());
						frw.addRole(frw);
						frw.master = mrw;
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

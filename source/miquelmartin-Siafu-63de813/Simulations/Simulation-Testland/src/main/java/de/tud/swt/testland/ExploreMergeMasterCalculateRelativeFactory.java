package de.tud.swt.testland;

import java.util.ArrayList;

import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.World;
import de.tud.evaluation.EvaluationConstants;
import de.tud.swt.cleaningrobots.FollowerRole;
import de.tud.swt.cleaningrobots.MasterRole;

public class ExploreMergeMasterCalculateRelativeFactory extends RobotFactory {

	public ExploreMergeMasterCalculateRelativeFactory ()
	{
		super();
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
			
			lsa.addMasterMerge(mre);
			lsa.addCalculateExploreRobotPositionGoal(mre);
			
			//explore agents
			for (int i = 0; i < EvaluationConstants.NUMBER_EXPLORE_AGENTS; i++) {
				ExploreRobotAgent era = createExploreAgent(world);
				era.addRelativeStandardGoals();
				population.add(era);		
				
				FollowerRole fre = new FollowerRole(era.getRobot());
				fre.addRole(fre);
				fre.master = mre;
				mre.getFollowers().add(fre);
			}
		
			if (EvaluationConstants.NUMBER_HOOVE_AGENTS > 0) {
				MasterRole mrh = new MasterRole(lsa.getRobot());
				mrh.addRole(mrh);
				
				lsa.addMasterMerge(mrh);
				lsa.addCalculateHooveRobotPositionGoal(mrh);

				//hoove agents
				for (int i = 0; i < EvaluationConstants.NUMBER_HOOVE_AGENTS; i++) {
					HooveRobotAgent hra = createHooveAgent(world);
					hra.addRelativeStandardGoals();
					population.add(hra);
					
					FollowerRole frh = new FollowerRole(hra.getRobot());
					frh.addRole(frh);
					frh.master = mrh;
					mrh.getFollowers().add(frh);
				}
				mrh.getFollowers().add(mre);
							
				if (EvaluationConstants.NUMBER_WIPE_AGENTS > 0) {
					MasterRole mrw = new MasterRole(lsa.getRobot());
					mrw.addRole(mrw);
					
					lsa.addMasterMerge(mrw);
					lsa.addCalculateWipeRobotPositionGoal(mrw);
					
					//wipe agents
					for (int i = 0; i < EvaluationConstants.NUMBER_WIPE_AGENTS; i++) {
						WipeRobotAgent wra = createWipeAgent(world);
						wra.addRelativeStandardGoals();
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
		
		//example output for master follower relation
		for (Agent a: population)
		{
			RobotAgent ra = (RobotAgent) a;
			System.out.println("Name: " + ra.cleaningRobot.getName() + " Roles: " + ra.cleaningRobot.getRoles());
		}
		
		return population;
	}

}

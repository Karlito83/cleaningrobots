package de.tud.swt.testland;

import java.util.ArrayList;

import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.FollowerRole;
import de.tud.swt.cleaningrobots.MasterRole;

/**
 * With user interface from Siafu. 
 * Create the robots with the specific goals for the test case with a master robot and many follower robots. 
 * Where the master merge data and calculate all destination and make all moves.
 * 
 * @author Christopher Werner
 *
 */
public class MasterExploreFactory extends RobotFactory {

	private boolean relative;
	
	public MasterExploreFactory (Configuration configuration)
	{
		super(configuration);
		this.relative = false;
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
		LoadStationAgent lsa = createLoadStationAgent(world);
		population.add(lsa);
		
		if (configuration.wc.number_explore_agents > 0) {
			MasterRole mre = new MasterRole(lsa.getRobot());
			mre.addRole(mre);			
			lsa.addMasterExploreGoals(mre, relative);
			
			//explore agents
			for (int i = 0; i < configuration.wc.number_explore_agents; i++) {
				ExploreRobotAgent era = createExploreAgent(world);
				era.addMasterExploreGoals();
				population.add(era);		
				
				FollowerRole fre = new FollowerRole(era.getRobot(), mre);
				fre.addRole(fre);
				mre.getFollowers().add(fre);
			}
			
			if (configuration.wc.number_hoove_agents > 0) {
				MasterRole mrh = new MasterRole(lsa.getRobot());
				mrh.addRole(mrh);
				lsa.addMasterHooveGoal(mrh, relative);
				
				//hoove agents
				for (int i = 0; i < configuration.wc.number_hoove_agents; i++) {
					HooveRobotAgent hra = createHooveAgent(world);
					hra.addMasterHooveGoals();
					population.add(hra);
					
					FollowerRole frh = new FollowerRole(hra.getRobot(), mrh);
					frh.addRole(frh);
					mrh.getFollowers().add(frh);
				}
								
				if (configuration.wc.number_wipe_agents > 0) {
					MasterRole mrw = new MasterRole(lsa.getRobot());
					mrw.addRole(mrw);
					lsa.addMasterWipeGoal(mrw, relative);
					
					//wipe agents
					for (int i = 0; i < configuration.wc.number_wipe_agents; i++) {
						WipeRobotAgent wra = createWipeAgent(world);
						wra.addMasterWipeGoals();
						population.add(wra);
						
						FollowerRole frw = new FollowerRole(wra.getRobot(), mrw);
						frw.addRole(frw);
						mrw.getFollowers().add(frw);
					}
				}
				
			}			
			lsa.addLoadIfRobotWantGoal();
		}		
				
		for (Agent a: population)
		{
			RobotAgent ra = (RobotAgent) a;
			System.out.println("Name: " + ra.cleaningRobot.getName() + " Roles: " + ra.cleaningRobot.getRoles() + " States: " + ra.cleaningRobot.getSupportedStates());
		}
		return population;
	}
}

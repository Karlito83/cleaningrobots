package de.tud.swt.cleaningrobots.factory;

import java.util.ArrayList;

import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.roles.ExplorerCalculateMergeMasterRole;
import de.tud.swt.cleaningrobots.roles.ExplorerRelativeFollowerRole;
import de.tud.swt.cleaningrobots.roles.FollowerRole;
import de.tud.swt.cleaningrobots.roles.HooverCalculateMergeMasterRole;
import de.tud.swt.cleaningrobots.roles.HooverRelativeFollowerRole;
import de.tud.swt.cleaningrobots.roles.LoadstationRole;
import de.tud.swt.cleaningrobots.roles.MasterRole;
import de.tud.swt.cleaningrobots.roles.WiperCalculateMergeMasterRole;
import de.tud.swt.cleaningrobots.roles.WiperRelativeFollowerRole;
import de.tud.swt.testland.ICreateAgents;
import de.tud.swt.testland.IRobotAgent;

/**
 * Without user interface from Siafu. 
 * Create the robots with the specific goals for the test case where the master has followers 
 * and calculate new destination and merge data between them. With a relative destination calculation.
 * 
 * @author Christopher Werner
 *
 */
public class ExploreMergeMasterCalculateRelativeFactory extends IAgentFactory {
	
	public ExploreMergeMasterCalculateRelativeFactory (Configuration configuration)
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
	public ArrayList<IRobotAgent> createRobots(ICreateAgents factory) {
		
		ArrayList<IRobotAgent> population = new ArrayList<IRobotAgent>();	
		
		//loadstation agent
		IRobotAgent lsa = factory.createLoadStationAgent();
		population.add(lsa);
		
		if (configuration.wc.number_explore_agents > 0) {
			MasterRole mre = new ExplorerCalculateMergeMasterRole(lsa.getRobot());
			mre.addRole(mre);
			
			//explore agents
			for (int i = 0; i < configuration.wc.number_explore_agents; i++) {
				IRobotAgent era = factory.createExploreAgent();
				population.add(era);		
				
				FollowerRole fre = new ExplorerRelativeFollowerRole(era.getRobot(), mre);
				fre.addRole(fre);
				mre.getFollowers().add(fre);
			}
		
			if (configuration.wc.number_hoove_agents > 0) {
				MasterRole mrh = new HooverCalculateMergeMasterRole(lsa.getRobot());
				mrh.addRole(mrh);

				//hoove agents
				for (int i = 0; i < configuration.wc.number_hoove_agents; i++) {
					IRobotAgent hra = factory.createHooveAgent();
					population.add(hra);
					
					FollowerRole frh = new HooverRelativeFollowerRole(hra.getRobot(), mrh);
					frh.addRole(frh);
					mrh.getFollowers().add(frh);
				}
				mrh.getFollowers().add(mre);
							
				if (configuration.wc.number_wipe_agents > 0) {
					MasterRole mrw = new WiperCalculateMergeMasterRole(lsa.getRobot());
					mrw.addRole(mrw);
					
					//wipe agents
					for (int i = 0; i < configuration.wc.number_wipe_agents; i++) {
						IRobotAgent wra = factory.createWipeAgent();
						population.add(wra);
						
						FollowerRole frw = new WiperRelativeFollowerRole(wra.getRobot(), mrw);
						frw.addRole(frw);
						mrw.getFollowers().add(frw);
					}
					mrw.getFollowers().add(mrh);
				}				
			}			
			RobotRole rr = new LoadstationRole(lsa.getRobot());
			rr.addRole(rr);
		}
		
		//example output for master follower relation
		for (IRobotAgent a: population)
		{
			a.getRobot().initializeRoles();
			System.out.println("Name: " + a.getRobot().getName() + " Roles: " + a.getRobot().getRoles() + " States: " + a.getRobot().getSupportedStates());
		}
		
		return population;
	}

}

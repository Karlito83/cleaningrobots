package de.tud.swt.cleaningrobots.factory;

import java.util.ArrayList;

import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.roles.ExplorerControlMasterRole;
import de.tud.swt.cleaningrobots.roles.ExplorerControlledFollowerRole;
import de.tud.swt.cleaningrobots.roles.FollowerRole;
import de.tud.swt.cleaningrobots.roles.HooverControlMasterRole;
import de.tud.swt.cleaningrobots.roles.HooverControlledFollowerRole;
import de.tud.swt.cleaningrobots.roles.LoadstationRole;
import de.tud.swt.cleaningrobots.roles.MasterRole;
import de.tud.swt.cleaningrobots.roles.WiperControlMasterRole;
import de.tud.swt.cleaningrobots.roles.WiperControlledFollowerRole;
import de.tud.swt.testland.ICreateAgents;
import de.tud.swt.testland.IRobotAgent;

/**
 * Without user interface from Siafu. 
 * Create the robots with the specific goals for the test case with a master robot and many follower robots. 
 * Where the master merge data and calculate all destination and make all moves.
 * 
 * @author Christopher Werner
 *
 */
public class MasterExploreFactory extends IAgentFactory {
	
	public MasterExploreFactory (Configuration configuration)
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
			MasterRole mre = new ExplorerControlMasterRole(lsa.getRobot());
			mre.addRole(mre);			
			
			//explore agents
			for (int i = 0; i < configuration.wc.number_explore_agents; i++) {
				IRobotAgent era = factory.createExploreAgent();
				population.add(era);		
				
				FollowerRole fre = new ExplorerControlledFollowerRole(era.getRobot(), mre);
				fre.addRole(fre);
				mre.getFollowers().add(fre);
			}
			
			if (configuration.wc.number_hoove_agents > 0) {
				MasterRole mrh = new HooverControlMasterRole(lsa.getRobot());
				mrh.addRole(mrh);
				
				//hoove agents
				for (int i = 0; i < configuration.wc.number_hoove_agents; i++) {
					IRobotAgent hra = factory.createHooveAgent();
					population.add(hra);
					
					FollowerRole frh = new HooverControlledFollowerRole(hra.getRobot(), mrh);
					frh.addRole(frh);
					mrh.getFollowers().add(frh);
				}
								
				if (configuration.wc.number_wipe_agents > 0) {
					MasterRole mrw = new WiperControlMasterRole(lsa.getRobot());
					mrw.addRole(mrw);
					
					//wipe agents
					for (int i = 0; i < configuration.wc.number_wipe_agents; i++) {
						IRobotAgent wra = factory.createWipeAgent();
						population.add(wra);
						
						FollowerRole frw = new WiperControlledFollowerRole(wra.getRobot(), mrw);
						frw.addRole(frw);
						mrw.getFollowers().add(frw);
					}
				}
				
			}			
			RobotRole rr = new LoadstationRole(lsa.getRobot());
			rr.addRole(rr);
		}		
				
		for (IRobotAgent a: population)
		{
			a.getRobot().initializeRoles();
			System.out.println("Name: " + a.getRobot().getName() + " Roles: " + a.getRobot().getRoles() + " States: " + a.getRobot().getSupportedStates());
		}
		return population;
	}
}

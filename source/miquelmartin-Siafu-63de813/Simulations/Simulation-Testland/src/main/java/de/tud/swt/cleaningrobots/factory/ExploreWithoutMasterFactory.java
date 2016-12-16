package de.tud.swt.cleaningrobots.factory;

import java.util.ArrayList;

import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.roles.CommunicationInterfaceRole;
import de.tud.swt.cleaningrobots.roles.ExplorerRole;
import de.tud.swt.cleaningrobots.roles.HooverRole;
import de.tud.swt.cleaningrobots.roles.LoadstationRole;
import de.tud.swt.cleaningrobots.roles.WiperRole;
import de.tud.swt.testland.ICreateAgents;
import de.tud.swt.testland.IRobotAgent;

/**
 * Without user interface from Siafu. 
 * Create the robots with the specific goals for the test case without a master robot 
 * and merge data between them.
 * 
 * @author Christopher Werner
 *
 */
public class ExploreWithoutMasterFactory extends IAgentFactory {
		
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
	public ArrayList<IRobotAgent> createRobots(ICreateAgents factory) {
		
		ArrayList<IRobotAgent> population = new ArrayList<IRobotAgent>();	
		
		//loadstation agent
		IRobotAgent lsa = factory.createLoadStation();
		population.add(lsa);
		
		//load if robot want role
		RobotRole rr = new LoadstationRole(lsa.getRobot());
		rr.addRole(rr);
		
		boolean proof;
		
		if (configuration.wc.number_explore_agents > 0) {
			
			proof = false;
			if (configuration.wc.number_hoove_agents > 0) {
				proof = true;
			}
			
			//explore agents
			for (int i = 0; i < configuration.wc.number_explore_agents; i++) {
				IRobotAgent era = factory.createExploreAgent();
				population.add(era);
				
				RobotRole exr = new ExplorerRole(era.getRobot());
				exr.addRole(exr);
				
				if (proof) {
					RobotRole exw = new CommunicationInterfaceRole(era.getRobot());
					exw.addRole(exw);
					proof = false;
				}
			}
			
			if (configuration.wc.number_hoove_agents > 0) {
				
				proof = false;
				if (configuration.wc.number_wipe_agents > 0) {
					proof = true;
				}
				
				//hoove agents
				for (int i = 0; i < configuration.wc.number_hoove_agents; i++) {
					IRobotAgent hra = factory.createHooveAgent();
					population.add(hra);
					
					RobotRole hor = new HooverRole(hra.getRobot());
					hor.addRole(hor);
					
					if (proof) {
						RobotRole how = new CommunicationInterfaceRole(hra.getRobot());
						how.addRole(how);
						proof = false;
					}
				}
				
				if (configuration.wc.number_wipe_agents > 0) {
					
					//wipe agents
					for (int i = 0; i < configuration.wc.number_wipe_agents; i++) {
						IRobotAgent wra = factory.createWipeAgent();
						population.add(wra);
						
						RobotRole wir = new WiperRole(wra.getRobot());
						wir.addRole(wir);
					}
				}
			}			
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

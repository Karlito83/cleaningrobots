package de.tud.swt.cleaningrobots.factory;

import java.util.ArrayList;

import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.roles.CommunicationInterfaceRole;
import de.tud.swt.cleaningrobots.roles.ExplorerRole;
import de.tud.swt.cleaningrobots.roles.HooverRole;
import de.tud.swt.cleaningrobots.roles.LoadstationRole;
import de.tud.swt.cleaningrobots.roles.LoggingCsvRole;
import de.tud.swt.cleaningrobots.roles.LoggingPictureRole;
import de.tud.swt.cleaningrobots.roles.LoggingXmlRole;
import de.tud.swt.cleaningrobots.roles.WiperRole;
import de.tud.swt.testland.ISimulatorAgentFactory;
import de.tud.swt.testland.ISimulatorAgent;

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
	public ArrayList<ISimulatorAgent> createRobots(ISimulatorAgentFactory factory) {
		
		ArrayList<ISimulatorAgent> population = new ArrayList<ISimulatorAgent>();	
		
		//loadstation agent
		ISimulatorAgent lsa = factory.createLoadStation();
		population.add(lsa);
		
		//load if robot want role
		AgentRole rr = new LoadstationRole(lsa.getRobot());
		rr.addRole(rr);
		
		boolean proof;
		
		if (configuration.getWc().number_explore_agents > 0) {
			
			proof = false;
			if (configuration.getWc().number_hoove_agents > 0) {
				proof = true;
			}
			
			//explore agents
			for (int i = 0; i < configuration.getWc().number_explore_agents; i++) {
				ISimulatorAgent era = factory.createExploreAgent();
				population.add(era);
				
				AgentRole exr = new ExplorerRole(era.getRobot());
				exr.addRole(exr);
				
				if (proof) {
					AgentRole exw = new CommunicationInterfaceRole(era.getRobot());
					exw.addRole(exw);
					proof = false;
				}
			}
			
			if (configuration.getWc().number_hoove_agents > 0) {
				
				proof = false;
				if (configuration.getWc().number_wipe_agents > 0) {
					proof = true;
				}
				
				//hoove agents
				for (int i = 0; i < configuration.getWc().number_hoove_agents; i++) {
					ISimulatorAgent hra = factory.createHooveAgent();
					population.add(hra);
					
					AgentRole hor = new HooverRole(hra.getRobot());
					hor.addRole(hor);
					
					if (proof) {
						AgentRole how = new CommunicationInterfaceRole(hra.getRobot());
						how.addRole(how);
						proof = false;
					}
				}
				
				if (configuration.getWc().number_wipe_agents > 0) {
					
					//wipe agents
					for (int i = 0; i < configuration.getWc().number_wipe_agents; i++) {
						ISimulatorAgent wra = factory.createWipeAgent();
						population.add(wra);
						
						AgentRole wir = new WiperRole(wra.getRobot());
						wir.addRole(wir);
					}
				}
			}			
		}
		
		//add the logging roles to every agent
		if (configuration.getWc().csvSave)
		{
			for (ISimulatorAgent a: population)
			{
				LoggingCsvRole csvRole = new LoggingCsvRole(a.getRobot());
				csvRole.addRole(csvRole);
			}
		}
		if (configuration.getWc().pngSave)
		{
			for (ISimulatorAgent a: population)
			{
				LoggingPictureRole pngRole = new LoggingPictureRole(a.getRobot());
				pngRole.addRole(pngRole);
			}
		}
		if (configuration.getWc().xmlSave)
		{
			for (ISimulatorAgent a: population)
			{
				LoggingXmlRole xmlRole = new LoggingXmlRole(a.getRobot());
				xmlRole.addRole(xmlRole);
			}
		}
		
		//example output for master follower relation
		for (ISimulatorAgent a: population)
		{
			a.getRobot().createAndInitializeRoleGoals();
			System.out.println("Name: " + a.getRobot().getName() + " Roles: " + a.getRobot().getRoles() + " States: " + a.getRobot().getSupportedStates());
		}
		
		return population;
	}
}

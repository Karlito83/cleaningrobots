package de.tud.swt.cleaningrobots.factory;

import java.util.ArrayList;

import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.roles.ExplorerControlMasterRole;
import de.tud.swt.cleaningrobots.roles.ExplorerControlledFollowerRole;
import de.tud.swt.cleaningrobots.roles.FollowerRole;
import de.tud.swt.cleaningrobots.roles.HooverControlMasterRole;
import de.tud.swt.cleaningrobots.roles.HooverControlledFollowerRole;
import de.tud.swt.cleaningrobots.roles.LoadstationRole;
import de.tud.swt.cleaningrobots.roles.LoggingCsvRole;
import de.tud.swt.cleaningrobots.roles.LoggingPictureRole;
import de.tud.swt.cleaningrobots.roles.LoggingXmlRole;
import de.tud.swt.cleaningrobots.roles.MasterRole;
import de.tud.swt.cleaningrobots.roles.WiperControlMasterRole;
import de.tud.swt.cleaningrobots.roles.WiperControlledFollowerRole;
import de.tud.swt.testland.ISimulatorAgentFactory;
import de.tud.swt.testland.ISimulatorAgent;

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
	public ArrayList<ISimulatorAgent> createRobots(ISimulatorAgentFactory factory) {
		
		ArrayList<ISimulatorAgent> population = new ArrayList<ISimulatorAgent>();	
		
		//loadstation agent
		ISimulatorAgent lsa = factory.createLoadStationAgent();
		population.add(lsa);
		
		if (configuration.getWc().number_explore_agents > 0) {
			MasterRole mre = new ExplorerControlMasterRole(lsa.getRobot());
			mre.addRole(mre);			
			
			//explore agents
			for (int i = 0; i < configuration.getWc().number_explore_agents; i++) {
				ISimulatorAgent era = factory.createExploreAgent();
				population.add(era);		
				
				FollowerRole fre = new ExplorerControlledFollowerRole(era.getRobot(), mre);
				fre.addRole(fre);
				mre.getFollowers().add(fre);
			}
			
			if (configuration.getWc().number_hoove_agents > 0) {
				MasterRole mrh = new HooverControlMasterRole(lsa.getRobot());
				mrh.addRole(mrh);
				
				//hoove agents
				for (int i = 0; i < configuration.getWc().number_hoove_agents; i++) {
					ISimulatorAgent hra = factory.createHooveAgent();
					population.add(hra);
					
					FollowerRole frh = new HooverControlledFollowerRole(hra.getRobot(), mrh);
					frh.addRole(frh);
					mrh.getFollowers().add(frh);
				}
								
				if (configuration.getWc().number_wipe_agents > 0) {
					MasterRole mrw = new WiperControlMasterRole(lsa.getRobot());
					mrw.addRole(mrw);
					
					//wipe agents
					for (int i = 0; i < configuration.getWc().number_wipe_agents; i++) {
						ISimulatorAgent wra = factory.createWipeAgent();
						population.add(wra);
						
						FollowerRole frw = new WiperControlledFollowerRole(wra.getRobot(), mrw);
						frw.addRole(frw);
						mrw.getFollowers().add(frw);
					}
				}
				
			}			
			AgentRole rr = new LoadstationRole(lsa.getRobot());
			rr.addRole(rr);
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
				
		for (ISimulatorAgent a: population)
		{
			a.getRobot().createAndInitializeRoleGoals();
			System.out.println("Name: " + a.getRobot().getName() + " Roles: " + a.getRobot().getRoles() + " States: " + a.getRobot().getSupportedStates());
		}
		return population;
	}
}

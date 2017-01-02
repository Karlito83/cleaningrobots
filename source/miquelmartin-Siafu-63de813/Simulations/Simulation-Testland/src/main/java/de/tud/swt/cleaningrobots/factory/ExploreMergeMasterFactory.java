package de.tud.swt.cleaningrobots.factory;

import java.util.ArrayList;

import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.roles.ExplorerRandomFollowerRole;
import de.tud.swt.cleaningrobots.roles.FollowerRole;
import de.tud.swt.cleaningrobots.roles.HooverRandomFollowerRole;
import de.tud.swt.cleaningrobots.roles.LoadstationRole;
import de.tud.swt.cleaningrobots.roles.LoggingCsvRole;
import de.tud.swt.cleaningrobots.roles.LoggingPictureRole;
import de.tud.swt.cleaningrobots.roles.LoggingXmlRole;
import de.tud.swt.cleaningrobots.roles.MasterRole;
import de.tud.swt.cleaningrobots.roles.MergeMasterRole;
import de.tud.swt.cleaningrobots.roles.WiperRandomFollowerRole;
import de.tud.swt.testland.ICreateAgents;
import de.tud.swt.testland.IRobotAgent;

/**
 * Without user interface from Siafu. 
 * Create the robots with the specific goals for the test case where the master has followers 
 * and merge data between them.
 * 
 * @author Christopher Werner
 *
 */
public class ExploreMergeMasterFactory extends IAgentFactory {
	
	public ExploreMergeMasterFactory (Configuration configuration)
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
		
		if (configuration.getWc().number_explore_agents > 0) {
			MasterRole mre = new MergeMasterRole(lsa.getRobot());
			mre.addRole(mre);		
			
			//explore agents
			for (int i = 0; i < configuration.getWc().number_explore_agents; i++) {
				IRobotAgent era = factory.createExploreAgent();
				population.add(era);		
				
				FollowerRole fre = new ExplorerRandomFollowerRole(era.getRobot(), mre);
				fre.addRole(fre);
				mre.getFollowers().add(fre);
			}
		
			if (configuration.getWc().number_hoove_agents > 0) {
				MasterRole mrh = new MergeMasterRole(lsa.getRobot());
				mrh.addRole(mrh);

				//hoove agents
				for (int i = 0; i < configuration.getWc().number_hoove_agents; i++) {
					IRobotAgent hra = factory.createHooveAgent();
					population.add(hra);
					
					FollowerRole frh = new HooverRandomFollowerRole(hra.getRobot(), mrh);
					frh.addRole(frh);
					mrh.getFollowers().add(frh);
				}
				mrh.getFollowers().add(mre);
							
				if (configuration.getWc().number_wipe_agents > 0) {
					MasterRole mrw = new MergeMasterRole(lsa.getRobot());
					mrw.addRole(mrw);
					
					//wipe agents
					for (int i = 0; i < configuration.getWc().number_wipe_agents; i++) {
						IRobotAgent wra = factory.createWipeAgent();
						population.add(wra);
						
						FollowerRole frw = new WiperRandomFollowerRole(wra.getRobot(), mrw);
						frw.addRole(frw);
						mrw.getFollowers().add(frw);
					}
					mrw.getFollowers().add(mrh);
				}				
			}			
			RobotRole rr = new LoadstationRole(lsa.getRobot());
			rr.addRole(rr);
		}
		
		//add the logging roles to every agent
		if (configuration.getWc().csvSave)
		{
			for (IRobotAgent a: population)
			{
				LoggingCsvRole csvRole = new LoggingCsvRole(a.getRobot());
				csvRole.addRole(csvRole);
			}
		}
		if (configuration.getWc().pngSave)
		{
			for (IRobotAgent a: population)
			{
				LoggingPictureRole pngRole = new LoggingPictureRole(a.getRobot());
				pngRole.addRole(pngRole);
			}
		}
		if (configuration.getWc().xmlSave)
		{
			for (IRobotAgent a: population)
			{
				LoggingXmlRole xmlRole = new LoggingXmlRole(a.getRobot());
				xmlRole.addRole(xmlRole);
			}
		}
		
		//example output for correct robots
		for (IRobotAgent a: population)
		{
			a.getRobot().createAndInitializeRoleGoals();
			System.out.println("Name: " + a.getRobot().getName() + " Roles: " + a.getRobot().getRoles() + " States: " + a.getRobot().getSupportedStates());
		}
		return population;
	}
}

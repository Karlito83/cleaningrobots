/*
 * Copyright NEC Europe Ltd. 2006-2007
 * 
 * This file is part of the context simulator called Siafu.
 * 
 * Siafu is free software; you can redistribute it and/or modify it under the
 * terms of the GNU General Public License as published by the Free Software
 * Foundation; either version 2 of the License, or (at your option) any later
 * version.
 * 
 * Siafu is distributed in the hope that it will be useful, but WITHOUT ANY
 * WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 * 
 * You should have received a copy of the GNU General Public License along
 * with this program. If not, see <http://www.gnu.org/licenses/>.
 */

package de.tud.swt.testland;

import java.util.ArrayList;
import java.util.Collection;

import de.nec.nle.siafu.behaviormodels.BaseAgentModel;
import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.World;
import de.tud.evaluation.WorkingConfiguration;
import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.measure.ExportFiles;

/**
 * This Agent Model defines the behavior of users in this test simulation.
 * Essentially, most users will wander around in a zombie like state, except for
 * Pietro and Teresa, who will stay put, and the postman, who will spend a
 * simulation life time running between the two ends of the map.
 * 
 * @author Miquel Martin
 * 
 */
public class AgentModel extends BaseAgentModel {
	
	private long startTime;
	private Configuration config;
	
	/**
	 * Constructor for the agent model.
	 * 
	 * @param world
	 *            the simulation's world
	 */
	public AgentModel(World world, WorkingConfiguration configuration) {
		super(world, configuration);
		this.config = new Configuration(configuration);
	}

	/**
	 * Create a bunch of agents that will wander around aimlessly. Tweak them
	 * for testing purposes as needed. Two agents, Pietro and Teresa, are
	 * singled out and left under the control of the user. A third agent,
	 * Postman, is set to run errands between the two places int he map.
	 * 
	 * @return the created agents
	 */
	@Override
	public ArrayList<Agent> createAgents() {

		ArrayList<Agent> agents = new ArrayList<Agent>();

		try {
			switch (configuration.config) {
				case 0:  agents = new MasterExploreFactory(config).createRobots(world);
						 break;
	            case 1:  agents = new ExploreWithoutMasterFactory(config).createRobots(world);
	                     break;	            
	            case 2:  agents = new ExploreMergeMasterFactory(config).createRobots(world);
	                     break;
	            case 3:  agents = new ExploreMergeMasterCalculateFactory(config).createRobots(world);
	                     break;
	            case 4:  agents = new ExploreMergeMasterCalculateRelativeFactory(config).createRobots(world);
	                     break;
	            default: agents = new MasterExploreFactory(config).createRobots(world);
	                     break;
	        }
		} catch (Exception ex) {
		}
		startTime = System.nanoTime();
		return agents;
	}
	
	private boolean runAction (Collection<Agent> agents) {
		boolean finish = true;
		//do that for each robot
		for (Agent a : agents) {
			//only if robot is on
			if (!((RobotAgent)a).getRobot().isShutDown()) {
				a.wander();
				if (!((RobotAgent)a).isFinish())
					finish = false;
			}
		}
		return finish;
	}
	
	/**
	 * Make all the normal agents wander around, and the postman, run errands
	 * from one place to another. His speed depends on the time, slowing down at
	 * night.
	 * 
	 * @param agents
	 *            the list of agents
	 */
	@Override
	public void doIteration(Collection<Agent> agents) {
		configuration.iteration = configuration.iteration + 1;
		
		if (runAction(agents))
		{
			long endTime = System.nanoTime();
			
			//do evaluation output
			for (Agent a : agents) {
				((RobotAgent)a).getRobot().addLastMeasurement();				
			}
			
			//make data output for all measurements
			for (Agent a : agents) {
				RobotCore rc = ((RobotAgent)a).getRobot();
				
				//save JSON document in .txt
				rc.getMeasurement().benchmarkTime = (endTime - startTime);
				String measu = rc.getMeasurement().toJson();
				
				ExportFiles ef = new ExportFiles();
				String path = "M" + configuration.map + "_V" + configuration.config + "_CE" + configuration.number_explore_agents + "_CH" + configuration.number_hoove_agents +
						"_CW" + configuration.number_wipe_agents + "_B" + configuration.new_field_count + "_D" + configuration.run + "_" + rc.getName()+ ".txt";
				ef.addLineToFile(measu, path);
				/*FileWorker fw = new FileWorker("M" + configuration.map + "_V" + configuration.config + "_CE" + configuration.number_explore_agents + "_CH" + configuration.number_hoove_agents +
						"_CW" + configuration.number_wipe_agents + "_B" + configuration.new_field_count + "_D" + configuration.run + "_" + rc.getName()+ ".txt");				
				fw.addLineToFile(measu);*/					
			}
			ExportFiles ef = new ExportFiles();
			String path = "M" + configuration.map + "_V" +configuration.config + "_CE" + configuration.number_explore_agents + "_CH" + configuration.number_hoove_agents +
					"_CW" + configuration.number_wipe_agents + "_B" + configuration.new_field_count + "_D" + configuration.run + "_" + "exchange.txt";
			ef.addConfigurationToFile(configuration, path);
			
			/*FileWorker fw = new FileWorker("M" + configuration.map + "_V" +configuration.config + "_CE" + configuration.number_explore_agents + "_CH" + configuration.number_hoove_agents +
					"_CW" + configuration.number_wipe_agents + "_B" + configuration.new_field_count + "_D" + configuration.run + "_" + "exchange.txt");
			int tester = 0;
			for (ExchangeMeasurement em : configuration.exchange) {
				tester++;
				em.setNumber(tester);
				String result = em.toJson();
				fw.addLineToFile(result);
			}*/
			System.out.println("Programm Finish!");
			System.out.println("Iterations: " + configuration.iteration);
			this.runFinish = true;
		}
	}
}

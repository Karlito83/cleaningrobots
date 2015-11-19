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

import de.nec.nle.siafu.behaviormodels.BaseAgentModelMulti;
import de.nec.nle.siafu.model.MultiAgent;
import de.nec.nle.siafu.model.MultiWorld;
import de.tud.evaluation.WorkingConfiguration;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.measure.ExportFiles;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.multithreaded.ExploreMergeMasterCalculateFactoryMulti;
import de.tud.swt.cleaningrobots.multithreaded.ExploreMergeMasterCalculateRelativeFactoryMulti;
import de.tud.swt.cleaningrobots.multithreaded.ExploreMergeMasterFactoryMulti;
import de.tud.swt.cleaningrobots.multithreaded.ExploreWithoutMasterFactoryMulti;
import de.tud.swt.cleaningrobots.multithreaded.MasterExploreFactoryMulti;
import de.tud.swt.cleaningrobots.multithreaded.RobotAgentMulti;

/**
 * This Agent Model defines the behavior of users in this test simulation.
 * Essentially, most users will wander around in a zombie like state, except for
 * Pietro and Teresa, who will stay put, and the postman, who will spend a
 * simulation life time running between the two ends of the map.
 * 
 * @author Miquel Martin
 * 
 */
public class AgentModelMulti extends BaseAgentModelMulti {
	
	private long startTime;
	private boolean roboterFinish;
	private boolean completeFinish;
	
	/**
	 * Constructor for the agent model.
	 * 
	 * @param world
	 *            the simulation's world
	 */
	public AgentModelMulti(MultiWorld world, WorkingConfiguration configuration) {
		super(world, configuration);
		this.configuration.as = new State("Start");
		this.completeFinish = false;
		this.roboterFinish = false;
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
	public ArrayList<MultiAgent> createAgents() {

		ArrayList<MultiAgent> agents = new ArrayList<MultiAgent>();

		try {
			switch (configuration.config) {
				case 0:  agents = new MasterExploreFactoryMulti(configuration).createRobots(world);
						 break;
	            case 1:  agents = new ExploreWithoutMasterFactoryMulti(configuration).createRobots(world);
	                     break;	            
	            case 2:  agents = new ExploreMergeMasterFactoryMulti(configuration).createRobots(world);
	                     break;
	            case 3:  agents = new ExploreMergeMasterCalculateFactoryMulti(configuration).createRobots(world);
	                     break;
	            case 4:  agents = new ExploreMergeMasterCalculateRelativeFactoryMulti(configuration).createRobots(world);
	                     break;
	            default: agents = new MasterExploreFactoryMulti(configuration).createRobots(world);
	                     break;
	        }
		} catch (Exception ex) {
		}
		startTime = System.nanoTime();
		return agents;
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
	public void doIteration(Collection<MultiAgent> agents) {
		configuration.iteration = configuration.iteration + 1;
		
		if (!completeFinish) {
			if (!roboterFinish) {			
				roboterFinish = true;
				//wemm noch nicht finsh dann mache das hier
				for (MultiAgent a : agents) {
					//nur wenn Robot noch nicht aus ist
					if (!((RobotAgentMulti)a).getRobot().isShutDown()) {
						((RobotAgentMulti)a).wander();
						if (!((RobotAgentMulti)a).isFinish())
							roboterFinish = false;
					}
				}
			} else {
				long endTime = System.nanoTime();
				
				for (MultiAgent a : agents) {
					//nur wenn Robot noch nicht aus ist
					((RobotAgentMulti)a).getRobot().addLastMeasurement();				
				}
				
				//Programm ist fertig Lese Measurement aller Roboter und gebe in Datei aus
				for (MultiAgent a : agents) {
					RobotCore rc = ((RobotAgentMulti)a).getRobot();
					
					//Json Datein in .txt speicher
					rc.getMeasurement().benchmarkTime = (endTime - startTime);
					String measu = rc.getMeasurement().toJson();
					
					ExportFiles ef = new ExportFiles();
					String path = "M" + configuration.map + "_V" + configuration.config + "_CE" + configuration.number_explore_agents + "_CH" + configuration.number_hoove_agents +
							"_CW" + configuration.number_wipe_agents + "_B" + configuration.new_field_count + "_D" + configuration.run + "_" + rc.getName()+ ".txt";
					ef.addLineToFile(measu, path);
					/*FileWorker fw = new FileWorker("M" + configuration.map + "_V" + configuration.config + "_CE" + configuration.number_explore_agents + "_CH" + configuration.number_hoove_agents +
							"_CW" + configuration.number_wipe_agents + "_B" + configuration.new_field_count + "_D" + configuration.run + "_" + rc.getName()+ ".txt");				
					fw.addLineToFile(measu);*/
					
					//Roboter time in csv speichern
					//outputCsv(rc.getMeasurement().timeProTick, rc.getName() + "Time");
					
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
				this.completeFinish = true;
			}
		} else {
			this.runFinish = true;
		}
	}
}

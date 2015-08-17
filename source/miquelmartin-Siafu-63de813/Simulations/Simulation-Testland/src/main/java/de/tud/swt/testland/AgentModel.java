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

import static de.tud.swt.testland.Constants.POPULATION;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.nec.nle.siafu.behaviormodels.BaseAgentModel;
import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.World;

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

	private final Logger logger = LogManager.getRootLogger();
	
	private boolean finish;
	
	/**
	 * Constructor for the agent model.
	 * 
	 * @param world
	 *            the simulation's world
	 */
	public AgentModel(final World world) {
		super(world);
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
			logger.info("Creating " + POPULATION + " random cleaning robots.");
			agents = new ExploreFactory().createRobots(world);
		} catch (Exception ex) {
			logger.error("An exception occured while creating the population.", ex);
		}

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
	public void doIteration(final Collection<Agent> agents) {
		System.out.println("New Iteration: ");
		if (!finish) {
			finish = true;
			//wemm noch nicht finsh dann mache das hier
			for (Agent a : agents) {
				a.wander();
				System.out.println("Robot: " + ((RobotAgent)a).getName() + " finish: " + ((RobotAgent)a).isFinish());
				if (!((RobotAgent)a).isFinish())
					finish = false;
			}
		} else {
			System.out.println("Programm Finish!");
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

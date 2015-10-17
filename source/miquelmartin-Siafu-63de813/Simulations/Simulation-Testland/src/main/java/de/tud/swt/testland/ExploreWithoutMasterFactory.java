package de.tud.swt.testland;

import java.util.ArrayList;

import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.World;
import de.tud.evaluation.EvaluationConstants;

public class ExploreWithoutMasterFactory extends RobotFactory {
	
	public ExploreWithoutMasterFactory ()
	{
		super();
	}
	
	/**
	 * Create a number of random agents.
	 * 
	 * @param world
	 *            the world where the agents will dwell
	 * @return an ArrayList with the created agents
	 */
	@Override
	public ArrayList<Agent> createRobots(World world) {
		
		ArrayList<Agent> population = new ArrayList<Agent>();	
		
		//loadstation agent
		LoadStationAgent lsa = createLoadStationAgent(world);
		lsa.addLoadIfRobotWantGoal();
		population.add(lsa);
		
		if (EvaluationConstants.NUMBER_EXPLORE_AGENTS > 0) {
			
			//explore agents
			for (int i = 0; i < EvaluationConstants.NUMBER_EXPLORE_AGENTS; i++) {
				ExploreRobotAgent era = createExploreAgent(world);
				era.addWithoutMasterConfiguration();
				population.add(era);
			}
			
			if (EvaluationConstants.NUMBER_HOOVE_AGENTS > 0) {
				
				//hoove agents
				for (int i = 0; i < EvaluationConstants.NUMBER_HOOVE_AGENTS; i++) {
					HooveRobotAgent hra = createHooveAgent(world);
					hra.addWithoutMasterConfiguration();
					population.add(hra);
				}
				
				if (EvaluationConstants.NUMBER_WIPE_AGENTS > 0) {
					
					//wipe agents
					for (int i = 0; i < EvaluationConstants.NUMBER_WIPE_AGENTS; i++) {
						WipeRobotAgent wra = createWipeAgent(world);
						wra.addWithoutMasterConfiguration();
						population.add(wra);
					}
				}
			}			
		}
		
		return population;
	}
}

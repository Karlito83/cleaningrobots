package de.nec.nle.siafu.control;

import de.nec.nle.siafu.behaviormodels.BaseAgentModelMulti;
import de.nec.nle.siafu.model.SimulationData;
import de.nec.nle.siafu.model.MultiWorld;
import de.tud.evaluation.WorkingConfiguration;

public class MultiSimulation implements Runnable {

	/**
	 * Whether the simulation is already running.
	 */
	private boolean simulationRunning;
	
	private boolean endSimulation;
	
	public void endSimulation () {
		this.endSimulation = true;
	}

	/**
	 * Find out if the simulation is already running.
	 * 
	 * @return true if the simulation is running
	 */
	public boolean isSimulationRunning() {
		return simulationRunning;
	}
		
	private MultiWorld world;

	/**
	 * Build a <code>Simulation</code> object and start a thread that
	 * governs it.
	 * 
	 * @param simulationPath the path to the simulation data, which includes
	 *            maps, sprites, behavior models, etc...
	 * @param control the simulation <code>Controller</code>
	 */
	public MultiSimulation(WorkingConfiguration configuration, SimulationData simData) {
		this.simulationRunning = true;
		this.endSimulation = false;
		
		this.world = new MultiWorld(simData, configuration);
		System.out.println("World created: " + configuration.toString());
						
		new Thread(this, "Simulation thread").start();
	}
	
	/**
	 * Starts the Evaluation phase. With Console.
	 */
	public void run() {
		BaseAgentModelMulti agentModel = world.getAgentModel();
		while (!agentModel.isRunFinish() && !this.endSimulation) {
			world.wander();
		}
		simulationRunning = false;
	}
	
	
}

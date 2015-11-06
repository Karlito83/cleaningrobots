package de.nec.nle.siafu.control;

import de.nec.nle.siafu.behaviormodels.BaseAgentModelMulti;
import de.nec.nle.siafu.model.SimulationData;
import de.nec.nle.siafu.model.MultiWorld;
import de.tud.evaluation.WorkingConfiguration;

public class MultiSimulation implements Runnable {

	/**
	 * The jar file or folder containing the simulation data.
	 */
	private SimulationData simData;

	/**
	 * The simulation's world. This is only a reference to the
	 * <code>World</code> in <code>control</code>.
	 */
	private MultiWorld world;

	/**
	 * The Agent Model, as defined by the configuration file.
	 * <p>
	 * The agent model determines the behaviour of an agent, deciding what he
	 * does next at each iteration, and how it changes over time.
	 */
	private BaseAgentModelMulti agentModel;

	/**
	 * Whether the simulation is already running.
	 */
	private boolean simulationRunning;

	/**
	 * Find out if the simulation is already running.
	 * 
	 * @return true if the simulation is running
	 */
	public boolean isSimulationRunning() {
		return simulationRunning;
	}
	
	private WorkingConfiguration configuration;

	/**
	 * Build a <code>Simulation</code> object and start a thread that
	 * governs it.
	 * 
	 * @param simulationPath the path to the simulation data, which includes
	 *            maps, sprites, behavior models, etc...
	 * @param control the simulation <code>Controller</code>
	 */
	public MultiSimulation(WorkingConfiguration configuration) {
		String simulationPath = "C:\\Users\\ChrissiMobil\\git\\cleaningrobots\\source\\miquelmartin-Siafu-63de813\\Simulations\\Simulation-Testland\\target\\classes";
		//simulationPath = "C:\\Users\\cwerner\\Documents\\cleaningrobots-master\\source\\miquelmartin-Siafu-63de813\\Simulations\\Simulation-Testland\\target\\classes";
		this.configuration = configuration;
		
		this.simulationRunning = true;
		
		this.simData = SimulationData.getInstance(simulationPath);
				
		new Thread(this, "Simulation thread").start();
	}

	/**
	 * Get the simulation's world.
	 * 
	 * @return the simulation's world
	 */
	public MultiWorld getWorld() {
		return world;
	}
	
	/**
	 * Starts the Evaluation phase. With Console.
	 */
	public void run() {
		this.world = new MultiWorld(simData, configuration);
		System.out.println("World created: " + configuration.toString());
		this.agentModel = world.getAgentModel();
		while (!agentModel.isRunFinish()) {
			world.wander();
		}
		simulationRunning = false;
	}	

	/**
	 * Get the Simulation's Data object.
	 * 
	 * @return the SimulationData for the running simulation
	 */
	public SimulationData getSimulationData() {
		return simData;
	}
}

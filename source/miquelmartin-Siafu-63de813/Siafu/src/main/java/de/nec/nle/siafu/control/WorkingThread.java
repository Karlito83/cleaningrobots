package de.nec.nle.siafu.control;

import de.nec.nle.siafu.model.World;

public class WorkingThread implements Runnable{

	private boolean isRunning = true;
	
	private int configuration;
		
	public WorkingThread(int configuration) {
		this.configuration = configuration;
	}
	
	@Override
	public void run() {
		/*
		this.world = new World(this, simData);
		this.time = world.getTime();
		this.iterationStep = simulationConfig.getInt("iterationstep");
		this.agentModel = world.getAgentModel();
		this.worldModel = world.getWorldModel();
		this.contextModel = world.getContextModel();
		
		
		Controller.getProgress().reportSimulationStarted();
		simulationRunning = true;
		while (!agentModel.isRunFinish()) {
			if (!isPaused()) {
				tickTime();
				worldModel.doIteration(world.getPlaces());
				agentModel.doIteration(world.getPeople());
				contextModel.doIteration(world.getOverlays());
			}
			//makes the drawing on the gui is important
			control.scheduleDrawing();				
		}
		simulationRunning = false;
		Controller.getProgress().reportSimulationEnded();
		this.isRunning = false;
	*/}

}

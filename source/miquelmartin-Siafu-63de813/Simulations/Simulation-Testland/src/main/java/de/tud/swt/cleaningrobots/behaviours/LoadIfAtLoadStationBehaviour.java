package de.tud.swt.cleaningrobots.behaviours;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.RobotCore;

/**
 * Set loading if the robot arrived at load station.
 * 
 * @author Christopher Werner
 *
 */
public class LoadIfAtLoadStationBehaviour extends Behaviour {
	
	public LoadIfAtLoadStationBehaviour(RobotCore robot) {
		super(robot);		
	}
	
	@Override
	protected void addSupportedStates() {
		//no states needed...		
	}

	@Override
	protected void addHardwareComponents() {
		//no hardware components needed...		
	}

	@Override
	public boolean action() throws Exception {

		//if at load destination activate loading
		if (robot.getDestinationContainer().isAtLoadDestination())
		{
			robot.isLoading = true;
		}		
		return false;
	}
}

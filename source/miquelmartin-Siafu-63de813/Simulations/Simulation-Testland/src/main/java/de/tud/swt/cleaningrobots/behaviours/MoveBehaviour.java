package de.tud.swt.cleaningrobots.behaviours;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.hardware.ComponentTypes;

/**
 * Behavior that realize the move of an robot to a destination. 
 * Every time it drives one step to the new destination. 
 * 
 * @author Christopher Werner
 *
 */
public class MoveBehaviour extends Behaviour {
		
	public MoveBehaviour(RobotCore robot) {
		super(robot);
	}
	
	@Override
	protected void addSupportedStates() {
		//no states needed...		
	}

	@Override
	protected void addHardwareComponents() {
		this.d.addDemandPair(ComponentTypes.ACTUATOR, 1);
	}

	@Override
	public boolean action() throws Exception {
		
		//if he is not at the destination and not loading he should move
		if (!robot.getDestinationContainer().isAtDestination() && !robot.isLoading()){
			//start all hardware components
			this.d.switchAllOn();
			
			//make the move
			robot.getDestinationContainer().moveTowardsDestination(false);
		} else {
			//switch off all hardware components
			this.d.switchAllOff();
		}		
		return false;
	}

	@Override
	public void initialiseBehaviour() {
		//do nothing before first start		
	}
}

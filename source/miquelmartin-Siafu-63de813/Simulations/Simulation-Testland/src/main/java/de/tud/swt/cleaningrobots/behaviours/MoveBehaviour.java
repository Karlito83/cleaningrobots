package de.tud.swt.cleaningrobots.behaviours;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Robot;

public class MoveBehaviour extends Behaviour {
	
	public MoveBehaviour(Robot robot) {
		super(robot);
	}

	@Override
	public boolean action() throws Exception {
		boolean result = false;
		
		logger.trace("Entered Movebehaviour");
		
		if (!getRobot().isAtDestination()){
			getRobot().moveTowardsDestination();
			result = true;
			logger.info("Executed Movebehaviour");
		}
		
		logger.trace("Ended Movebehaviour");
		

		return result;
	}

}

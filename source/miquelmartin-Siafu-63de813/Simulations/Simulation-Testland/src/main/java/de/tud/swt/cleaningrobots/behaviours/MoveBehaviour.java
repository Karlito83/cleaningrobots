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
		
		//System.err.println("Movebehaviour action start");
		
		if (!getRobot().isAtDestination()){
			System.err.println("Movebehaviour action actually moving?");
			getRobot().moveTowardsDestination();
			result = true;
		}
		
		//System.err.println("Movebehaviour action end");

		return result;
	}

}

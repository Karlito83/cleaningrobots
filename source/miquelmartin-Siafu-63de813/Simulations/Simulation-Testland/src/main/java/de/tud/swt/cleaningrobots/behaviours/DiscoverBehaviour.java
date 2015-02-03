package de.tud.swt.cleaningrobots.behaviours;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Position;
import de.tud.swt.cleaningrobots.Robot;

public class DiscoverBehaviour extends Behaviour {
	
	public DiscoverBehaviour(Robot robot) {
		super(robot);
	}

	@Override
	public boolean action() throws Exception {
		boolean result = false;
		
		logger.trace("Entered DiscoverBehaviour.action().");
		
		if(getRobot().isAtDestination()){
			
			Position nextUnknownPosition = this.getRobot().getWorld().getNextUnknownFieldPosition(); 
			if(nextUnknownPosition != null){
				getRobot().setDestination(nextUnknownPosition);
				result = true;
				logger.info("Executed DiscoverBehaviour.action().");
			}
		}
		else {
			result = false;
		}
		
		logger.trace("Ended DiscoverBehaviour.action().");
		
		return result;		
	}

}

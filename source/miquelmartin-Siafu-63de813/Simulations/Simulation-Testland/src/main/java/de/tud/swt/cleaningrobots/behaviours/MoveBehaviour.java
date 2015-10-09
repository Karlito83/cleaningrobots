package de.tud.swt.cleaningrobots.behaviours;

import java.util.EnumMap;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;

public class MoveBehaviour extends Behaviour {
		
	public MoveBehaviour(RobotCore robot) {
		super(robot);
		
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		hardware.put(Components.MOTOR, 1);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
	}

	@Override
	public boolean action() throws Exception {
		
		logger.trace("Entered Movebehaviour");
		
		//wenn er nicht am Ziel ist und nicht am Laden ist soll er laufen
		if (!getRobot().getDestinationContainer().isAtDestination() && !getRobot().isLoading){
			//start all hardwarecomponents if not active
			for (HardwareComponent hard : d.getHcs())
			{
				if (!hard.isActive())
				{
					hard.changeActive();
				}
			}
			
			//make the move
			getRobot().getDestinationContainer().moveTowardsDestination();
			logger.info("Executed Movebehaviour");
		} else {
			//Schalte alle Hardwarecomponenten aus
			for (HardwareComponent hard : d.getHcs())
			{
				if (hard.isActive())
				{
					hard.changeActive();
				}
			}
		}
		
		logger.trace("Ended Movebehaviour");		

		return false;
	}

}

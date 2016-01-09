package de.tud.swt.cleaningrobots.behaviours;

import java.util.EnumMap;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;

/**
 * Behavior that realize the move of an robot to a destination only with the master information. 
 * Every time it drives one step to the new destination. 
 * 
 * @author Christopher Werner
 *
 */
public class MasterMoveBehaviour extends Behaviour {

	public MasterMoveBehaviour(RobotCore robot) {
		super(robot);
		
		//add hardware components
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		hardware.put(Components.MOTOR, 1);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
	}

	@Override
	public boolean action() throws Exception {
		
		//if he is not at the destination and not loading he should move
		if (!robot.getDestinationContainer().isAtDestination() && !robot.isLoading){
			//start all hardware components
			for (HardwareComponent hard : d.getHcs())
			{
				hard.switchOn();
			}
			
			//make the move
			robot.getDestinationContainer().moveTowardsDestinationWithoutInformation();
		} else {
			//switch off all hardware components
			for (HardwareComponent hard : d.getHcs())
			{
				hard.switchOff();
			}
		}	
		return false;
	}
}

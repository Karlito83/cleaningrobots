package de.tud.swt.cleaningrobots.behaviours;

import java.util.EnumMap;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;

public class MasterMoveBehaviour extends Behaviour {

	public MasterMoveBehaviour(RobotCore robot) {
		super(robot);
		
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		hardware.put(Components.MOTOR, 1);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
	}

	@Override
	public boolean action() throws Exception {
		
		//wenn er nicht am Ziel ist und nicht am Laden ist soll er laufen
		if (!robot.getDestinationContainer().isAtDestination() && !robot.isLoading){
			//start all hardwarecomponents if not active
			for (HardwareComponent hard : d.getHcs())
			{
				if (!hard.isActive())
				{
					hard.changeActive();
				}
			}
			
			//make the move
			robot.getDestinationContainer().moveTowardsDestinationWithoutInformation();
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

		return false;
	}
}

package de.tud.swt.cleaningrobots.behaviours;

import java.util.EnumMap;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.hardware.Components;

public class LoadIfAtLoadStationBehaviour extends Behaviour {
	
	public LoadIfAtLoadStationBehaviour(RobotCore robot) {
		super(robot);

		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
		
	}

	@Override
	public boolean action() throws Exception {

		//if at load destination activate loading
		if (getRobot().getDestinationContainer().isAtLoadDestination())
		{
			getRobot().isLoading = true;
		}		
		return false;
	}
}

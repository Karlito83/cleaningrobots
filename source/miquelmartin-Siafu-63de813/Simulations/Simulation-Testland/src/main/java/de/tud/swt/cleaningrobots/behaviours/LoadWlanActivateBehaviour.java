package de.tud.swt.cleaningrobots.behaviours;

import java.util.EnumMap;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;
import de.tud.swt.cleaningrobots.hardware.Wlan;

/**
 * Activate the WLAN component if the robot is loading.
 * Switch off the WLAN component if it is not loading.
 *   
 * @author Christopher Werner
 *
 */
public class LoadWlanActivateBehaviour extends Behaviour {

	private Wlan wlan;
	
	public LoadWlanActivateBehaviour(RobotCore robot) {
		super(robot);

		//add hardware components
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		hardware.put(Components.WLAN, 1);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
		
		//get vision Radius from WLAN hardware component
		for (HardwareComponent hard : d.getHcs())
		{
			if (hard.getComponents() == Components.WLAN)
			{
				wlan = (Wlan)hard;
			}
		}
		
	}

	@Override
	public boolean action() throws Exception {

		//if isLoading switch on WLAN
		if (robot.isLoading)
		{
			wlan.switchOn();
		} else {
			//if not isLoading switch off WLAN
			wlan.switchOff();
		}
		
		return false;
	}

}

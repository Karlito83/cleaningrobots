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
 * Activate the WLAN component of the robot.
 * 
 * @author Christopher Werner
 *
 */
public class WlanOnBehaviour extends Behaviour {
	
	private Wlan wlan;
	
	public WlanOnBehaviour(RobotCore robot) {
		super(robot);
		
		//proof hardware components and create the demand
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		hardware.put(Components.WLAN, 1);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
		
		//save WLAN component for activation
		for (HardwareComponent hard : d.getHcs())
		{
			if (hard.getComponents() == Components.WLAN)
			{
				wlan = (Wlan)hard;
			}
		}
	}

	@Override
	public boolean action() {		
		//activate WLAN component
		wlan.switchOn();
		return false;
	}
}

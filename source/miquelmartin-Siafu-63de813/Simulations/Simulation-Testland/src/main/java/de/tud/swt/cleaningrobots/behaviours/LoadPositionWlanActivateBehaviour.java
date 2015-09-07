package de.tud.swt.cleaningrobots.behaviours;

import java.util.EnumMap;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;
import de.tud.swt.cleaningrobots.hardware.Wlan;

public class LoadPositionWlanActivateBehaviour extends Behaviour {
	
	private Wlan wlan;

	public LoadPositionWlanActivateBehaviour(RobotCore robot) {
		super(robot);
		
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		hardware.put(Components.WLAN, 1);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
		
		//Vision Radius aus Wlan Hardwarecomponente ziehen
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
		//if isLoading mache Wlan an
		if (getRobot().getPosition().equals(getRobot().getDestinationContainer().getLoadStationPosition()))
		{
			if (!wlan.isActive())
			{
				wlan.changeActive();
			}
		} else {
			//if nicht mehr isLoading mache Wlan aus
			if (wlan.isActive())
			{
				wlan.changeActive();
			}
		}
				
		return false;
	}

}

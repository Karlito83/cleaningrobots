package de.tud.swt.cleaningrobots.behaviours;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.hardware.Components;

/**
 * Activate the WLAN component of the robot.
 * 
 * @author Christopher Werner
 *
 */
public class WlanOnBehaviour extends Behaviour {
	
	public WlanOnBehaviour(RobotCore robot) {
		super(robot);		
	}
	
	@Override
	protected void addSupportedStates() {
		//no states needed...		
	}

	@Override
	protected void addHardwareComponents() {
		this.d.addDemandPair(Components.WLAN, 1);
	}

	@Override
	public boolean action() {		
		//activate WLAN component
		this.d.switchAllOn();
		return false;
	}
}

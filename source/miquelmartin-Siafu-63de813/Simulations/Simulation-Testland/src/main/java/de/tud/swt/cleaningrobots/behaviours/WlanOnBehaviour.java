package de.tud.swt.cleaningrobots.behaviours;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.hardware.ComponentTypes;

/**
 * Activate the WLAN component of the robot.
 * 
 * @author Christopher Werner
 *
 */
public class WlanOnBehaviour extends Behaviour {
	
	public WlanOnBehaviour(AgentRole role) {
		super(role);		
	}
	
	@Override
	protected void addSupportedStates() {
		//no states needed...		
	}

	@Override
	protected void addHardwareComponents() {
		this.demand.addDemandPair(ComponentTypes.WLAN, 1);
	}

	@Override
	public boolean action() {		
		//activate WLAN component
		this.demand.switchAllOn();
		return false;
	}

	@Override
	public void initialiseBehaviour() {
		//do nothing before first start		
	}
}

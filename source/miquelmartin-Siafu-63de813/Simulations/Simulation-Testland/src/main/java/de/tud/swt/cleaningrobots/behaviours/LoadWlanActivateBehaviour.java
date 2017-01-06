package de.tud.swt.cleaningrobots.behaviours;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.hardware.ComponentTypes;

/**
 * Activate the WLAN component if the robot is loading.
 * Switch off the WLAN component if it is not loading.
 *   
 * @author Christopher Werner
 *
 */
public class LoadWlanActivateBehaviour extends Behaviour {

	public LoadWlanActivateBehaviour(AgentRole role) {
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
	public boolean action() throws Exception {

		//isLoading switch on or off
		if (agentCore.isLoading())
		{
			//switch on hardware components
			this.demand.switchAllOn();
		} 
		else 
		{
			//switch off hardware components
			this.demand.switchAllOff();
		}
		
		return false;
	}

	@Override
	public void initialiseBehaviour() {
		//do nothing before first start		
	}

}

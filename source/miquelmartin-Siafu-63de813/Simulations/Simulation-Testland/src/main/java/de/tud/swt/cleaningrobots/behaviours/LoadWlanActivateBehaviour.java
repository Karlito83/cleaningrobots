package de.tud.swt.cleaningrobots.behaviours;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.hardware.ComponentTypes;

/**
 * Activate the WLAN component if the robot is loading.
 * Switch off the WLAN component if it is not loading.
 *   
 * @author Christopher Werner
 *
 */
public class LoadWlanActivateBehaviour extends Behaviour {

	public LoadWlanActivateBehaviour(RobotCore robot) {
		super(robot);		
	}
	
	@Override
	protected void addSupportedStates() {
		//no states needed...		
	}

	@Override
	protected void addHardwareComponents() {
		this.d.addDemandPair(ComponentTypes.WLAN, 1);		
	}

	@Override
	public boolean action() throws Exception {

		//isLoading switch on or off
		if (robot.isLoading())
		{
			//switch on hardware components
			this.d.switchAllOn();
		} 
		else 
		{
			//switch off hardware components
			this.d.switchAllOff();
		}
		
		return false;
	}

	@Override
	public void initialiseBehaviour() {
		//do nothing before first start		
	}

}

package de.tud.swt.cleaningrobots.hardware;

/**
 * Energy On state.
 * 
 * @author Christopher Werner
 *
 */
public class EnergyOn implements EnergyState{

	@Override
	public void switchOn(HardwareComponent hc) {
		hc.setActualEnergy(hc.getWorkEnergy());		
	}

	@Override
	public void switchOff(HardwareComponent hc) {
		hc.setActualEnergy(hc.getOffEnergy());
		hc.setState(new EnergyOff());		
	}

	@Override
	public String toString() {
		return "Online";
	}

	@Override
	public boolean isActive() {
		return true;
	}	
}

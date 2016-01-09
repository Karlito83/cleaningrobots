package de.tud.swt.cleaningrobots.hardware;

/**
 * Energy Off state.
 * 
 * @author Christopher Werner
 *
 */
public class EnergyOff implements EnergyState {

	@Override
	public void switchOn(HardwareComponent hc) {
		hc.setActualEnergy(hc.getOnEnergy());
		hc.setState(new EnergyOn());
		
	}

	@Override
	public void switchOff(HardwareComponent hc) {
		hc.setActualEnergy(hc.getOutEnergy());
	}

	@Override
	public String toString() {
		return "Offline";
	}

	@Override
	public boolean isActive() {
		return false;
	}
}

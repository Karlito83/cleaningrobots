package de.tud.swt.cleaningrobots.hardware;

/**
 * State interface for the state pattern.
 * 
 * @author Christopher Werner
 *
 */
public interface EnergyState {
	
	public void switchOn(HardwareComponent hc);
	public void switchOff(HardwareComponent hc);
	public boolean isActive();
}

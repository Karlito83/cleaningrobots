package de.tud.swt.cleaningrobots.hardware;

public class Wlan extends HardwareComponent {

	private int visionRadius;
	
	public Wlan ()
	{
		super();
		name = "Wlan";
		offEnergie = 0.1;
		onEnergie = 0.7;
		workEnergie = 0.5;
		outEnergie = 0.0;
		
		visionRadius = 1;
	}
	
	public int getVisionRadius () {
		return visionRadius;
	}

	@Override
	public Components getComponents() {
		return Components.WLAN;
	}
}

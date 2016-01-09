package de.tud.swt.cleaningrobots.hardware;

/**
 * The WLAN component of a robot.
 * 
 * @author Christopher Werner
 *
 */
public class Wlan extends HardwareComponent {

	private int visionRadius;
	
	public Wlan ()
	{
		super();
		name = "Wlan";
		//0
		offEnergie = 0.0;
		//1Wh
		onEnergie = caluculateEnergie(1.0);
		//1Wh
		workEnergie = caluculateEnergie(1.0);
		//0
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

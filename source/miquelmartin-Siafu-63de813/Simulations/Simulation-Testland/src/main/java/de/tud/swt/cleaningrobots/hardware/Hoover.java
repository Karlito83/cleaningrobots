package de.tud.swt.cleaningrobots.hardware;

/**
 * The Hoover component of a robot.
 * 
 * @author Christopher Werner
 *
 */
public class Hoover extends MeasurementRangeHardwareComponent {

	public Hoover ()
	{
		super("HOOVER", 2);
		//0
		offEnergie = 0.0;
		//13Wh
		onEnergie = caluculateEnergie(13.0);
		//13Wh
		workEnergie = caluculateEnergie(13.0);
		//0
		outEnergie = 0.0;
	}

	@Override
	public ComponentTypes getComponentType() {
		return ComponentTypes.HOOVER;
	}
}

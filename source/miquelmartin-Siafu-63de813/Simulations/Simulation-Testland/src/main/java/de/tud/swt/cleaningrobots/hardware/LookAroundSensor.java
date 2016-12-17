package de.tud.swt.cleaningrobots.hardware;

/**
 * The laser scanner component of a robot.
 * 
 * @author Christopher Werner
 *
 */
public class LookAroundSensor extends MeasurementRangeHardwareComponent {

	public LookAroundSensor ()
	{
		super("LOOKAROUNDSENSOR", 2);
		//0
		offEnergie = 0.0;
		//15Wh
		onEnergie = caluculateEnergie(15.0);
		//6Wh
		workEnergie = caluculateEnergie(6.0);
		//0
		outEnergie = 0.0;
	}

	@Override
	public ComponentTypes getComponentType() {
		return ComponentTypes.LOOKAROUNDSENSOR;
	}
}

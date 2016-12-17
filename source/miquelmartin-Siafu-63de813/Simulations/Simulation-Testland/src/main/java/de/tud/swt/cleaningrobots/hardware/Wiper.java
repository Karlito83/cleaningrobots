package de.tud.swt.cleaningrobots.hardware;

/**
 * The Wiper component of a robot.
 * 
 * @author Christopher Werner
 *
 */
public class Wiper extends MeasurementRangeHardwareComponent {

	public Wiper ()
	{
		super("WIPER", 2);
		//0
		offEnergie = 0.0;
		//19Wh
		onEnergie = caluculateEnergie(19.0);
		//19Wh
		workEnergie = caluculateEnergie(19.0);
		//19Wh
		outEnergie = 0.0;
	}

	@Override
	public ComponentTypes getComponentType() {
		return ComponentTypes.WIPER;
	}
}

package de.tud.swt.cleaningrobots.hardware;

/**
 * The load station component of a robot.
 * 
 * @author Christopher Werner
 *
 */
public class LoadStation extends MeasurementRangeHardwareComponent{

	private int loadValue;
	
	public LoadStation ()
	{
		super("LOADSTATION", 0);
		offEnergie = 0.0;
		onEnergie = 0.0;
		workEnergie = 0.0;
		outEnergie = 0.0;
		
		loadValue = 80;
	}
	
	public double getLoadValue () {
		return caluculateEnergie(loadValue);
	}
	
	@Override
	public ComponentTypes getComponentType() {
		return ComponentTypes.LOADSTATION;
	}

}

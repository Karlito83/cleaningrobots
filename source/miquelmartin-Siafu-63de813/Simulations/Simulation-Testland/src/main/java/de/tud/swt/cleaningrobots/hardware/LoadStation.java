package de.tud.swt.cleaningrobots.hardware;

/**
 * The load station component of a robot.
 * 
 * @author Christopher Werner
 *
 */
public class LoadStation extends HardwareComponent{

	private int loadValue;
	private int loadRadius;
	
	public LoadStation ()
	{
		super("LOADSTATION");
		offEnergie = 0.0;
		onEnergie = 0.0;
		workEnergie = 0.0;
		outEnergie = 0.0;
		
		loadValue = 80;
		loadRadius = 0;
	}
	
	public double getLoadValue () {
		return caluculateEnergie(loadValue);
	} 
	
	public int getLoadRadius () {
		return loadRadius;
	}
	
	@Override
	public ComponentTypes getComponentType() {
		return ComponentTypes.LOADSTATION;
	}

}

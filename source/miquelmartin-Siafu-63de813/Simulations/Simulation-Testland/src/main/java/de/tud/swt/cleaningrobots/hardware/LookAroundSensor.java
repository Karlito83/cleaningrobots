package de.tud.swt.cleaningrobots.hardware;

/**
 * The laser scanner component of a robot.
 * 
 * @author Christopher Werner
 *
 */
public class LookAroundSensor extends HardwareComponent {

	private int radius;
	
	public LookAroundSensor ()
	{
		super("LOOKAROUNDSENSOR");
		//0
		offEnergie = 0.0;
		//15Wh
		onEnergie = caluculateEnergie(15.0);
		//6Wh
		workEnergie = caluculateEnergie(6.0);
		//0
		outEnergie = 0.0;
		
		radius = 2;
	}
	
	public int getRadius ()	{
		return radius;
	}

	@Override
	public ComponentTypes getComponentType() {
		return ComponentTypes.LOOKAROUNDSENSOR;
	}
}

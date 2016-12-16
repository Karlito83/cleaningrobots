package de.tud.swt.cleaningrobots.hardware;

/**
 * The Wiper component of a robot.
 * 
 * @author Christopher Werner
 *
 */
public class Wiper extends HardwareComponent {

	private int radius;
	
	public Wiper ()
	{
		super("WIPER");
		//0
		offEnergie = 0.0;
		//19Wh
		onEnergie = caluculateEnergie(19.0);
		//19Wh
		workEnergie = caluculateEnergie(19.0);
		//19Wh
		outEnergie = 0.0;
		
		radius = 2;
	}
	
	public int getRadius ()
	{
		return radius;
	}

	@Override
	public ComponentTypes getComponentType() {
		return ComponentTypes.WIPER;
	}
}

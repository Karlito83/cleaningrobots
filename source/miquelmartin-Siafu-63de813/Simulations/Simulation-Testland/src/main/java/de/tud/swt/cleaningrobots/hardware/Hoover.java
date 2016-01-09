package de.tud.swt.cleaningrobots.hardware;

/**
 * The Hoover component of a robot.
 * 
 * @author Christopher Werner
 *
 */
public class Hoover extends HardwareComponent {

	private int radius;
	
	public Hoover ()
	{
		super();
		name = "Hoover";
		//0
		offEnergie = 0.0;
		//13Wh
		onEnergie = caluculateEnergie(13.0);
		//13Wh
		workEnergie = caluculateEnergie(13.0);
		//0
		outEnergie = 0.0;
		
		radius = 2;
	}
	
	public int getRadius ()	{
		return radius;
	}

	@Override
	public Components getComponents() {
		return Components.HOOVER;
	}
}

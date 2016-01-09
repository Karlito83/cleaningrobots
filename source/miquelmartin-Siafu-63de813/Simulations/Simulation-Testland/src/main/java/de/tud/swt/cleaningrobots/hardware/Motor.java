package de.tud.swt.cleaningrobots.hardware;

/**
 * The Motor component of a robot.
 * 
 * @author Christopher Werner
 *
 */
public class Motor extends HardwareComponent {

	public Motor ()
	{
		super();
		name = "Motor";
		//0
		offEnergie = 0.0;
		//19Wh
		onEnergie = caluculateEnergie(19.0);
		//19Wh
		workEnergie = caluculateEnergie(19.0);
		//0
		outEnergie = 0.0;
	}

	@Override
	public Components getComponents() {
		return Components.MOTOR;
	}
}

package de.tud.swt.cleaningrobots.hardware;

public class Motor extends HardwareComponent {

	public Motor ()
	{
		super();
		name = "Motor";
		offEnergie = 0.1;
		onEnergie = 0.7;
		workEnergie = 0.5;
		outEnergie = 0.0;
	}

	@Override
	public Components getComponents() {
		return Components.MOTOR;
	}
}

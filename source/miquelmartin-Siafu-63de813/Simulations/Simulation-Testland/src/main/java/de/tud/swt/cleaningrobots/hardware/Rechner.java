package de.tud.swt.cleaningrobots.hardware;

public class Rechner extends HardwareComponent {

	public Rechner ()
	{
		super();
		name = "Rechner";
		//alle gleich da immer an
		offEnergie = 0.5;
		onEnergie = 0.5;
		workEnergie = 0.5;
		outEnergie = 0.5;
	}
	
	@Override
	public Components getComponents() {
		return Components.RECHNER;
	}

}

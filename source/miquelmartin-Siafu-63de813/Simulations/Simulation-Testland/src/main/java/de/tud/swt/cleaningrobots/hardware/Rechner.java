package de.tud.swt.cleaningrobots.hardware;

public class Rechner extends HardwareComponent {

	public Rechner ()
	{
		super();
		name = "Rechner";
		//0,5Wh
		offEnergie = caluculateEnergie(5.0);
		//5Wh
		onEnergie = caluculateEnergie(5.0);
		//5Wh
		workEnergie = caluculateEnergie(5.0);
		//5Wh
		outEnergie = caluculateEnergie(0.5);
	}
	
	@Override
	public Components getComponents() {
		return Components.RECHNER;
	}

}

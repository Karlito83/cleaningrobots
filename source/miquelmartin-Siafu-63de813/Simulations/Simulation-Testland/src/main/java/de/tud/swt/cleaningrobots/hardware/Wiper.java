package de.tud.swt.cleaningrobots.hardware;

public class Wiper extends HardwareComponent {

	private int radius;
	
	public Wiper ()
	{
		super();
		name = "Wiper";
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
	public Components getComponents() {
		return Components.WIPER;
	}
}

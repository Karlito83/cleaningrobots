package de.tud.swt.cleaningrobots.hardware;

public class Hoover extends HardwareComponent {

	private int radius;
	
	public Hoover ()
	{
		super();
		name = "Hoover";
		offEnergie = 0.1;
		onEnergie = 0.7;
		workEnergie = 0.5;
		outEnergie = 0.0;
		
		radius = 2;
	}
	
	public int getRadius ()
	{
		return radius;
	}

	@Override
	public Components getComponents() {
		return Components.HOOVER;
	}
}

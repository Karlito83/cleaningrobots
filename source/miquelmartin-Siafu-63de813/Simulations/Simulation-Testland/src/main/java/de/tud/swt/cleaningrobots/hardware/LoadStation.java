package de.tud.swt.cleaningrobots.hardware;

public class LoadStation extends HardwareComponent{

	private double loadValue;
	private int loadRadius;
	
	public LoadStation ()
	{
		super();
		name = "LoadStation";
		offEnergie = 0.0;
		onEnergie = 0.0;
		workEnergie = 0.0;
		outEnergie = 0.0;
		
		loadValue = caluculateEnergie(80);
		loadRadius = 0;
	}
	
	public double getLoadValue () {
		return loadValue;
	} 
	
	public int getLoadRadius () {
		return loadRadius;
	}
	
	@Override
	public Components getComponents() {
		return Components.LOADSTATION;
	}

}

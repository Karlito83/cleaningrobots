package de.tud.swt.cleaningrobots.hardware;

public abstract class HardwareComponent {

	protected String name;
	
	private boolean active;
	private double actualEnergie;
	
	//Energieverbrauch wenn Komponente läuft
	protected double workEnergie;
	//Energieverbrauch wenn Komponente aus
	protected double outEnergie;
	//Energieverbrauch wenn Komponente eingeschaltet wird
	protected double onEnergie;
	//Energieverbrauch wenn Komponente ausgeschaltet wird
	protected double offEnergie;
	
	public HardwareComponent ()
	{
		active = false;
		actualEnergie = 0.0;
	}
	
	public String getName ()
	{
		return name;
	}
	
	public double getActualEnergie ()
	{
		double out = actualEnergie;
		if (active)
			actualEnergie = workEnergie;
		else
			actualEnergie = outEnergie;
		return out;
	}
	
	public boolean isActive ()
	{
		return active;
	}
	
	public void changeActive ()
	{
		if (active)
			actualEnergie = offEnergie;
		else
			actualEnergie = onEnergie;
		active = !active;
	}
	
	public double getMinEnergie () {
		if (workEnergie < offEnergie && workEnergie < onEnergie && workEnergie < outEnergie)
			return workEnergie;
		if (offEnergie < onEnergie && offEnergie < outEnergie)
			return offEnergie;
		if (onEnergie < outEnergie)
			return onEnergie;
		else
			return outEnergie;
	}
	
	public double getMaxEnergie () {
		if (workEnergie > offEnergie && workEnergie > onEnergie && workEnergie > outEnergie)
			return workEnergie;
		if (offEnergie > onEnergie && offEnergie > outEnergie)
			return offEnergie;
		if (onEnergie > outEnergie)
			return onEnergie;
		else
			return outEnergie;		
	}
	
	public abstract Components getComponents ();
	
}

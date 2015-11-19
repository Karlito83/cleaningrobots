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
		this.active = false;
		this.actualEnergie = 0.0;
	}
	
	protected double caluculateEnergie (double value) {
		return (value / 3600);
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
	
	public double getMinEnergie () 
	{
		return Math.min(Math.min(workEnergie, offEnergie), Math.min(onEnergie, outEnergie));
	}
	
	public double getMaxEnergie () 
	{
		return Math.max(Math.max(workEnergie, offEnergie), Math.max(onEnergie, outEnergie));	
	}
	
	public abstract Components getComponents ();
	
}

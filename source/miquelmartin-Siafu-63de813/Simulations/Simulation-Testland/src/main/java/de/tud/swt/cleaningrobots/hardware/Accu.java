package de.tud.swt.cleaningrobots.hardware;

public class Accu {
	
	private double maxKWh;
	private double minKWh;
	private double actualKWh;
	
	public Accu (double min, double max)
	{
		maxKWh = max;
		minKWh = min;
		actualKWh = max;
	}
	
	public Accu (double min, double max, double actual)
	{
		this(min,max);
		actualKWh = actual;
	}
	
	public void load (double loadKWh)
	{
		actualKWh += loadKWh;
		if (actualKWh > maxKWh)
		{
			actualKWh = maxKWh;
		}
	}

	public boolean isFull ()
	{
		return actualKWh == maxKWh;
	}
	
	public void use (double useKWh)
	{
		actualKWh -= useKWh;
		if (actualKWh < 0)
			actualKWh = 0;
	}
	
	public double getMinKWh ()
	{
		return minKWh;
	}
	
	public double getMaxKWh ()
	{
		return maxKWh;
	}
	
	public double getActualKWh ()
	{
		return actualKWh;
	}
	
	public double getRestKWh ()
	{
		return actualKWh - minKWh;
	}
	
	public double getMaxFieldGoes (double Energie)
	{
		return (maxKWh - minKWh) / Energie;
	}
}

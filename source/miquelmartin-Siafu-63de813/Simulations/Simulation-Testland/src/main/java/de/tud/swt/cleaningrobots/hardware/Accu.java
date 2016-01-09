package de.tud.swt.cleaningrobots.hardware;

/**
 * The Accu information and management of the robot.
 * 
 * @author Christopher Werner
 *
 */
public class Accu {
	
	//48 Wh
	private double maxWh;
	//10%
	private double minWh;
	private double actualWh;
	
	public Accu (double max)
	{
		actualWh = max;
		maxWh = max;
		minWh = 0.1 * max;
	}
	
	public Accu (double min, double max)
	{
		maxWh = max;
		minWh = min;
		actualWh = max;
	}
	
	public Accu (double min, double max, double actual)
	{
		this(min,max);
		actualWh = actual;
	}
	
	public void load (double loadKWh)
	{
		actualWh += loadKWh;
		if (actualWh > maxWh)
		{
			actualWh = maxWh;
		}
	}

	public boolean isFull ()
	{
		return actualWh == maxWh;
	}
	
	public void use (double useKWh)
	{
		actualWh -= useKWh;
		if (actualWh < 0)
			actualWh = 0;
	}
	
	public double getMinKWh ()
	{
		return minWh;
	}
	
	public double getMaxKWh ()
	{
		return maxWh;
	}
	
	public double getActualKWh ()
	{
		return actualWh;
	}
	
	public double getRestKWh ()
	{
		return actualWh - minWh;
	}
	
	public double getMaxFieldGoes (double Energie)
	{
		return (maxWh - minWh) / Energie;
	}
}

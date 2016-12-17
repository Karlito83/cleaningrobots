package de.tud.swt.cleaningrobots.hardware;

public abstract class MeasurementRangeHardwareComponent extends HardwareComponent {

	private int radius;
	
	public MeasurementRangeHardwareComponent (String name, int radius)
	{
		super(name);		
		this.radius = radius;
	}
	
	public int getMeasurementRange ()
	{
		return radius;
	}
}

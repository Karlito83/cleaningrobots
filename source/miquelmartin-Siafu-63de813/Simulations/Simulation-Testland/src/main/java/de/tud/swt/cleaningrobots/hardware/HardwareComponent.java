package de.tud.swt.cleaningrobots.hardware;

/**
 * The abstract class of a hardware component with the energy values.
 * 
 * @author Christopher Werner
 *
 */
public abstract class HardwareComponent {

	protected String name;
	
	private EnergyState state;
	private double actualEnergie;
	
	/**
	 * Energy consumption when the component runs
	 */
	protected double workEnergie;
	
	/**
	 * Energy consumption when the component is out
	 */
	protected double outEnergie;
	
	/**
	 * Energy consumption when the component turns on
	 */
	protected double onEnergie;
	
	/**
	 * Energy consumption when the component turns off
	 */
	protected double offEnergie;
		
	public HardwareComponent (String name)	{
		this.actualEnergie = 0.0;
		this.name = name;		
		setState(new EnergyOff());
	}
	
	public double getActualEnergy () {
		return this.actualEnergie;
	}
	
	public void setActualEnergy (double energy) {
		this.actualEnergie = energy;
	}
	
	public String getName () {
		return this.name;
	}
	
	public double getWorkEnergy () {
		return this.workEnergie;
	}
	
	public double getOutEnergy () {
		return this.outEnergie;
	}
	
	public double getOnEnergy () {
		return this.onEnergie;
	}
	
	public double getOffEnergy () {
		return this.offEnergie;
	}
	
	public void setState (EnergyState state) {
		this.state = state;
	}
	
	public void switchOn() {
		state.switchOn(this);
	}
	
	public void switchOff() {
		state.switchOff(this);
	}
	
	public boolean isActive () {
		return state.isActive();
	}
	
	public double getMinEnergie () {
		return Math.min(Math.min(workEnergie, offEnergie), Math.min(onEnergie, outEnergie));
	}
	
	public double getMaxEnergie () {
		return Math.max(Math.max(workEnergie, offEnergie), Math.max(onEnergie, outEnergie));	
	}
	
	public static double caluculateEnergie (double value) {
		return (value / 3600);
	}
	
	public abstract ComponentTypes getComponentType ();
	
}

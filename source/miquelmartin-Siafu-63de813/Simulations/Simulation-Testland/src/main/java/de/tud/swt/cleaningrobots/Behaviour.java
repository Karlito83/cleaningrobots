package de.tud.swt.cleaningrobots;

public abstract class Behaviour {
	
	private Robot robot;

	public Behaviour(Robot robot){
		this.robot = robot;
	}
	
	protected Robot getRobot() {
		return robot;
	}
	
	public abstract void action();
}

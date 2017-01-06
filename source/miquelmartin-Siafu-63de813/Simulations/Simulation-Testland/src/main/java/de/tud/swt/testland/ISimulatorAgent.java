package de.tud.swt.testland;

import de.tud.swt.cleaningrobots.AgentCore;

public interface ISimulatorAgent {

	public void wander();
	
	public boolean isFinish ();

	public AgentCore getRobot();

	public void setName(String name);
	
	public void setSpeed(int speed);	
}

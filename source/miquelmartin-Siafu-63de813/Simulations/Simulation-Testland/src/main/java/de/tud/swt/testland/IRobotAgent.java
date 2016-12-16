package de.tud.swt.testland;

import de.tud.swt.cleaningrobots.RobotCore;

public interface IRobotAgent {

	public void wander();
	
	public boolean isFinish ();

	public RobotCore getRobot();

	public void setName(String name);
	
	public void setSpeed(int speed);	
}

package de.tud.swt.cleaningrobots;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public abstract class Behaviour {
	
	private Robot robot;
	protected final Logger logger = LogManager.getRootLogger();

	public Behaviour(Robot robot){
		this.robot = robot;
	}
	
	protected Robot getRobot() {
		return robot;
	}
	
	/***
	 * Does the actions implemented by the behaviour
	 * @return <b>true</b> if the action is terminating the action sequence 
	 * or <b>false</b> if the action of the next behaviour can be performed   
	 * @throws Exception
	 */
	public abstract boolean action() throws Exception;
}

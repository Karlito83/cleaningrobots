package de.tud.swt.cleaningrobots;

import java.util.ArrayList;
import java.util.Collection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import de.tud.swt.cleaningrobots.model.State;

public abstract class Behaviour {
	
	private RobotCore robot;
	protected final Logger logger = LogManager.getRootLogger();
	
	protected Demand d;
	protected boolean hardwarecorrect;
	
	protected ArrayList<State> supportedStates;

	public Behaviour(RobotCore robot){
		this.robot = robot;
		this.supportedStates = new ArrayList<State>();
		hardwarecorrect = false;
	}
	
	protected RobotCore getRobot() {
		return robot;
	}
	
	/***
	 * Does the actions implemented by the behaviour
	 * @return <b>true</b> if the action is terminating the action sequence 
	 * or <b>false</b> if the action of the next behaviour can be performed   
	 * @throws Exception
	 */
	public abstract boolean action() throws Exception;
	
	public boolean isHardwarecorrect() {
		return hardwarecorrect;
	}
	
	/***
	 * Returns a copy of the SupportedStates Collection. 
	 */
	@SuppressWarnings("unchecked")
	public Collection<State> getSupportedStates ()	{
		return (Collection<State>) supportedStates.clone();
	}
}

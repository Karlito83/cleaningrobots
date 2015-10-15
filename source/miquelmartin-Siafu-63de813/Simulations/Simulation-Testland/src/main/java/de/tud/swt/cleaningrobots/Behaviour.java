package de.tud.swt.cleaningrobots;

import java.util.HashSet;
import java.util.Set;

import de.tud.swt.cleaningrobots.model.State;

public abstract class Behaviour {
	
	private RobotCore robot;
	
	protected Demand d;
	protected boolean hardwarecorrect;
	
	protected Set<State> supportedStates;

	public Behaviour(RobotCore robot){
		this.robot = robot;
		this.supportedStates = new HashSet<State>();
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
	public Set<State> getSupportedStates ()	{
		return supportedStates;
	}
}

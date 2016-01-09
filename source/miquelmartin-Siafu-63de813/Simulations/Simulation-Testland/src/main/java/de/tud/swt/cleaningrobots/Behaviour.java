package de.tud.swt.cleaningrobots;

import java.util.HashSet;
import java.util.Set;

import de.tud.swt.cleaningrobots.model.State;

/**
 * The abstract behavior class to create a behavior. 
 * 
 * @author Christopher Werner
 *
 */
public abstract class Behaviour {
	
	protected RobotCore robot;	
	protected Demand d;
	protected boolean hardwarecorrect;	
	protected Set<State> supportedStates;

	public Behaviour(RobotCore robot){
		this.robot = robot;
		this.supportedStates = new HashSet<State>();
		this.hardwarecorrect = false;
	}
	
	/**
	 * Does the actions implemented by the behaviour
	 * @return <b>true</b> if the action is terminating the action sequence 
	 * or <b>false</b> if the action of the next behaviour can be performed   
	 * @throws Exception
	 */
	public abstract boolean action() throws Exception;
	
	/**
	 * Gives back true if the robot has the needed Hardware.
	 * @return
	 */
	public boolean isHardwarecorrect() {
		return hardwarecorrect;
	}
	
	/**
	 * Returns a copy of the SupportedStates Collection. 
	 */
	public Set<State> getSupportedStates ()	{
		return supportedStates;
	}
}

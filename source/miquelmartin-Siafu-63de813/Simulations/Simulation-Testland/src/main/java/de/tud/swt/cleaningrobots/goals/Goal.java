package de.tud.swt.cleaningrobots.goals;

import java.util.Collection;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.model.State;

public abstract class Goal {

	//Robot
	private RobotCore robot;
	protected boolean optional;
	
	public Goal (RobotCore robot)
	{
		this.robot = robot;
	}
	
	public RobotCore getRobotCore() {
		return robot;
	}
	
	public abstract boolean isOptional ();
	
	public abstract void run();
	
	public abstract boolean preCondition ();
	
	public abstract boolean postCondition ();
	
	public abstract boolean isHardwareCorrect ();
	
	public abstract Collection<State> getSupportedStates();
	
	public boolean preConditionAndHardware () {
		return this.preCondition() && this.isHardwareCorrect();
	}
}

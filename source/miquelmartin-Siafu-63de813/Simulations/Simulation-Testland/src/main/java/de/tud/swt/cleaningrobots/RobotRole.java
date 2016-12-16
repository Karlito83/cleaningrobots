package de.tud.swt.cleaningrobots;

import java.util.ArrayList;
import java.util.List;

import de.tud.swt.cleaningrobots.goals.Goal;

/**
 * Abstract robot role for role-based pattern.
 * 
 * @author Christopher Werner
 *
 */
public abstract class RobotRole extends Robot {
	
	protected RobotCore core;
	private List<Goal> roleGoals;
	
	public RobotRole (RobotCore robotCore) {
		this.core = robotCore;
		this.roleGoals = new ArrayList<Goal>();
	}
	
	public abstract boolean createGoals();
	
	//public abstract void initializeGoals();
	
	protected boolean addGoals(Goal goal) {
		this.roleGoals.add(goal);
		return this.core.addGoal(goal);
	}
	
	/**
	 * Get the RobotCore object of this role.
	 * @return
	 */
	public RobotCore getRobotCore() {
		return core;
	}	

	@Override
	public boolean addRole(RobotRole role) {
		return this.core.addRole(role);
		
	}

	@Override
	public boolean hasRole(RobotRole role) {
		return this.core.hasRole(role);
	}

	@Override
	public boolean removeRole(RobotRole role) {
		return this.core.removeRole(role);		
	}
	
	@Override
	public String toString() {
		return core.getName() + super.toString();
	}

}

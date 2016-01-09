package de.tud.swt.cleaningrobots;

/**
 * Abstract robot role for role-based pattern.
 * 
 * @author Christopher Werner
 *
 */
public abstract class RobotRole extends Robot {
	
	private RobotCore core;
	
	public RobotRole (RobotCore robotCore) {
		core = robotCore;
	}
	
	/**
	 * Get the RobotCore object of this role.
	 * @return
	 */
	public RobotCore getRobotCore () {
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

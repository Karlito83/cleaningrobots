package de.tud.swt.cleaningrobots;

public abstract class RobotRole extends Robot {
	
	protected RobotCore core;
	
	public RobotRole (RobotCore robotCore) {
		core = robotCore;
	}
	
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
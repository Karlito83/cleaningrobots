package de.tud.swt.cleaningrobots;

public abstract class Robot {
	
	private boolean newInformation;	

	public boolean hasNewInformation () {
		return newInformation;
	}
	
	public void setNewInformation (boolean value) {
		newInformation = value;
	}
	
	public abstract boolean addRole (RobotRole role);	
	public abstract boolean hasRole(RobotRole role);
	public abstract boolean removeRole (RobotRole role);
	
	@Override
	public String toString() {
		return " NewInfo: " + newInformation;
	}	
}

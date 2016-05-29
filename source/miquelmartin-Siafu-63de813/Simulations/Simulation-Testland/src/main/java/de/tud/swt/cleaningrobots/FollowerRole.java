package de.tud.swt.cleaningrobots;

/**
 * The follower role has a master he knows.
 * 
 * @author Christopher Werner
 *
 */
public class FollowerRole extends RobotRole {
	
	private MasterRole master;

	public FollowerRole(RobotCore robotCore, MasterRole master) {
		super(robotCore);
		this.master = master;
	}
	
	public MasterRole getMaster () {
		return master;
	}
	
	
}

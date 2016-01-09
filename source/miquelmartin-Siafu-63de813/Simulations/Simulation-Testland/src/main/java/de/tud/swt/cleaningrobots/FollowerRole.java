package de.tud.swt.cleaningrobots;

/**
 * The follower role has a master he knows.
 * 
 * @author Christopher Werner
 *
 */
public class FollowerRole extends RobotRole {
	
	public MasterRole master;

	public FollowerRole(RobotCore robotCore) {
		super(robotCore);
	}
}

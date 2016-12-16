package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;

/**
 * The follower role has a master he knows.
 * 
 * @author Christopher Werner
 *
 */
public abstract class FollowerRole extends RobotRole {
	
	private MasterRole master;

	public FollowerRole(RobotCore robotCore, MasterRole master) {
		super(robotCore);
		this.master = master;
	}
	
	public MasterRole getMaster () {
		return master;
	}
	
	
}

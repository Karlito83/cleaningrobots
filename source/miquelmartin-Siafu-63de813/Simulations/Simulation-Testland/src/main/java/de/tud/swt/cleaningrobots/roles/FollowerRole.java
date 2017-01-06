package de.tud.swt.cleaningrobots.roles;

import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.AgentRole;

/**
 * The follower role has a master he knows.
 * 
 * @author Christopher Werner
 *
 */
public abstract class FollowerRole extends AgentRole {
	
	private MasterRole master;

	public FollowerRole(AgentCore robotCore, MasterRole master) {
		super(robotCore);
		this.master = master;
	}
	
	public MasterRole getMaster () {
		return master;
	}
	
	
}

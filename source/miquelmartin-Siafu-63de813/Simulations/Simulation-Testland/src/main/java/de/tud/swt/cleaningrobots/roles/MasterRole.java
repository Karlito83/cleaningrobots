package de.tud.swt.cleaningrobots.roles;

import java.util.ArrayList;
import java.util.List;

import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.AgentRole;

/**
 * The master role has a number of followers under him.
 * 
 * @author Christopher Werner
 *
 */
public abstract class MasterRole extends AgentRole {
	
	private List<AgentRole> followers;

	public MasterRole(AgentCore robotCore) {
		super(robotCore);		
		this.followers = new ArrayList<AgentRole>();
	}
	
	public List<AgentRole> getFollowers () {
		return this.followers;
	}
	
	public boolean addFollowerRole (FollowerRole follower)
	{
		return this.followers.add(follower);
	}

}

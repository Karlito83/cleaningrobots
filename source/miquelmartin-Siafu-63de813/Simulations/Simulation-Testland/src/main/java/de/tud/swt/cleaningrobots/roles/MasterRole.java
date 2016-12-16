package de.tud.swt.cleaningrobots.roles;

import java.util.ArrayList;
import java.util.List;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;

/**
 * The master role has a number of followers under him.
 * 
 * @author Christopher Werner
 *
 */
public abstract class MasterRole extends RobotRole {
	
	private List<RobotRole> followers;

	public MasterRole(RobotCore robotCore) {
		super(robotCore);		
		this.followers = new ArrayList<RobotRole>();
	}
	
	public List<RobotRole> getFollowers () {
		return this.followers;
	}
	
	public boolean addFollowerRole (FollowerRole follower)
	{
		return this.followers.add(follower);
	}

}

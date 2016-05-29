package de.tud.swt.cleaningrobots;

import java.util.ArrayList;
import java.util.List;

/**
 * The master role has a number of followers under him.
 * 
 * @author Christopher Werner
 *
 */
public class MasterRole extends RobotRole {
	
	private List<RobotRole> followers;

	public MasterRole(RobotCore robotCore) {
		super(robotCore);		
		this.followers = new ArrayList<RobotRole>();
	}
	
	public List<RobotRole> getFollowers () {
		return this.followers;
	}

}

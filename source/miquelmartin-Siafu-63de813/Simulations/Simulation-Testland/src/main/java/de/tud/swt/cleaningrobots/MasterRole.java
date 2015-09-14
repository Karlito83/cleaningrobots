package de.tud.swt.cleaningrobots;

import java.util.ArrayList;
import java.util.List;

public class MasterRole extends RobotRole {
	
	private List<RobotRole> followers;

	public MasterRole(RobotCore robotCore) {
		super(robotCore);
		
		followers = new ArrayList<RobotRole>();
	}
	
	public List<RobotRole> getFollowers () {
		return this.followers;
	}

}

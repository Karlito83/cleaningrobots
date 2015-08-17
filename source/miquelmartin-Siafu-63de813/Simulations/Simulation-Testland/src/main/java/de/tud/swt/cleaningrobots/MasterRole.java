package de.tud.swt.cleaningrobots;

import java.util.ArrayList;
import java.util.List;

public class MasterRole extends RobotRole {
	
	public List<FollowerRole> followers;

	public MasterRole(RobotCore robotCore) {
		super(robotCore);
		
		followers = new ArrayList<FollowerRole>();
	}

}

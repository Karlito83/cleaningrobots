package de.tud.swt.cleaningrobots.model;

import java.util.ArrayList;
import java.util.List;

/**
 * MasterRole Model only for the robot knowledge class.
 * 
 * @author Christopher Werner
 *
 */
public class MasterRoleModel extends RoleModel{

	public List<String> followers;
	
	public MasterRoleModel () {
		followers = new ArrayList<String>();
	}
}

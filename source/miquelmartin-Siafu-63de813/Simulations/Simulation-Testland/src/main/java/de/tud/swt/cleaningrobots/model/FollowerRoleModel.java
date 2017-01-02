package de.tud.swt.cleaningrobots.model;

/**
 * FollowerRole Model only for the robot knowledge class.
 * 
 * @author Christopher Werner
 *
 */
public class FollowerRoleModel extends RoleModel{
	
	private String master;
	
	public FollowerRoleModel(String roleName) {
		super(roleName);
	}
	
	public String getMaster () {
		return master;
	}

	public void setMaster (String master) {
		this.master = master;
	}
}

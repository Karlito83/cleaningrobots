package de.tud.swt.cleaningrobots.model;

import java.util.ArrayList;
import java.util.List;

public class MasterRoleModel extends RoleModel{

	public List<String> followers;
	
	public MasterRoleModel () {
		followers = new ArrayList<String>();
	}
}

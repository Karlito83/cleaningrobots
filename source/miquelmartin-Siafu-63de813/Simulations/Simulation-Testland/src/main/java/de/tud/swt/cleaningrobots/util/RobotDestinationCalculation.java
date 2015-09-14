package de.tud.swt.cleaningrobots.util;

import de.tud.swt.cleaningrobots.model.Position;

public class RobotDestinationCalculation {

	public Position oldDest;
	public Position newDest;
	public boolean needNew;
	public int distMax;
	private String robotName;
	
	public RobotDestinationCalculation (String name) {
		robotName = name;
		oldDest = null;
		newDest = null;
		needNew = false;
	}
	
	public String getName () {
		return robotName;
	}
	
	public Position getActualPosition () {
		if (newDest == null)
			return oldDest;
		return newDest;
	}
	
	public boolean hasPostion () {
		return newDest != null || oldDest != null;
	}
}

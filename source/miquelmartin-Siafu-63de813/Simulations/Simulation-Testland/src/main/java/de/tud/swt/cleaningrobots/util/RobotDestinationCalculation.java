package de.tud.swt.cleaningrobots.util;

import de.tud.swt.cleaningrobots.model.Position;

public class RobotDestinationCalculation {
	
	public Position actualPosition;
	public boolean finish;

	public Position oldDest;
	public Position newDest;
	public boolean needNew;
	public int distMax;
	private String robotName;
	
	public RobotDestinationCalculation (String name) {
		finish = false;
		robotName = name;
		oldDest = null;
		newDest = null;
		actualPosition = null;
		needNew = false;
	}
	
	public String getName () {
		return robotName;
	}
	
	public Position getNewOldPosition () {
		if (newDest == null)
			return oldDest;
		return newDest;
	}
	
	public boolean hasPostion () {
		return newDest != null || oldDest != null;
	}
}

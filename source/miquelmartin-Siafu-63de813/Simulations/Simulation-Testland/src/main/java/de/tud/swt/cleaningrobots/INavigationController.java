package de.tud.swt.cleaningrobots;

import de.tud.swt.cleaningrobots.model.Position;

public interface INavigationController {
	
	public void setPosition(Position position);
	public boolean isWall(int row, int col);
	public int getCol();
	public int getRow();
}

package de.tud.swt.cleaningrobots;

public interface INavigationController {
	//public Position getDestination();
	//public void setDestination(Position destination, Position start);
	public void moveTowardsDestination();
	public boolean isAtDestination();
	public void setPosition(Position position);
}

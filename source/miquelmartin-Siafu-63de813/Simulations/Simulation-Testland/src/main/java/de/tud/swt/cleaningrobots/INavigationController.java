package de.tud.swt.cleaningrobots;

public interface INavigationController {
	public Position getDestination();
	public void setDestination(Position destination);
	public void moveTowardsDestination();
}

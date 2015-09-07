package de.tud.swt.cleaningrobots;

import java.util.List;

import de.tud.swt.cleaningrobots.model.Position;

public class DestinationContainer {
	
	private Position loadStationPosition;
	private Position lastLoadDestination;	
	
	private List<Position> path;
	private Position destination;
	
	private boolean loadDestination;
	private boolean destinationSet;
	
	private RobotCore robot;
	
	public DestinationContainer (RobotCore robotCore) {
		
		this.robot = robotCore;
		this.path = null;
		this.destination = null;
		this.lastLoadDestination = null;
		
		loadStationPosition = robot.getPosition();
		
		//setze Destination informationen
		loadDestination = false;
		destinationSet = false;
	}
	
	public Position getLastLoadDestination () {
		return this.lastLoadDestination;
	}
	
	public Position getLoadStationPosition () {
		return this.loadStationPosition;
	}
	
	public Position getDestination () {
		return this.destination;
	}
	
	public boolean isDestinationSet () {
		return destinationSet;
	}
	
	public boolean isAtLoadDestination () {
		return path == null && loadDestination;
	}

	public boolean isAtDestination() {
		return path == null;
	}
	
	public void setDestinationLoadStation () {
		loadDestination = true;
		this.destination = loadStationPosition;
		refreshPath();
	}

	/***
	 * Sets the destination of the robot
	 * 
	 * @param destination
	 *            If null, the destination will be reset and it is assumed, that
	 *            the robot is at the destination
	 */
	public void setDestination (Position destination) {		
		if (destination == null) {
			this.destination = null;
			this.path = null;
		} else {
			//speichere die erste Destination nachdem er laden war
			if (loadDestination) {
				lastLoadDestination = destination;
				System.out.println("++++LastLoadDestination bei " + robot.getName() + ": " + lastLoadDestination);
			}
			this.destinationSet = true;
			this.destination = destination;
			refreshPath();
		}
		loadDestination = false;		
	}
	
	public void setMasterDestination (Position destination) {
		if (destination == null) {
			this.destination = null;
			this.path = null;
		} else {
			lastLoadDestination = destination;
			this.destinationSet = true;
			this.destination = destination;
			refreshPath();
		}
		loadDestination = false;
	}

	private void refreshPath () {
		this.path = robot.getWorld().getPath(destination);
	}
	
	public List<Position> getPathFromTo (Position start, Position dest) {
		return robot.getWorld().getPathFromTo(start, dest);
	}
	
	public List<Position> getPath () {
		return path;
	}
	
	public void moveTowardsDestination () {		
		if (this.path != null) {
			//sollte nicht auftreten
			if (this.path.size() == 0) {
				//refreshPath();
				System.err.println(this.destination);
				path = null;
				return;
			}
			Position nextPosition = this.path.get(0);
			if (robot.getWorld().isPassable(nextPosition)) {
				path.remove(nextPosition);
				if (nextPosition.equals(destination)) {
					//an destination angekommen
					this.destinationSet = false;
					//destination = null;
					path = null;
				}
				this.robot.setPosition(nextPosition);
			} else {
				//da sich welt nicht verändert sollte das nicht vorkommen
				refreshPath();
			}
		} /*else {
			//logger.warn(getName()
			//		+ ": can't move towards destination because no destination given.");
		}*/
	}
	
}

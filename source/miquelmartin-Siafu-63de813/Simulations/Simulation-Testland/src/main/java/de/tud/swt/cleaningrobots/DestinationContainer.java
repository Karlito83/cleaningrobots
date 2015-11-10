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
		if (!robot.getPosition().equals(loadStationPosition)) {
			this.destination = loadStationPosition;
			refreshPath();
		} else {
			this.path = null;
		}
		loadDestination = true;
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
			this.path = null;
		} else if (destination.equals(robot.getPosition())) {
			this.destinationSet = true;
			this.loadDestination = false;
			this.path = null;
		} else {
			//speichere die erste Destination nachdem er laden war
			if (loadDestination) {
				lastLoadDestination = destination;
			}
			this.destinationSet = true;
			this.destination = destination;
			this.loadDestination = false;
			refreshPath();	
		}	
	}
	
	public void setMasterDestination (Position destination) {
		if (destination == null) {
			this.path = null;
		} else if (destination.equals(robot.getPosition())) {
			this.destinationSet = true;
			this.loadDestination = false;
			this.path = null;
		} else {
			lastLoadDestination = destination;
			this.destination = destination;
			this.destinationSet = true;
			refreshPath();
			loadDestination = false;
		}
	}

	private void refreshPath () {
		this.path = robot.getWorld().getPath(destination);
	}
	
	public List<Position> getPathFromTo (Position start, Position dest) {
		return robot.getWorld().getPathFromTo(start, dest);
	}
	
	public void moveTowardsDestination () {		
		if (this.path != null) {
			//sollte nicht auftreten
			if (this.path.isEmpty())
				return;
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
		}
	}
	
	//Master Do all Szenario
	public boolean setDestinationAndPath (List<Position> path, Position dest) {
		if (dest == null || path == null || path.isEmpty())
			return false;

		this.destination = dest;
		this.path = path;
		this.destinationSet = true;
		
		if (this.loadDestination) {
			robot.isLoading = true;
		}
		
		if (dest.equals(loadStationPosition)) {
			this.loadDestination = true;
		} else {
			if (loadDestination) {
				this.lastLoadDestination = dest;
			}
			this.loadDestination = false;
		}
		return true;
	}
	
	public void moveTowardsDestinationWithoutInformation () {		
		if (this.path != null) {			
			Position nextPosition = this.path.get(0);
			path.remove(nextPosition);
			if (nextPosition.equals(this.destination)) {
				//an destination angekommen
				this.destinationSet = false;
				//destination = null;
				path = null;
			}
			this.robot.setPosition(nextPosition);
		}
	}
	
}

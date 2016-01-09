package de.tud.swt.cleaningrobots;

import java.util.List;

import de.tud.swt.cleaningrobots.model.Position;

/**
 * Handle all destinations and positions of important areas of the robot.
 * 
 * @author Christopher Werner
 *
 */
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
		
		this.loadStationPosition = robot.getPosition();
		
		//set Destination information
		this.loadDestination = false;
		this.destinationSet = false;
	}
	
	/**
	 * Get the destination after the last loading.
	 * @return
	 */
	public Position getLastLoadDestination () {
		return this.lastLoadDestination;
	}
	
	/**
	 * Get the position of the load station. 
	 * @return
	 */
	public Position getLoadStationPosition () {
		return this.loadStationPosition;
	}
	
	/**
	 * Get the actual destination.
	 * @return
	 */
	public Position getDestination () {
		return this.destination;
	}
	
	/**
	 * Is a destination set.
	 * @return
	 */
	public boolean isDestinationSet () {
		return destinationSet;
	}
	
	/**
	 * Is arrived at load station.
	 * @return
	 */
	public boolean isAtLoadDestination () {
		return path == null && loadDestination;
	}

	/**
	 * Is arrived at destination.
	 * @return
	 */
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
		this.destinationSet = false;
		this.loadDestination = true;
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
			this.destinationSet = false;
		} else if (destination.equals(robot.getPosition())) {
			this.destinationSet = true;
			this.loadDestination = false;
			this.path = null;
		} else {
			//save the first Destination after loading
			if (loadDestination) {
				this.lastLoadDestination = destination;
			}
			this.destination = destination;
			this.destinationSet = true;
			this.loadDestination = false;
			refreshPath();	
		}	
	}
	
	public void setMasterDestination (Position destination) {
		if (destination == null) {
			this.path = null;
			this.destinationSet = false;
		} else if (destination.equals(robot.getPosition())) {
			this.destinationSet = true;
			this.loadDestination = false;
			this.path = null;
		} else {
			this.lastLoadDestination = destination;
			this.destination = destination;
			this.destinationSet = true;
			this.loadDestination = false;
			refreshPath();
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
			//should not be
			if (this.path.isEmpty())
				return;
			Position nextPosition = this.path.get(0);
			if (robot.getWorld().isPassable(nextPosition)) {
				path.remove(nextPosition);
				if (nextPosition.equals(destination)) {
					//do not set destination to null because it is not needed
					path = null;
				}
				this.robot.getICommunicationAdapter().setPosition(nextPosition);
			} else {
				//should not be, because the world do not changes
				refreshPath();
			}
		}
	}
	
	//functions for the test case where the Master do all
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
				//do not set destination to null because it is not needed
				path = null;
			}
			this.robot.getICommunicationAdapter().setPosition(nextPosition);
		}
	}
	
}

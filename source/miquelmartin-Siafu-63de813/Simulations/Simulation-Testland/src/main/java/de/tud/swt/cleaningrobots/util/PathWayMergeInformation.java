package de.tud.swt.cleaningrobots.util;

import java.util.List;

import de.tud.swt.cleaningrobots.model.Position;

public class PathWayMergeInformation {

	private List<Position> path;
	private Position destination;
	
	public PathWayMergeInformation (Position destination, List<Position> path) {
		this.path = path;
		this.destination = destination;
	}
	
	public Position getDestination () {
		return this.destination;
	}
	
	public List<Position> getPath () {
		return this.path;
	}
}

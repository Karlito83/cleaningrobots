package de.tud.swt.cleaningrobots.model;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/**
 * Helper functions for the world.
 * 
 * @author Christopher Werner
 *
 */
public class WorldHelper {

	private class Node implements Comparable<Node> {

		private Position currentPosition, destination;
		private int distanceGone;
		private List<Position> pathGone;

		public Node(Position currentPosition, Position destination,
				int wayGone, List<Position> pathGone) {
			this.currentPosition = currentPosition;
			this.destination = destination;
			this.distanceGone = wayGone;
			this.pathGone = pathGone;
		}

		public int getDistanceGone() {
			return distanceGone;
		}

		public int getDistanceToGo() {
			return Math.max(Math.abs(currentPosition.getX() - destination.getX()), 
					Math.abs(currentPosition.getY() - destination.getY()));
		}

		public int getWeight() {
			return getDistanceGone() + getDistanceToGo();
		}

		public List<Position> getPathGone() {
			return pathGone;
		}

		public int compareTo(Node o) {
			return this.getWeight() - o.getWeight();
		}

		public Position getCurrentPosition() {
			return currentPosition;
		}

	}
	
	private World world;

	public WorldHelper(World world) {
		this.world = world;
	}

	/***
	 * Determines the shortest path between two Positions according to the known
	 * world data (A*-Algorithm)
	 * Return empty list if start equals destination.
	 * @param startPosition
	 * @param destination
	 * @return Shortest path beginning with the first neighbor of the start
	 *         position
	 */
	public List<Position> findPath(Position startPosition, Position destination) {
		
		List<Position> result = new LinkedList<Position>();
		Set<Position> expanded = new HashSet<Position>();

		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		Node initialNode = new Node(startPosition, destination, 0,
				new LinkedList<Position>());
		expanded.add(startPosition);
		queue.add(initialNode);
		while (!queue.isEmpty()) {
			Node currentNode = queue.poll();
			if (currentNode.getDistanceToGo() == 0) {
				result = currentNode.getPathGone();
				break;
			}
			for (Position neighbour : getPassableNeighborPositions(currentNode.getCurrentPosition())) {
				if (!expanded.contains(neighbour)){
					List<Position> path = new LinkedList<Position>(
							currentNode.getPathGone());
					path.add(neighbour);
					expanded.add(neighbour);
					Node newNode = new Node(neighbour, destination,
							currentNode.getDistanceGone() + 1, path);
					queue.add(newNode);
				}
			}
		}
		return result;
	}
	
	/**
	 * Returns all Neighbor Positions which are passable.
	 * @param currentPosition
	 * @return
	 */
	private List<Position> getPassableNeighborPositions(Position currentPosition) {
		List<Position> result = new LinkedList<Position>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				Position newPosition = new Position(currentPosition.getX() + i,
						currentPosition.getY() + j);
				if (world.isPassable(newPosition)) {
					result.add(newPosition);
				}
			}
		}		
		return result;
	}

	/**
	 * Return all Neighbor Positions of the current Position.
	 * @param currentPosition
	 * @return
	 */
	public List<Position> getNeighbourPositions(Position currentPosition) {
		List<Position> result = new LinkedList<Position>();
		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				result.add(new Position(currentPosition.getX() + i,
						currentPosition.getY() + j));
			}
		}		
		return result;
	}
}

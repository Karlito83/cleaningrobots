package de.tud.swt.cleaningrobots;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

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
			int xDiff, yDiff;
			xDiff = currentPosition.getX() - destination.getX();
			xDiff = xDiff > 0 ? xDiff : -xDiff;
			yDiff = currentPosition.getY() - destination.getY();
			yDiff = yDiff > 0 ? yDiff : -yDiff;
			System.err.println(xDiff + " ," + yDiff + ", " + (xDiff > yDiff ? xDiff : yDiff));
			return xDiff > yDiff ? xDiff : yDiff;
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
	 * 
	 * @param startPosition
	 * @param destination
	 * @return Shortest path beginning with the first neighbor of the start
	 *         position
	 */
	public List<Position> findPath(Position startPosition, Position destination) {
		List<Position> result = new LinkedList<Position>();

		System.err.println("findpath");
		
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
			for (Position neighbour : getNeighbourPositions(
					currentNode.getCurrentPosition(), true)) {
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

	public List<Position> getNeighbourPositions(Position currentPosition,
			boolean onlyPassable) {
		List<Position> result = new LinkedList<Position>();

		for (int i = -1; i <= 1; i++) {
			for (int j = -1; j <= 1; j++) {
				if (i == 0 && j == 0) {
					continue;
				}
				// System.err.println(i + ", " + j);
				Position newPosition = new Position(currentPosition.getX() + i,
						currentPosition.getY() + j);
				if (!onlyPassable || world.isPassable(newPosition)) {
					result.add(newPosition);
				}
			}
		}
		
		System.err.println(result.size());

		return result;
	}
}

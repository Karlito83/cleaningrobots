package de.tud.swt.cleaningrobots;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class WorldHelper {

	private class Node implements Comparable<Node> {

		private Position currentPosition, destination;
		private int wayGone;
		private List<Position> pathGone;

		public Node(Position currentPosition, Position destination, int wayGone, List<Position> pathGone) {
			this.currentPosition = currentPosition;
			this.destination = destination;
			this.wayGone = wayGone;
			this.pathGone = pathGone;
		}
		
		
		public int getWayGone() {
			return wayGone;
		}

		public int getWayToGo() {
			int xDiff, yDiff;
			xDiff = currentPosition.getX() - destination.getX();
			xDiff = xDiff > 0 ? xDiff : -xDiff;
			yDiff = currentPosition.getX() - destination.getX();
			yDiff = yDiff > 0 ? yDiff : -yDiff;
			return xDiff > yDiff ? xDiff : yDiff;
		}
		
		public int getPriority(){
			return getWayGone() + getWayToGo();
		}
		
		public List<Position> getPathGone() {
			return pathGone;
		}


		public int compareTo(Node o) {
			return this.getPriority()-o.getPriority();
		}


		public Position getCurrentPosition() {
			return currentPosition;
		}
	
		
	}

	private World world;

	public WorldHelper(World world) {
		this.world = world;
	}

	private List<Position> findPath(Position currentPosition,
			Position destination) {
		List<Position> result = new LinkedList<Position>();
		
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		Node initialNode = new Node(currentPosition, destination, 0, new LinkedList<Position>());
		while (!queue.isEmpty()){
			Node currentNode = queue.poll();
			if(currentNode.getWayToGo()==0){
				 result = currentNode.getPathGone();
				 result.add(currentNode.getCurrentPosition());
				 break;
			}
			for (Position neighbour: getNeighbours(currentNode.getCurrentPosition())){
				Node newNode = new Node(neighbour, destination, currentNode.wayGone + 1, pathGone)				
			}
		}

		return result;
	}

	private List<Position> getNeighbours(Position currentPosition) {
		List<Position> result = new LinkedList<Position>();
		
		for (int i=-1; i <= 1; i++){
			for (int j=-1; j <= 1; j++){
				 if (i==j){
					 continue;
				 }
				 Position newPosition = new Position(currentPosition.getX()+i, currentPosition.getY()+j);
				 if(world.isPassable(newPosition)){
					 result.add(newPosition);
				 }
			}
		}
		
		return result;
		
	}
}

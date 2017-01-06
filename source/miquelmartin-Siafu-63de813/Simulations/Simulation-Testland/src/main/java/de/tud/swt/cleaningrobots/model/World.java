package de.tud.swt.cleaningrobots.model;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import cleaningrobots.CleaningrobotsFactory;
import de.tud.evaluation.ExchangeMeasurement;
import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;
import de.tud.swt.cleaningrobots.util.AgentDestinationCalculation;

/**
 * The World a robot has about the map in the test case.
 * 
 * @author Christopher Werner
 *
 */
public class World {
	
	private class Node implements Comparable<Node> {
		
		private Position currentPosition;
		private int weight;

		public Node(Position currentPosition, int weight) {
			this.currentPosition = currentPosition;
			this.weight = weight;
		}

		public int getWeight() {
			return this.weight;
		}

		public int compareTo(Node o) {
			return this.getWeight() - o.getWeight();
		}

		public Position getCurrentPosition() {
			return currentPosition;
		}

	}

	private final Random random = new Random();

	private AgentCore robot;
	private Map<Position, Field> map;
	private Set<State> worldStates;
	//Coordinations of the big map 
	private int minX;
	private int minY;
	//Dimensions of the local mini map
	private int xDim;
	private int yDim;
	
	private int newInformationCounter;
	
	private State DISCOVERED_WORLDSTATE;
	private State HOOVED_WORLDSTATE;
	private State WIPED_WORLDSTATE;
	
	private WorldHelper helper;

	public World(AgentCore robot) {
		this.DISCOVERED_WORLDSTATE = robot.getConfiguration().createState("Discovered");
		this.HOOVED_WORLDSTATE = robot.getConfiguration().createState("Hooved");
		this.WIPED_WORLDSTATE = robot.getConfiguration().createState("Wiped");				
		this.robot = robot;
		this.worldStates = new HashSet<State>();
		this.map = new HashMap<Position, Field>();
		this.helper = new WorldHelper(this);
		this.newInformationCounter = 0;
	}
	
	/**
	 * Reset the NewInformationCounter to 0.
	 */
	public void resetNewInformationCounter () {
		this.newInformationCounter = 0;
	}
	
	/**
	 * Get the NewInformationCounter.
	 * @return
	 */
	public int getNewInformationCounter () {
		return this.newInformationCounter;
	}

	/**
	 * Add a new Field to the world.
	 * @param newField
	 */
	public void addField(Field newField) {
		Position p = newField.getPos();
		//if map contains Position then update Field else add
		if (map.containsKey(p)) {
			updateField(newField);
		} else {
			newInformationCounter++;
			//extend the dimension of the map with the new Field
			if (p.getX() < this.minX) {
				this.minX = p.getX();
			}
			if (p.getY() < this.minY) {
				this.minY = p.getY();
			}
			if (p.getX() > this.minX + this.xDim) {
				this.xDim = p.getX() + this.minX;
			}
			if (p.getY() > this.minY + this.yDim) {
				this.yDim = p.getY() + this.minY;
			}			
			map.put(p, newField);
		}
	}

	/**
	 * Update the old Field with the States of the new Field.
	 * @param newField
	 */
	private void updateField(Field newField) {
		Field oldField = map.get(newField.getPos());
		
		for (State newState : newField.getStates()) {
			if (!oldField.containsState(newState)) {
				oldField.addState(newState, robot.getConfiguration().getWc().iteration);
				newInformationCounter++;
			}
		}
	}

	public void addFields(Iterable<Field> fields) {
		for (Field field : fields) {
			this.addField(field);
		}
	}	

	/***
	 * Returns the next passable Field without a given State.
	 * For example search field which is passable without state "hoove".	 * 
	 * @param state
	 * @return
	 */
	public Position getNextPassablePositionWithoutState(State state) {
		return getNextPassablePositionWithoutState(this.robot.getPosition(), state);
	}
	
	/**
	 * Returns the next passable Field without a given State.
	 * For example search field which is passable without state "hoove".
	 * @param my
	 * @param state
	 * @return
	 */
	public Position getNextPassablePositionWithoutState(Position my, State state) {
		if (!map.containsKey(my))
			return null;
		
		boolean flag = true;
		
		Set<Position> visited = new HashSet<Position>();
		visited.add(my);
		Queue<Position> nodes = new LinkedList<Position>();
		nodes.add(my);

		//add temp results
		List<Position> tmpResult = new LinkedList<Position>();
		Position result = null;
		
		int dist = Integer.MAX_VALUE;
		
		while (flag && !nodes.isEmpty()) {
			Position currentNodePosition = nodes.poll();
			if (!map.get(currentNodePosition).containsState(state)) {
				//wenn aktuelles Feld nicht den State enthält
				//Position kann zu Ergebnis gehören
				int actDist = getDistanceFromPositionToPosition(my, currentNodePosition);
				if (actDist <= dist) {
					if (actDist < dist)	{
						tmpResult.clear();
						dist = actDist;
					}
					tmpResult.add(currentNodePosition);
				} else {
					flag = false;
				}
			} else {
				//add neighbor fields to the node list, if they exists, contains the state and not visited yet
				for (Position neighbour : helper.getNeighbourPositions(currentNodePosition)) {
					if (map.containsKey(neighbour) && map.get(neighbour).isPassable() && !visited.contains(neighbour)) {
						visited.add(neighbour);
						nodes.add(neighbour);
					}
				}
			}	
		}		
		
		if(!tmpResult.isEmpty()){
			int index = random.nextInt(tmpResult.size());
			result = tmpResult.get(index);
		}
		return result;
	}
	
	/**
	 * Returns the next passable Field without a given State, relative to a given Position.
	 * For example search field which is passable without state "hoove".
	 * @param relative
	 * @param state
	 * @return
	 */
	public Position getNextPassablePositionRelativeWithoutState(Position relative, State state) {
		return getNextPassablePositionRelativeWithoutState(this.robot.getPosition(), relative, state);		
	}
	
	/**
	 * Returns the next passable Field without a given State, relative to a given Position.
	 * For example search field which is passable without state "hoove".
	 * @param my
	 * @param relative
	 * @param state
	 * @return
	 */
	public Position getNextPassablePositionRelativeWithoutState(Position my, Position relative, State state) {
				
		if (relative == null)
			return getNextPassablePositionWithoutState(my, state);
		
		if (!map.containsKey(my))
			return null;

		Position result = null;
		List<Node> tmpResult = new LinkedList<Node>();
		
		boolean flag = true;

		Set<Position> visited = new HashSet<Position>();
		visited.add(my);
		
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		Node start = new Node(my, getDistanceFromPositionToPosition(my, relative));
		queue.add(start);

		int dist = Integer.MAX_VALUE;
		
		while (flag && !queue.isEmpty()) {
			Node currentNode = queue.poll();
			if (!map.get(currentNode.getCurrentPosition()).containsState(state)) {
				//field not contains the state
				//Position could be a result
				int actDist = currentNode.getWeight();
				if (actDist <= dist) {
					if (actDist < dist)	{
						tmpResult.clear();
						dist = actDist;
					}
					tmpResult.add(currentNode);
				} else {
					flag = false;
				}
			} else {
				//add neighbor fields to the node list, if they exists, contains the state and not visited yet
				for (Position neighbour : helper.getNeighbourPositions(currentNode.getCurrentPosition())) {
					if (map.containsKey(neighbour) && map.get(neighbour).isPassable() && !visited.contains(neighbour)) {
						int actDist = getDistanceFromPositionToPosition(my, neighbour) + getDistanceFromPositionToPosition(neighbour, relative);
						Node n = new Node(neighbour, actDist);
						visited.add(neighbour);
						queue.add(n);
					}
				}
			}
		}		
		
		if(!tmpResult.isEmpty()){
			int index = random.nextInt(tmpResult.size());
			result = tmpResult.get(index).getCurrentPosition();
		}
		return result;
	}
	
	/**
	 * Search best next passable Field without a given State for different Robots that they are far away of each other.
	 * For example search fields which are passable without state "hoove".
	 * @param information
	 * @param maxAway
	 * @return
	 */
	public Map<String, AgentDestinationCalculation> getNextPassablePositionsWithoutState(Map<String, AgentDestinationCalculation> information, int maxAway, State state) {

		if (this.containsWorldState(HOOVED_WORLDSTATE)) {
			System.out.println("Welt schon gesaugt!");
			return null;
		}
		
		//Position is the same of all robots
		Position p = this.robot.getPosition();
		
		if (!map.containsKey(p))
			return null;
		
		boolean changeFlag = false;
		
		for (AgentDestinationCalculation rdc : information.values()) {
			if (rdc.needNew) {
				rdc.distMax = -1;
				
				boolean flag = true;

				Set<Position> visited = new HashSet<Position>();
				visited.add(p);
				Queue<Position> nodes = new LinkedList<Position>();
				nodes.add(p);

				while (flag && !nodes.isEmpty()) {
					Position currentNodePosition = nodes.poll();
					if (!map.get(currentNodePosition).containsState(state)) {
						//field not contains the state and can be result
						boolean proofDist = true;
						int minDistToOther = maxAway;
						for (AgentDestinationCalculation proof : information.values())
						{
							if (!proof.getName().equals(rdc.getName()))
							{
								//if there is one position proof this
								if (proof.hasPostion())
								{
									int dist = getDistanceFromPositionToPosition(proof.getNewOldPosition(), currentNodePosition);									
									if (dist < maxAway)
									{
										if (dist < minDistToOther)
											minDistToOther = dist;
										proofDist = false;
									}
								}
							}
						}
						if (proofDist)
						{
							changeFlag = true;
							//all correct use this position
							rdc.newDest = currentNodePosition;
							flag = false;
							break;
						}
						else 
						{
							changeFlag = true;
							//use position if it is better than the last one
							if (minDistToOther > rdc.distMax) {
								rdc.newDest = currentNodePosition;
								rdc.distMax = minDistToOther;
							}
						}
					} else {
						//add neighbor fields to the node list, if they exists, contains the state and not visited yet
						for (Position neighbour : helper.getNeighbourPositions(currentNodePosition)) {
							if (map.containsKey(neighbour) && map.get(neighbour).isPassable() && !visited.contains(neighbour)) {
								visited.add(neighbour);
								nodes.add(neighbour);
							}
						}
					}
				}
			}
		}
		
		if (!changeFlag)
			return null;
				
		return information;
	}
	
	/**
	 * Returns the next Field with a State but without an other State.
	 * @param state
	 * @param without
	 * @return
	 */
	public Position getNextPassablePositionByStateWithoutState (State state, State without) {
		return getNextPassablePositionByStateWithoutState(this.robot.getPosition(), state, without);
	}		
	
	/**
	 * Returns the next Field with a State but without an other State.
	 * @param my
	 * @param state
	 * @param without
	 * @return
	 */
	public Position getNextPassablePositionByStateWithoutState (Position my, State state, State without) {	
		//return null if position not in map and not contains state
		if (!map.containsKey(my) || !map.get(my).containsState(state))
			return null;
		
		boolean flag = true;
		
		Set<Position> visited = new HashSet<Position>();
		visited.add(my);
		Queue<Position> nodes = new LinkedList<Position>();
		nodes.add(my);

		//add temp results
		List<Position> tmpResult = new LinkedList<Position>();
		Position result = null;
		
		int dist = Integer.MAX_VALUE;
		
		while (flag && !nodes.isEmpty()) {
			Position currentNodePosition = nodes.poll();
			if (!map.get(currentNodePosition).containsState(without)) {
				//Position can be result
				int actDist = getDistanceFromPositionToPosition(my, currentNodePosition);
				if (actDist <= dist) {
					if (actDist < dist) {
						tmpResult.clear();
						dist = actDist;
					}
					tmpResult.add(currentNodePosition);
				} else {
					flag = false;
				}
			} else {
				//add neighbor fields to the node list, if they exists, contains the state and not visited yet
				for (Position neighbour : helper.getNeighbourPositions(currentNodePosition)) {
					if (map.containsKey(neighbour) && map.get(neighbour).containsState(state) && !visited.contains(neighbour)) {
						visited.add(neighbour);
						nodes.add(neighbour);
					}
				}
			}
		}		
		
		if(!tmpResult.isEmpty()){
			int index = random.nextInt(tmpResult.size());
			result = tmpResult.get(index);
		}
		return result;
	}
	
	/**
	 * Returns the next Field with a State but without an other State and relative to a given Position.
	 * @param relative
	 * @param state
	 * @param without
	 * @return
	 */
	public Position getNextPassableRelativePositionByStateWithoutState (Position relative, State state, State without) {
		return getNextPassableRelativePositionByStateWithoutState(this.robot.getPosition(), relative, state, without);		
	}
	
	/**
	 * Returns the next Field with a State but without an other State and relative to a given Position.
	 * @param my
	 * @param relative
	 * @param state
	 * @param without
	 * @return
	 */
	public Position getNextPassableRelativePositionByStateWithoutState (Position my, Position relative, State state, State without) {
				
		if (relative == null)
			return getNextPassablePositionByStateWithoutState(my, state, without);
		
		if (!map.containsKey(my) || !map.get(my).containsState(state))
			return null;

		Position result = null;
		List<Node> tmpResult = new LinkedList<Node>();
		
		boolean flag = true;

		Set<Position> visited = new HashSet<Position>();
		visited.add(my);
		
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		Node start = new Node(my, getDistanceFromPositionToPosition(my, relative));
		queue.add(start);

		int dist = Integer.MAX_VALUE;
		
		while (flag && !queue.isEmpty()) {
			Node currentNode = queue.poll();
			if (!map.get(currentNode.getCurrentPosition()).containsState(without)) {
				//Position can be result
				int actDist = currentNode.getWeight();
				if (actDist <= dist) {
					if (actDist < dist) {
						tmpResult.clear();
						dist = actDist;
					}
					tmpResult.add(currentNode);
				} else {
					flag = false;
				}
			} else {
				//add neighbor fields to the node list, if they exists, contains the state and not visited yet
				for (Position neighbour : helper.getNeighbourPositions(currentNode.getCurrentPosition())) {
					if (map.containsKey(neighbour) && map.get(neighbour).containsState(state) && !visited.contains(neighbour)) {
						int actDist = getDistanceFromPositionToPosition(my, neighbour) + getDistanceFromPositionToPosition(neighbour, relative);
						Node n = new Node(neighbour, actDist);
						visited.add(neighbour);
						queue.add(n);
					}
				}
			}
		}		
		
		if(!tmpResult.isEmpty()){
			int index = random.nextInt(tmpResult.size());
			result = tmpResult.get(index).getCurrentPosition();
		}
		return result;
	}
	
	/**
	 * Search best next Field with a State but without an other State for different Robots that they are far away of each other.
	 * @param information
	 * @param maxAway
	 * @return
	 */
	public Map<String, AgentDestinationCalculation> getNextPassablePositionsByStateWithoutState(Map<String, AgentDestinationCalculation> information, int maxAway, State state, State without) {

		if (this.containsWorldState(WIPED_WORLDSTATE)) {
			System.out.println("Welt schon gewischt!");
			return null;
		}		
		
		//Position is for all robots the same
		Position p = this.robot.getPosition();
		
		if (!map.containsKey(p) || !map.get(p).containsState(state))
			return null;
		
		boolean changeFlag = false;
		
		for (AgentDestinationCalculation rdc : information.values()) {
			if (rdc.needNew) {
				rdc.distMax = -1;
				
				boolean flag = true;

				Set<Position> visited = new HashSet<Position>();
				visited.add(p);
				Queue<Position> nodes = new LinkedList<Position>();
				nodes.add(p);

				while (flag && !nodes.isEmpty()) {
					Position currentNodePosition = nodes.poll();
					if (!map.get(currentNodePosition).containsState(without)) {
						//field not contains the state, Position can be result
						boolean proofDist = true;
						int minDistToOther = maxAway;
						for (AgentDestinationCalculation proof : information.values())
						{
							if (!proof.getName().equals(rdc.getName()))
							{
								//if there is one position proof this
								if (proof.hasPostion())
								{
									int dist = getDistanceFromPositionToPosition(proof.getNewOldPosition(), currentNodePosition);									
									if (dist < maxAway)
									{
										if (dist < minDistToOther)
											minDistToOther = dist;
										proofDist = false;
									}
								}
							}
						}
						if (proofDist)
						{
							changeFlag = true;
							//all correct use this position
							rdc.newDest = currentNodePosition;
							flag = false;
							break;
						}
						else 
						{
							changeFlag = true;
							//if position is better than last use this
							if (minDistToOther > rdc.distMax) {
								rdc.newDest = currentNodePosition;
								rdc.distMax = minDistToOther;
							}
						}
					} else {
						//add neighbor fields to the node list, if they exists, contains the state and not visited yet
						for (Position neighbour : helper.getNeighbourPositions(currentNodePosition)) {
							if (map.containsKey(neighbour) && map.get(neighbour).containsState(state) && !visited.contains(neighbour)){
								visited.add(neighbour);
								nodes.add(neighbour);
							}
						}
					}
				}
			}
		}
		
		if(!changeFlag)
			return null;
				
		return information;
	}

	/**
	 * Calculate the minimal way between the Robot Position and the Destination Position.
	 * @param destination
	 * @return
	 */
	public List<Position> getPath(Position destination) {
		return getPathFromTo(this.robot.getPosition(), destination);
	}
	
	/**
	 * Calculate the minimal way between the Start Position and the Destination Position.
	 * @param start
	 * @param destination
	 * @return
	 */
	public List<Position> getPathFromTo (Position start, Position destination)	{
		return helper.findPath(start, destination);
	}
	
	/**
	 * Search best NextUnknownPosition for different Robots that they are far away of each other.
	 * @param information
	 * @param maxAway
	 * @return
	 */
	public Map<String, AgentDestinationCalculation> getNextUnknownFields(Map<String, AgentDestinationCalculation> information, int maxAway) {

		if (this.containsWorldState(DISCOVERED_WORLDSTATE)) {
			System.out.println("Welt schon erkundet!");
			return null;
		}
		
		//Position of all robots is the same
		Position p = this.robot.getPosition();
		
		boolean changeFlag = false;
		
		for (AgentDestinationCalculation rdc : information.values()) {
			if (rdc.needNew) {
				rdc.distMax = -1;
				
				boolean flag = true;

				Set<Position> visited = new HashSet<Position>();
				visited.add(p);
				Queue<Position> nodes = new LinkedList<Position>();
				nodes.add(p);

				while (flag && !nodes.isEmpty()) {
					Position currentNodePosition = nodes.poll();
					for (Position neighbour : helper.getNeighbourPositions(
							currentNodePosition)) {
						//only add if not seen before
						if (!map.containsKey(neighbour)) {
							boolean proofDist = true;
							int minDistToOther = maxAway;
							for (AgentDestinationCalculation proof : information.values())
							{
								if (!proof.getName().equals(rdc.getName()))
								{
									//if there is one position proof this
									if (proof.hasPostion())
									{
										int dist = getDistanceFromPositionToPosition(proof.getNewOldPosition(), currentNodePosition);									
										if (dist < maxAway)
										{
											if (dist < minDistToOther)
												minDistToOther = dist;
											proofDist = false;
										}
									}
								}
							}
							if (proofDist)
							{
								changeFlag = true;
								//all correct use this position
								rdc.newDest = currentNodePosition;
								flag = false;
								break;
							}
							else 
							{
								changeFlag = true;
								//if position is better use this
								if (minDistToOther > rdc.distMax) {
									rdc.newDest = currentNodePosition;
									rdc.distMax = minDistToOther;
								}
							}							
						} else {
							//proof if visited contain neighbor and neighbor is passable
							if (!visited.contains(neighbour) && map.get(neighbour).isPassable()) {
								visited.add(neighbour);
								nodes.add(neighbour);
							}
						}
					}
				}
			}
		}
		
		if (!changeFlag)
			return null;
		
		return information;
	}	
	
	/**
	 * Return the Position of an passable Field next to an unknown Position.
	 * @return
	 */
	public Position getNextUnknownFieldPosition() {
		return this.getNextUnknownFieldPosition(this.robot.getPosition());
	}

	/**
	 * Return the Position of an passable Field next to an unknown Position.
	 * @param my
	 * @return
	 */
	public Position getNextUnknownFieldPosition(Position my) {

		Position result = null;
		List<Position> tmpResult = new LinkedList<Position>();
		boolean flag = true;

		Set<Position> visited = new HashSet<Position>();
		visited.add(my);
		Queue<Position> nodes = new LinkedList<Position>();
		nodes.add(my);

		while (flag && !nodes.isEmpty()) {
			Position currentNodePosition = nodes.poll();
			for (Position neighbour : helper.getNeighbourPositions(
					currentNodePosition)) {
				//only add if not seen before
				if (!map.containsKey(neighbour)) {
					if (!tmpResult.isEmpty()
							&& getDistanceFromPositionToPosition(my, currentNodePosition) > getDistanceFromPositionToPosition(my, tmpResult
									.get(0))) {
						flag = false;
						break;
					}
					tmpResult.add(currentNodePosition);
				} else {
					//proof if visited contain neighbor and neighbor is passable
					if (!visited.contains(neighbour) && map.get(neighbour).isPassable()) {
						visited.add(neighbour);
						nodes.add(neighbour);
					}
				}
			}
		}
		
		if(!tmpResult.isEmpty()){
			int index = random.nextInt(tmpResult.size());
			result = tmpResult.get(index);
		}
		return result;
	}	
	
	/**
	 * Return the Position of an passable Field next to an unknown Position, which is near the relative Position.
	 * @param relative
	 * @return
	 */
	public Position getNextUnknownRelativeFieldPosition(Position relative) {
		return getNextUnknownRelativeFieldPosition(this.robot.getPosition(), relative);
	}
	
	/**
	 * Return the Position of an passable Field next to an unknown Position, which is near the relative Position.
	 * @param my Position of Robot
	 * @param relative Relative Position to Look
	 * @return
	 */
	public Position getNextUnknownRelativeFieldPosition(Position my, Position relative) {
		
		if (relative == null)
			return getNextUnknownFieldPosition(my);

		Position result = null;
		List<Node> tmpResult = new LinkedList<Node>();
		
		boolean flag = true;

		Set<Position> visited = new HashSet<Position>();
		visited.add(my);
		
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		Node start = new Node(my, getDistanceFromPositionToPosition(my, relative));
		queue.add(start);

		int dist = Integer.MAX_VALUE;
		
		while (flag && !queue.isEmpty()) {
			Node currentNode = queue.poll();
			for (Position neighbour : helper.getNeighbourPositions(
					currentNode.getCurrentPosition())) {
				//only add if not seen before
				if (!map.containsKey(neighbour)) {
					int actDist = currentNode.getWeight();
					if (actDist <= dist) {
						if (actDist < dist)
						{
							tmpResult.clear();
							dist = actDist;
						}
						tmpResult.add(currentNode);
					} else {
						flag = false;
					}
				} else {
					//proof if visited contain neighbor and neighbor is passable
					if (!visited.contains(neighbour) && map.get(neighbour).isPassable()) {
						int actDist = getDistanceFromPositionToPosition(my, neighbour) + getDistanceFromPositionToPosition(neighbour, relative);
						Node n = new Node(neighbour, actDist);
						visited.add(neighbour);
						queue.add(n);
					}
				}
			}
		}
				
		if(!tmpResult.isEmpty()){
			int index = random.nextInt(tmpResult.size());
			result = tmpResult.get(index).getCurrentPosition();
		}
		return result;
	}
	
	/**
	 * Get the biggest distance between two positions
	 * @param one first Position
	 * @param two second Position
	 * @return distance in int
	 */
	private int getDistanceFromPositionToPosition(Position one, Position two) {
		return Math.max(Math.abs(one.getX() - two.getX()), Math.abs(one.getY() - two.getY()));
	}

	/**
	 * Look if Position exists and if it is passable.
	 * @param position
	 * @return
	 */
	public boolean isPassable(Position position) {
		return map.containsKey(position) && map.get(position).isPassable();
	}
	
	/**
	 * Look if Position exists and if it contains the given State.
	 * @param position
	 * @param state
	 * @return
	 */
	public boolean hasState(Position position, State state) {
		return map.containsKey(position) && map.get(position).containsState(state);
	}
	
	/**
	 * Get the Field with this Position from the World.
	 * @param position
	 * @return
	 */
	public Field getField (Position position) {
		return map.get(position);
	}
	
	//worldstate methods
	/**
	 * Get the WorldStates of the World.
	 * @return
	 */
	public Set<State> getWorldStates () {
		return worldStates;
	}
	
	public Collection<Field> getFields () {
		return map.values();
	}
	
	/**
	 * Look if the world contains this State as worldstate.
	 * @param state
	 * @return
	 */
	public boolean containsWorldState (State state) {
		return this.worldStates.contains(state);
	}
	
	/**
	 * Add this State to the World as worldstate.
	 * @param state
	 * @return
	 */
	public boolean addWorldState (State state) {
		return this.worldStates.add(state);
	}
	
	/**
	 * Remove this state form the World's worldstates.
	 * @param state
	 * @return
	 */
	public boolean removeWorldState (State state) {
		return this.worldStates.remove(state);
	}
	
	public ExchangeMeasurement getHoleMeasurement () {
		ExchangeMeasurement em = new ExchangeMeasurement("", "", 0);
		//run through the WorldStates
		em.addWorldIntegerNumber(4);
		em.addWorldStatesStringNumber(this.worldStates.size());
		for (State s : this.worldStates) {
			em.addWorldStatesStringByteNumber(s.getName().getBytes().length);
		}
		//run through the Map
		em.addWorldPositionCount(map.keySet().size() * 2);
		
		for (Field f : map.values()) {
			em.addWorldStringNumber(f.getStates().size());
			for (State s  : f.getStates()) {
				em.addWorldStringByteNumber(s.getName().getBytes().length);
			}
		}
		return em;
	}

	/*
	public cleaningrobots.World exportModel() {
		//TODO: Consider caching
		cleaningrobots.World modelWorld = null;
		cleaningrobots.Map modelMap = null;
		
		//modelWorld = CleaningrobotsFactory.eINSTANCE.createWorld();
		modelMap = CleaningrobotsFactory.eINSTANCE.createMap();
		modelMap.setXdim(xDim);
		modelMap.setYdim(yDim);
		for (Field field : map.values()){
			modelMap.getFields().add(field.exportModel());
		}
		modelWorld.getChildren().add(modelMap);
		
		return modelWorld;
	}
	*/
	
	public cleaningrobots.WorldPart exportModel(ImportExportConfiguration config) {
		//TODO: Consider caching
		cleaningrobots.Map modelMap = null;
		
		modelMap = CleaningrobotsFactory.eINSTANCE.createMap();
		modelMap.setXdim(xDim);
		modelMap.setYdim(yDim);
		//add world fields
		for (Field field : map.values()){
			cleaningrobots.Field f = field.exportModel(config);
			if (f != null)
				modelMap.getFields().add(f);
		}
		
		//add World states
		for (State state : worldStates){
			modelMap.getWorldStates().add(state.exportModel());
		}
		
		return modelMap;
	}
}

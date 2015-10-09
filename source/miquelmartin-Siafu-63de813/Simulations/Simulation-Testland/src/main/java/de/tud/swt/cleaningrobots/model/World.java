package de.tud.swt.cleaningrobots.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cleaningrobots.CleaningrobotsFactory;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.measure.ExchangeMeasurement;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;
import de.tud.swt.cleaningrobots.util.RobotDestinationCalculation;

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

	private final Logger logger = LogManager.getRootLogger();

	private RobotCore robot;
	private Map<Position, Field> map;
	private Set<State> worldStates;
	//koordinaten von großer karte 
	private int minX;
	private int minY;
	//dimensionen von kleiner Karte
	private int xDim;
	private int yDim;

	private WorldHelper helper;

	public World(RobotCore robot) {
		this.robot = robot;
		this.worldStates = new HashSet<State>();
		this.map = new HashMap<Position, Field>();
		this.helper = new WorldHelper(this);
	}

	public void addField(Field newField) {
		Position p = newField.getPos();
		//erweitere die Dimensionen der Karte anhand des neuen Feldes
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
		//Wenn Feld mit Positon schon in Map drin ist dann update ansonsten füge hinzu
		if (map.containsKey(p)) {
			updateField(newField);
		} else {
			map.put(p, newField);
		}
	}

	/**
	 * Update das Feld
	 * @param newField
	 */
	private void updateField(Field newField) {
		Field oldField = map.get(newField.getPos());
		
		for (State newState : newField.getStates()) {
			if (!oldField.containsState(newState)) {
				oldField.addState(newState);
			}
		}
	}

	public void addFields(Iterable<Field> fields) {
		for (Field field : fields) {
			this.addField(field);
		}
	}

	/***
	 * Returns the next passable Field without a given State
	 * 
	 * @param state
	 * @return
	 */
	public Position getNextPassablePositionWithoutState(State state) {
		logger.trace("Getting the next passable field without the state");
		Position p = this.robot.getPosition();
		
		if (!map.containsKey(p))
			return null;
		
		boolean flag = true;
		
		Set<Position> visited = new HashSet<Position>();
		visited.add(p);
		Queue<Position> nodes = new LinkedList<Position>();
		nodes.add(p);

		//tmp results hinzufügen
		List<Position> tmpResult = new LinkedList<Position>();
		Position result = null;
		
		int dist = Integer.MAX_VALUE;
		
		while (flag && !nodes.isEmpty()) {
			Position currentNodePosition = nodes.poll();
			if (!map.get(currentNodePosition).containsState(state)) {
				//wenn aktuelles Feld nicht den State enthält
				//Position kann zu Ergebnis gehören
				int actDist = getDistanceFromPositionToPosition(p, currentNodePosition);
				if (actDist <= dist) {
					if (actDist < dist)	{
						tmpResult.clear();
						dist = actDist;
					}
					tmpResult.add(currentNodePosition);
				} else {
					flag = false;
				}
				//System.out.println("Dist: " + dist + " actDist: " + actDist + " Node: " + currentNodePosition + " Size: " + nodes.size());
			} else {
				//füge alle nachbar Felder der Nodeliste hinzu aber nur wenn sie existieren und den state enthalten
				//und noch nicht besucht wurden
				for (Position neighbour : helper.getNeighbourPositions(currentNodePosition, false)) {
					if (map.containsKey(neighbour) && map.get(neighbour).isPassable() && !visited.contains(neighbour)) {
						//System.out.println("Neighbour added: " + getDistanceFromCurrentPosition(neighbour) + " Dist: " + dist);
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
		//System.out.println("Result: " + result);
		return result;
	}
	
	/***
	 * Returns the next state Field without a given State
	 * 
	 * @param state
	 * @return
	 */
	public Position getNextPassablePositionByStateWithoutState (State state, State without) {
		logger.trace("Getting the next state field without a given state");
		Position p = this.robot.getPosition();
		
		//gibt null zurück wenn Position nicht in Map und nicht state hat
		if (!map.containsKey(p) || !map.get(p).containsState(state))
			return null;
		
		boolean flag = true;
		
		Set<Position> visited = new HashSet<Position>();
		visited.add(p);
		Queue<Position> nodes = new LinkedList<Position>();
		nodes.add(p);

		//tmp results hinzufügen
		List<Position> tmpResult = new LinkedList<Position>();
		Position result = null;
		
		int dist = Integer.MAX_VALUE;
		
		while (flag && !nodes.isEmpty()) {
			Position currentNodePosition = nodes.poll();
			if (!map.get(currentNodePosition).containsState(without)) {
				//Position kann zu Ergebnis gehören
				int actDist = getDistanceFromPositionToPosition(p, currentNodePosition);
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
				//füge alle nachbar Felder der Nodeliste hinzu aber nur wenn sie existieren und den state enthalten
				//und noch nicht besucht wurden
				for (Position neighbour : helper.getNeighbourPositions(currentNodePosition, false)) {
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

	public List<Position> getPath(Position destination) {
		return helper.findPath(this.robot.getPosition(), destination);
	}
	
	public List<Position> getPathFromTo (Position start, Position destination)	{
		return helper.findPath(start, destination);
	}
	
	/***
	 * Returns the yet unknown field
	 * gibt Position von bekannter passierbarer Stelle zurück
	 * 
	 * @return
	 */
	public Map<String, RobotDestinationCalculation> getNextUnknownFields(Map<String, RobotDestinationCalculation> information, int maxAway) {

		if (this.containsWorldState(State.createState("Discovered"))) {
			System.out.println("Welt schon erkundet!");
			return null;
		}
		
		//Position des Masters und aller Roboter
		Position p = this.robot.getPosition();
		
		for (RobotDestinationCalculation rdc : information.values()) {
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
							currentNodePosition, false)) {
						//bedeuted nimm nur auf wenn noch nicht gesehen wurde
						if (!map.containsKey(neighbour)) {
							//Prüfung anders umsetzen: wenn von allen position so und so weit entfernt nimm das feld
							boolean proofDist = true;
							int minDistToOther = maxAway;
							for (RobotDestinationCalculation proof : information.values())
							{
								if (!proof.getName().equals(rdc.getName()))
								{
									//wenn es eine beschriebene Position gibt dann Prüfe diese
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
								//alles stimmt nimm Position und setze sie als neue
								rdc.newDest = currentNodePosition;
								flag = false;
								break;
							}
							else 
							{
								//nimm Position erstmal wenn sie besser ist als letzte gefundene
								if (minDistToOther > rdc.distMax) {
									rdc.newDest = currentNodePosition;
									rdc.distMax = minDistToOther;
								}
							}							
						} else {
							//prüfe ob visited noch nicht Nachbar enthält und
							//ob map Nachbar enthält und ob Nachbar passierbar ist
							if (!visited.contains(neighbour) && map.get(neighbour).isPassable()) {
								visited.add(neighbour);
								nodes.add(neighbour);
							}
						}
					}
				}
			}
		}
				
		//outPutInformation(information);
		return information;
	}	
	
	/*private void outPutInformation (Map <String, RobotDestinationCalculation> information)
	{
		for (RobotDestinationCalculation out : information.values())
		{			
			System.out.print("*****Name: " + out.getName() + " " + out.needNew + " NewDest: " + out.newDest + " OldDest: " + out.oldDest );
			for (RobotDestinationCalculation proof : information.values()) {
				if (!proof.getName().equals(out.getName()))
				{
					if (out.hasPostion() && proof.hasPostion())
					{
						System.out.print(" Ab: " + getDistanceFromPositionToPosition(out.getActualPosition(), proof.getActualPosition()));
					}
				}
			}
			System.out.println("");
		}
	}*/
	
	public Position getNextUnknownFieldPosition() {
		Position p = this.robot.getPosition();
		return this.getNextUnknownFieldPosition(p);
	}

	/***
	 * Returns the yet unknown field
	 * gibt Position von bekannter passierbarer Stelle zurück die neben unknown position liegt
	 * 
	 * @return
	 */
	public Position getNextUnknownFieldPosition(Position p) {

		logger.trace("getNextUnknownFieldPosition start");
		long startTime = System.nanoTime();

		Position result = null;
		List<Position> tmpResult = new LinkedList<Position>();
		boolean flag = true;

		Set<Position> visited = new HashSet<Position>();
		visited.add(p);
		Queue<Position> nodes = new LinkedList<Position>();
		nodes.add(p);

		while (flag && !nodes.isEmpty()) {
			Position currentNodePosition = nodes.poll();
			for (Position neighbour : helper.getNeighbourPositions(
					currentNodePosition, false)) {
				//bedeuted nimm nur auf wenn noch nicht gesehen wurde
				if (!map.containsKey(neighbour)) {
					if (!tmpResult.isEmpty()
							&& getDistanceFromPositionToPosition(p, currentNodePosition) > getDistanceFromPositionToPosition(p, tmpResult
									.get(0))) {
						flag = false;
						break;
					}
					tmpResult.add(currentNodePosition);
				} else {
					//nachbar muss in Map enthalten sein
					//prüfe ob visited noch nicht Nachbar enthält und
					//ob Nachbar passierbar ist
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

		long endTime = System.nanoTime();
		logger.info("Determinig the next Unknown field position took "
				+ (endTime - startTime) + " ns.");
		logger.trace("getNextUnknownFieldPosition end");

		return result;
	}	
	
	public Position getNextUnknownRelativeFieldPosition(Position relative) {
		Position p = this.robot.getPosition();
		return getNextUnknownRelativeFieldPosition(p, relative);
	}
	
	/***
	 * Returns the yet unknown field
	 * gibt Position von bekannter passierbarer Stelle zurück die neben unknown position liegt
	 * 
	 * @return
	 */
	public Position getNextUnknownRelativeFieldPosition(Position p, Position relative) {
		
		if (relative == null)
			return getNextUnknownFieldPosition(p);

		Position result = null;
		List<Node> tmpResult = new LinkedList<Node>();
		
		boolean flag = true;

		Set<Position> visited = new HashSet<Position>();
		visited.add(p);
		
		PriorityQueue<Node> queue = new PriorityQueue<Node>();
		Node start = new Node(p, getDistanceFromPositionToPosition(p, relative));
		queue.add(start);

		int dist = Integer.MAX_VALUE;
		
		while (flag && !queue.isEmpty()) {
			Node currentNode = queue.poll();
			for (Position neighbour : helper.getNeighbourPositions(
					currentNode.getCurrentPosition(), false)) {
				//bedeuted nimm nur auf wenn noch nicht gesehen wurde
				if (!map.containsKey(neighbour)) {
					int actDist = currentNode.getWeight();
					if (actDist <= dist) {
						//System.out.println("actDist: " + actDist);
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
					//prüfe ob visited noch nicht Nachbar enthält und
					//ob map Nachbar enthält und ob Nachbar passierbar ist
					if (!visited.contains(neighbour) && map.get(neighbour).isPassable()) {
						int actDist = getDistanceFromPositionToPosition(p, neighbour) + getDistanceFromPositionToPosition(neighbour, relative);
						Node n = new Node(neighbour, actDist);
						visited.add(neighbour);
						queue.add(n);
					}
				}
			}
		}
		
		//System.out.println("***Test: " + robot.getName() + " : " + tmpResult + " " + relative + " " + p);
		
		if(!tmpResult.isEmpty()){
			int index = random.nextInt(tmpResult.size());
			result = tmpResult.get(index).getCurrentPosition();
		}
		return result;
	}
	
	private int getDistanceFromPositionToPosition(Position x, Position y) {
		//return Math.max(Math.abs(x.getX() - y.getX()), Math.abs(x.getY() - y.getY()));
		
		int deltaX = 0, deltaY = 0;

		deltaX = x.getX() - y.getX();
		deltaY = x.getY() - y.getY();
		deltaX = deltaX < 0 ? -deltaX : deltaX;
		deltaY = deltaY < 0 ? -deltaY : deltaY;

		return deltaX < deltaY ? deltaY : deltaX;
	}

	public boolean isPassable(Position position) {
		return map.containsKey(position) && map.get(position).isPassable();
	}
	
	public boolean hasState(Position position, State state) {
		return map.containsKey(position) && map.get(position).containsState(state);
	}
	
	public Field getField (Position position) {
		return map.get(position);
	}
	
	public boolean containsWorldState (State state) {
		return this.worldStates.contains(state);
	}
	
	public boolean addWorldState (State state) {
		return this.worldStates.add(state);
	}
	
	public boolean removeWorldState (State state) {
		return this.worldStates.remove(state);
	}
	
	public ExchangeMeasurement getHoleMeasurement () {
		ExchangeMeasurement em = new ExchangeMeasurement("", "", 0);
		//WorldStates durchlaufen
		em.addWorldIntegerNumber(4);
		em.addWorldStatesStringNumber(this.worldStates.size());
		for (State s : this.worldStates) {
			em.addWorldStatesStringByteNumber(s.getName().getBytes().length);
		}
		//Map durchlaufen
		em.addWorldPositionCount(map.keySet().size() * 2);
		em.addWorldIntegerNumber(map.keySet().size() * 4);
		
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
		//world fields hinzufügen
		for (Field field : map.values()){
			cleaningrobots.Field f = field.exportModel(config);
			if (f != null)
				modelMap.getFields().add(f);
		}
		
		//World states hinzufügen
		for (State state : worldStates){
			modelMap.getWorldStates().add(state.exportModel());
		}
		
		return modelMap;
	}
}

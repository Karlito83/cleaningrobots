package de.tud.swt.cleaningrobots.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Random;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cleaningrobots.CleaningrobotsFactory;
import de.tud.swt.cleaningrobots.RobotCore;

public class World {

	private final Random random = new Random();

	private final Logger logger = LogManager.getRootLogger();

	private RobotCore robot;
	private Map<Position, Field> map;
	//koordinaten von großer karte 
	private int minX;
	private int minY;
	//dimensionen von kleiner Karte
	private int xDim;
	private int yDim;

	private WorldHelper helper;

	public World(RobotCore robot) {
		this.robot = robot;
		this.map = new HashMap<Position, Field>();
		this.helper = new WorldHelper(this);
	}

	public void addField(Field newField) {
		//erweitere die Dimensionen der Karte anhand des neuen Feldes
		if (newField.getX() < this.minX) {
			this.minX = newField.getX();
		}
		if (newField.getY() < this.minY) {
			this.minY = newField.getY();
		}
		if (newField.getX() > this.minX + this.xDim) {
			this.xDim = newField.getX() + this.minX;
		}
		if (newField.getY() > this.minY + this.yDim) {
			this.yDim = newField.getY() + this.minY;
		}
		//Wenn Feld mit Positon schon in Map drin ist dann update ansonsten füge hinzu
		Position coordinates = new Position(newField.getX(), newField.getY());
		if (map.containsKey(coordinates)) {
			updateField(newField);
		} else {
			map.put(coordinates, newField);
		}
	}

	/**
	 * Update das Feld
	 * @param newField
	 */
	private void updateField(Field newField) {
		Position coordinates = new Position(newField.getX(), newField.getY());
		Field oldField = map.get(coordinates);
		
		for(State oldState : oldField.getStates()){
			if (!robot.getSupportedStates().contains(oldState)){
				newField.addState(oldState);
			}
		}
		
		map.put(coordinates, newField);
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
	public Position getNextFieldByState(State state) {
		logger.trace("Getting the next passable field without the state");
		Position p = this.robot.getPosition();
		
		if (!map.containsKey(p))
			return null;
		
		Set<Position> visited = new HashSet<Position>();
		visited.add(p);
		Queue<Position> nodes = new LinkedList<Position>();
		nodes.add(p);

		//tmp results hinzufügen
		List<Position> tmpResult = new LinkedList<Position>();
		Position result = null;
		
		int dist = Integer.MAX_VALUE;
		
		while (!nodes.isEmpty()) {
			Position currentNodePosition = nodes.poll();
			if (!map.get(currentNodePosition).getStates().contains(state)) {
				//Position kann zu Ergebnis gehören
				int actDist = getDistanceFromCurrentPosition(currentNodePosition);
				if (actDist < dist) {
					tmpResult.clear();
					tmpResult.add(currentNodePosition);
					dist = actDist;
				} else if (actDist == dist) {
					tmpResult.add(currentNodePosition);
				}
				//System.out.println("Dist: " + dist + " actDist: " + actDist + " Node: " + currentNodePosition + " Size: " + nodes.size());
				//Ignoriere wenn zu weit entfernt
			} else {
				//füge alle nachbar Felder der Nodeliste hinzu aber nur wenn sie existieren und den state enthalten
				//und noch nicht besucht wurden und ihre distanz kleiner kleich der minimalen distanz ist
				for (Position neighbour : helper.getNeighbourPositions(currentNodePosition, false)) {
					if (map.containsKey(neighbour) && map.get(neighbour).isPassable() && !visited.contains(neighbour) && getDistanceFromCurrentPosition(neighbour) <= dist) {
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
		System.out.println("Result: " + result);
		return result;
	}
	
	/***
	 * Returns the next state Field without a given State
	 * 
	 * @param state
	 * @return
	 */
	public Position getNextFieldByStateWithoutState (State state, State without) {
		logger.trace("Getting the next state field without a given state");
		Position p = this.robot.getPosition();
		
		if (!map.containsKey(p) || !map.get(p).getStates().contains(state))
			return null;
		
		Set<Position> visited = new HashSet<Position>();
		visited.add(p);
		Queue<Position> nodes = new LinkedList<Position>();
		nodes.add(p);

		//tmp results hinzufügen
		List<Position> tmpResult = new LinkedList<Position>();
		Position result = null;
		
		int dist = Integer.MAX_VALUE;
		
		while (!nodes.isEmpty()) {
			Position currentNodePosition = nodes.poll();
			if (!map.get(currentNodePosition).getStates().contains(without)) {
				//Position kann zu Ergebnis gehören
				int actDist = getDistanceFromCurrentPosition(currentNodePosition);
				if (actDist < dist) {
					tmpResult.clear();
					tmpResult.add(currentNodePosition);
					dist = actDist;
				} else if (actDist == dist) {
					tmpResult.add(currentNodePosition);
				}
				//Ignoriere wenn zu weit entfernt
			} else {
				//füge alle nachbar Felder der Nodeliste hinzu aber nur wenn sie existieren und den state enthalten
				//und noch nicht besucht wurden und ihre distanz kleiner kleich der minimalen distanz ist
				for (Position neighbour : helper.getNeighbourPositions(currentNodePosition, false)) {
					if (map.containsKey(neighbour) && map.get(neighbour).getStates().contains(state) && !visited.contains(neighbour) && getDistanceFromCurrentPosition(neighbour) <= dist) {
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
	public Position getNextUnknownFieldPosition() {

		logger.trace("getNextUnknownFieldPosition start");
		long startTime = System.nanoTime();

		Position result = null;
		List<Position> tmpResult = new LinkedList<Position>();
		Position p = this.robot.getPosition();
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
							&& getDistanceFromCurrentPosition(currentNodePosition) > getDistanceFromCurrentPosition(tmpResult
									.get(0))) {
						flag = false;
						break;
					}
					tmpResult.add(currentNodePosition);
				}
				//prüfe ob visited noch nicht Nachbar enthält und
				//ob map Nachbar enthält und ob Nachbar passierbar ist
				if (!visited.contains(neighbour) && map.containsKey(neighbour)
						&& map.get(neighbour).isPassable()) {
					visited.add(neighbour);
					nodes.add(neighbour);
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

	private int getDistanceFromCurrentPosition(Position currentNodePosition) {
		int deltaX = 0, deltaY = 0;

		Position currentRobotPosition = this.robot.getPosition();
		deltaX = currentRobotPosition.getX() - currentNodePosition.getX();
		deltaY = currentRobotPosition.getY() - currentNodePosition.getY();
		deltaX = deltaX < 0 ? -deltaX : deltaX;
		deltaY = deltaY < 0 ? -deltaY : deltaY;

		return deltaX < deltaY ? deltaX : deltaY;
	}

	public boolean isPassable(Position position) {
		return map.containsKey(position) && map.get(position).isPassable();
	}
	
	public boolean hasState(Position position, State state) {
		return map.containsKey(position) && map.get(position).getStates().contains(state);
	}
	
	public Field getField (Position position) {
		return map.get(position);
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
	
	public cleaningrobots.WorldPart exportModel() {
		//TODO: Consider caching
		//cleaningrobots.World modelWorld = null;
		cleaningrobots.Map modelMap = null;
		
		//modelWorld = CleaningrobotsFactory.eINSTANCE.createWorld();
		modelMap = CleaningrobotsFactory.eINSTANCE.createMap();
		modelMap.setXdim(xDim);
		modelMap.setYdim(yDim);
		for (Field field : map.values()){
			modelMap.getFields().add(field.exportModel());
		}
		//modelWorld.getChildren().add(modelMap);
		
		return modelMap;
	}
}

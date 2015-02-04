package de.tud.swt.cleaningrobots;

import java.util.Collection;
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

public class World {

	private final Random random = new Random();

	private final Logger logger = LogManager.getRootLogger();

	private Robot robot;
	private Map<Position, Field> map;
	private int minX;
	private int minY;
	private int xDim;
	private int yDim;

	private WorldHelper helper;

	public World(Robot robot) {
		this.robot = robot;
		this.map = new HashMap<Position, Field>();
		this.helper = new WorldHelper(this);
	}

	public void addField(Field newField) {
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
		Position coordinates = new Position(newField.getX(), newField.getY());
		if (map.containsKey(coordinates)) {
			updateField(newField);
		} else {
			map.put(coordinates, newField);
		}
	}

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
	 * Returns the next Field with a State identified by a state name
	 * 
	 * @param stateName
	 * @return
	 */
	public Field getNextFieldByState(String stateName) {
		logger.warn("Getting the next field by stateName is not yet implemented");
		return null;
	}

	public List<Position> getPath(Position destination) {
		return helper.findPath(this.robot.getPosition(), destination);
	}

	/***
	 * Returns the yet unknown field
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
				if (!map.containsKey(neighbour)) {
					if (!tmpResult.isEmpty()
							&& getDistanceFromCurrentPosition(currentNodePosition) > getDistanceFromCurrentPosition(tmpResult
									.get(0))) {
						flag = false;
						break;
					}
					tmpResult.add(currentNodePosition);
				}
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
}

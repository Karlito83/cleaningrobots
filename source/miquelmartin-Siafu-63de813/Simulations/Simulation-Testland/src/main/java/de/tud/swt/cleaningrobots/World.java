package de.tud.swt.cleaningrobots;

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

public class World {

	Random random = new Random(); 
	
	Robot robot;
	Map<Position, Field> map;
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
		if (newField.getX()<this.minX){
			this.minX = newField.getX();
		}
		if (newField.getY()<this.minY){
			this.minY = newField.getY();
		}
		if (newField.getX()>this.minX+this.xDim){
			this.xDim = newField.getX()+this.minX;
		}
		if (newField.getY()>this.minY+this.yDim){
			this.yDim = newField.getY()+this.minY;
		}
		Position coordinates = new Position(newField.getX(), newField.getY());
		if (map.containsKey(coordinates)) {
			Field oldField = map.get(coordinates);
			updateField(oldField, newField, robot.getSupportedStates());
		} else {
			map.put(coordinates, newField);
		}
	}

	private void updateField(Field oldField, Field newField,
			Collection<State> supportedStates) {
		System.out.println("Updating Field information is not yet implemented");
		//throw new RuntimeException("Updating Field information is not yet implemented");
	}

	public void addFields(Iterable<Field> fields) {
		for (Field field : fields) {
			this.addField(field);
		}
	}

	/***
	 * Returns the next Field with a State identified by a state name
	 * @param stateName
	 * @return
	 */
	public Field getNextFieldByState(String stateName) {
		// TODO Auto-generated method stub
		System.out.println("Getting the next field by stateName is not yet implemented");
		return null;
		//return null;
	}

	public List<Position> getPath(Position destination){
		
		System.err.println("getpath: " + this.robot.getPosition() + ", " + destination);
		
		return helper.findPath(this.robot.getPosition(), destination);
	}
	
	/***
	 * Returns the yet unknown field 
	 * @return
	 */
	public Position getNextUnknownPosition() {
		Position result = null;
		Position p = this.robot.getPosition();
		
		//Float minDistance = Float.POSITIVE_INFINITY;
		
		Set<Position> visited = new HashSet<Position>();
		visited.add(p);
		Queue<Position> nodes = new LinkedList<Position>();
		nodes.add(p);
		
		System.err.println("nextUnknownposition start");

		while (result==null&&!nodes.isEmpty()){
			Position currentNodePosition = nodes.poll();
			for(Position neighbour : helper.getNeighbourPositions(currentNodePosition, false)){
				if(!map.containsKey(neighbour)){
					result = currentNodePosition;
					break;
				}
				if(!visited.contains(neighbour)&&map.containsKey(neighbour)&&map.get(neighbour).isPassable()){
					visited.add(neighbour);
					nodes.add(neighbour);
				}
			}
		}
		
		//System.err.println("nextUnknownposition end");
		
		return result;
	}

	public boolean isPassable(Position position) {
		return map.containsKey(position) && map.get(position).isPassable();
	}
}

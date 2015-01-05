package de.tud.swt.cleaningrobots;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import cleaningrobots.CleaningrobotsFactory;

public class World {

	// TODO: Be more memory-efficient?
	private class XYCoordinates {
		private int x;
		private int y;
	}

	Robot robot;
	Map<XYCoordinates, Field> map;
	private int minX;
	private int minY;
	private int xDim;
	private int yDim;

	public World(Robot robot) {
		this.robot = robot;
		this.map = new HashMap<XYCoordinates, Field>();
	}

	public void addField(Field newField) {
		XYCoordinates coordinates = new XYCoordinates();
		coordinates.x = newField.getX();
		coordinates.y = newField.getY();
		if (map.containsKey(coordinates)) {
			Field oldField = map.get(coordinates);
			updateField(oldField, newField, robot.getSupportedStates());
		} else {
			map.put(coordinates, newField);
		}
	}

	private void updateField(Field oldField, Field newField,
			Collection<State> supportedStates) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Updating Field information is not yet implemented");
	}

	public void addFields(Iterable<Field> fields) {
		for (Field field : fields) {
			if (field.getX()<this.minX){
				this.minX = field.getX();
			}
			if (field.getY()<this.minY){
				this.minY = field.getY();
			}
			if (field.getX()>this.minX+this.xDim){
				this.xDim = field.getX()+this.minX;
			}
			if (field.getY()>this.minY+this.yDim){
				this.yDim = field.getY()+this.minY;
			}
			this.addField(field);
		}
	}

	public Field getNextFieldByState(String stateName) {
		// TODO Auto-generated method stub
		throw new RuntimeException("Getting the next field by stateName is not yet implemented");
		//return null;
	}
	
	public Field getNextUnknownField() {
		// TODO Auto-generated method stub
		throw new RuntimeException("Getting the next unknown field is not yet implemented");
		//return null;
	}
}

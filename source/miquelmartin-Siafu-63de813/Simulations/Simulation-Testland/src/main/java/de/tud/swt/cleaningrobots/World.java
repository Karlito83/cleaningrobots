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

	}

	public void addFields(Iterable<Field> fields) {
		for (Field field : fields) {
			this.addField(field);
		}
	}

	public Field getNextFieldByState(String stateName) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public Field getNextUnknownField() {
		// TODO Auto-generated method stub
		return null;
	}
}

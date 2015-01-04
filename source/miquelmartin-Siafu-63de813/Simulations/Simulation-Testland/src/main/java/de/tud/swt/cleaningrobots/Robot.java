package de.tud.swt.cleaningrobots;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Robot {

	private List<ISensor> sensors;
	private String name;
	private World world;

	public Robot(String name) {
		this.name = name;
		this.world = new World(this);
	}

	public String getName() {
		return name;
	}

	public void addSensor(ISensor sensor) {
		sensors.add(sensor);
	}

	public Collection<State> getSupportedStates() {
		Set<State> supportedStates = new HashSet<State>();
		for (ISensor sensor : sensors) {
			supportedStates.addAll(sensor.getSupportedStates());
		}
		return supportedStates;
	}

	public World getWorld() {
		return this.world;
	}
}

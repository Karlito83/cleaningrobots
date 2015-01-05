package de.tud.swt.cleaningrobots;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Robot {

	private List<ISensor> sensors;
	private String name;
	private World world;
	private List<Behaviour> behaviours;
	private Position destination;
	private IPositionProvider positionProvider;

	private static int counter = 1;
	
	
	public Robot(IPositionProvider positionProvider)
	{
		this("Robbi" + counter++, positionProvider);
	}
	
	public Robot(String name, IPositionProvider positionProvider) {
		
		this.name = name;
		this.positionProvider = positionProvider;
		this.world = new World(this);
	}

	public void action() {
		boolean flag = false;
		try {
			for (Behaviour behaviour : behaviours) {
				if (behaviour.action()) {
					flag = true;
				}
			}
			if (!flag) {
				String message = "The robot \"{0}\" does not know what to do and feels a bit sad now..."
						+ "\nPlease specify appropriate behaviours for him to avoid that.";
				throw new RuntimeException(String.format(message,
						this.getName()));
			}

		} catch (Exception e) {
			throw new RuntimeException(
					"There is no Exception handling defined if a Behaviour goes wrong...");
		}
	}
	
	/***
	 * Adds a new {@link Behaviour} to the {@link List} of behaviours This
	 * function behaves like {@link List#add(int, Object)}.
	 * 
	 * @param index
	 * @param behaviour
	 */
	public void addBehaviour(int index, Behaviour behaviour) {
		this.behaviours.add(index, behaviour);
	}
	
	public void addSensor(ISensor sensor) {
		sensors.add(sensor);
	}

	/***
	 * Adds a {@link Behaviour} to the list with the least priority
	 * 
	 * @param behaviour
	 */
	public boolean appendBehaviour(Behaviour behaviour) {
		return this.behaviours.add(behaviour);
	}
	
	public Position getDestination() {
		return destination;
	}

	public String getName() {
		return name;
	}

	public Position getPosition()
	{
		return positionProvider.getPosition();
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

	/***
	 * Removes a {@link Behaviour} from the {@link List} of behaviours. This
	 * function behaves like {@link List#remove(int)}.
	 * 
	 * @param behaviour
	 * @return
	 */
	public boolean removeBehaviour(Behaviour behaviour) {
		return this.behaviours.remove(behaviour);
	}

	public void setDestination(Position destination) {
		this.destination = destination;
	}
	
	public void setName(String name) {
		this.name = name;
	}
}

package de.tud.swt.cleaningrobots;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import de.nec.nle.siafu.utils.SiafuGradientCache;


public class Robot {

	private Collection<ISensor> sensors;
	private String name;
	private World world;
	private List<Behaviour> behaviours;
	private IPositionProvider positionProvider;
	private INavigationController navigationController;

	private static int counter = 1;
	
	
	public Robot(IPositionProvider positionProvider, INavigationController navigationController)
	{
		this("Robby_" + counter++, positionProvider, navigationController);
	}
	
	public Robot(String name, IPositionProvider positionProvider, INavigationController navigationController) {
		
		this.name = name;
		this.positionProvider = positionProvider;
		this.world = new World(this);
		this.sensors = new LinkedList<ISensor>();
		this.behaviours = new LinkedList<Behaviour>();
		this.navigationController = navigationController;
	}

	public void action() {
		boolean flag = false;
		try {
			getSensorData();
			if (getDestination()==null){
				for (Behaviour behaviour : behaviours) {
					if (behaviour.action()) {
						flag = true;
					}
				}
				if (!flag) {
					String message = "The robot \"%s\" does not know what to do and feels a bit sad now..."
							+ "\nPlease specify appropriate behaviours for him to avoid that.";
					throw new RuntimeException(String.format(message,
							this.getName()));
				}
			} else {
				System.out.println("move");
				navigationController.moveTowardsDestination();
			}

		} catch (Exception e) {
			e.printStackTrace();
			//System.out.println("There is no Exception handling defined if a Behaviour goes wrong...");
			throw new RuntimeException(
					"There is no Exception handling defined if a Behaviour goes wrong...");
		}
	}
	
	private void getSensorData() {
		if (sensors != null){
			for (ISensor sensor : sensors){
				world.addFields(sensor.getData());
			}
		}
		else {
			System.out.println(this.toString() + " has no sensors.");
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
		return navigationController.getDestination();
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
		this.navigationController.setDestination(destination, getPosition());
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}

	public void addBehaviour(Behaviour behaviour) {
		this.behaviours.add(behaviour);
	}

	public boolean isAtDestination() {
		return this.navigationController.isAtDestination();
	}
}

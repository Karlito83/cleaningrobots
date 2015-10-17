package de.tud.swt.cleaningrobots.old;

import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.emf.ecore.EObject;

import cleaningrobots.CleaningrobotsFactory;
import cleaningrobots.WorldPart;
import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.ICommunicationProvider;
import de.tud.swt.cleaningrobots.INavigationController;
import de.tud.swt.cleaningrobots.IPositionProvider;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.model.World;
import de.tud.swt.cleaningrobots.util.EMFUtils;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;

public class OldRobot {

private final Logger logger = LogManager.getRootLogger();
	

	private Collection<ISensor> sensors;
	private String name;
	private World world;
	private List<Behaviour> behaviours;
	private IPositionProvider positionProvider;
	private INavigationController navigationController;
	private ICommunicationProvider communicationProvider;
	private List<Position> path;
	private Position destination;

	private static int counter = 1; // counter for the standard-name

	public OldRobot(IPositionProvider positionProvider,
			INavigationController navigationController,
			ICommunicationProvider communicationProvider) {
		this("Robby_" + counter++, positionProvider, navigationController,
				communicationProvider);
	}

	public OldRobot(String name, IPositionProvider positionProvider,
			INavigationController navigationController,
			ICommunicationProvider communicationProvider) {

		logger.info("Initializing robot \"" + name + "\"");

		this.name = name;
		this.positionProvider = positionProvider;
		//this.world = new World(this);
		this.sensors = new LinkedList<ISensor>();
		this.behaviours = new LinkedList<Behaviour>();
		this.navigationController = navigationController;
		this.path = null;
		this.destination = null;
		this.communicationProvider = communicationProvider;
	}

	public void action() {
		try {
			getSensorData();
			getNearRobotsAndImportModel();
			executeBehaviours();
		} catch (Exception e) {
			logger.error(this + ": Error while action().", e);
		}
	}
	
	public INavigationController getINavigationController()
	{
		return this.navigationController;
	}

	private void executeBehaviours() throws Exception {
		try {
			boolean flag = false;
			for (Behaviour behaviour : behaviours) {
				if (behaviour.action()) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				String message = "The robot \"%s\" does not know what to do and feels a bit sad now..."
						+ "\nPlease specify appropriate behaviours for him to avoid that.";
				logger.warn(String.format(message, this.getName()));
			}
		} catch (Exception e) {
			logger.error(
					"There is no Exception handling defined if a Behaviour goes wrong...",
					e);
		}
		
	}

	private void getNearRobotsAndImportModel() {
		
		long endTime, startTime = System.nanoTime();
		logger.trace("enter getNearRobotsAndImportModel");
		
		List<RobotCore> nearRobots = this.communicationProvider.getNearRobots(10);
		nearRobots.remove(this);
		for (RobotCore nearRobot : nearRobots) {
			EObject model = nearRobot.exportModel(new ImportExportConfiguration());
			importModel(model);
		}
		
		endTime = System.nanoTime();
		logger.info("Importing the data from " + nearRobots.size() + " other agents took " + (endTime - startTime) + " ns.");
		
		logger.trace("exit getNearRobotsAndImportModel");
	}

	private void importFieldsFromWorldModel(cleaningrobots.WorldPart worldPart) {
		// Maybe an arrayList is better here?
		if (worldPart instanceof cleaningrobots.Map) {
			cleaningrobots.State blockedState = CleaningrobotsFactory.eINSTANCE
					.createState();
			blockedState.setName("Blocked");
			for (cleaningrobots.Field modelField : ((cleaningrobots.Map) worldPart)
					.getFields()) {
				boolean isBlocked = EMFUtils.listContains(modelField
						.getStates(), blockedState);
				Field f = new Field(modelField.getPos().getXpos(), modelField.getPos().getYpos(), !isBlocked);
				for (cleaningrobots.State modelState : modelField.getStates()) {
					State state = State.createState(modelState.getName());
					f.addState(state);
				}
				world.addField(f);
			}
		}
		if (worldPart instanceof cleaningrobots.World) {
			for (WorldPart innerWorldPart : ((cleaningrobots.World) worldPart)
					.getChildren()) {
				importFieldsFromWorldModel(innerWorldPart);
			}
		}
	}

	private void importModel(EObject model) {
		if (model instanceof cleaningrobots.Robot) {
			logger.trace("importing model " + model);
			cleaningrobots.Robot robot = (cleaningrobots.Robot) model;
			cleaningrobots.WorldPart rootWorldPart = robot.getWorld();
			importFieldsFromWorldModel(rootWorldPart);
		} else {
			logger.warn("unknown model " + model);
		}
	}

	private void getSensorData() {
		try {
			if (sensors != null) {
				for (ISensor sensor : sensors) {
					world.addFields(sensor.getData());
				}
			} else {
				logger.warn(this.toString() + " has no sensors.");
			}
		} catch (Exception e) {
			throw e;
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
		return this.destination;
	}

	public String getName() {
		return name;
	}

	public Position getPosition() {
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

	/***
	 * Sets the destination of the robot
	 * 
	 * @param destination
	 *            If null, the destination will be reset and it is assumed, that
	 *            the robot is at the destination
	 */
	public void setDestination(Position destination) {
		logger.debug("Set destination of " + this + " to " + destination + ".");
		if (destination == null) {
			this.destination = null;
			this.path = null;
		}
		this.destination = destination;
		refreshPath();
	}

	private void refreshPath() {
		this.path = this.world.getPath(destination);
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
		return path == null;
	}

	public void moveTowardsDestination() {
		if (this.path != null) {
			if (this.path.size() == 0) {
				refreshPath();
				System.err.println(this.destination);
			}
			Position nextPosition = this.path.get(0);
			if (world.isPassable(nextPosition)) {
				path.remove(nextPosition);
				if (nextPosition.equals(destination)) {
					destination = null;
					path = null;
				}
				this.setPosition(nextPosition);
			} else {
				refreshPath();
			}
		} else {
			logger.warn(getName()
					+ ": can't move towards destination because no destination given.");
		}
	}

	private void setPosition(Position position) {
		logger.debug("Set new position of " + this + " to " + position + ".");
		this.navigationController.setPosition(position);
	}

	public cleaningrobots.Robot exportModel() {
		// TODO: Consider caching
		cleaningrobots.Robot result = null;

		try {
			cleaningrobots.Robot robot = CleaningrobotsFactory.eINSTANCE
					.createRobot();
			//robot.setWorld(world.exportModel());
			robot.setName(getName());
			for (State state : getSupportedStates()) {
				robot.getKnownStates().add(state.exportModel());
			}
			result = robot;
		} catch (Exception e) {
			logger.error("An error occured while exporting the model", e);
		}

		return result;
	}
}
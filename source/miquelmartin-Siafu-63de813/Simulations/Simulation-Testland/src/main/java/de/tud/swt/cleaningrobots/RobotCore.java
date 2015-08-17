package de.tud.swt.cleaningrobots;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import cleaningrobots.CleaningrobotsFactory;
import de.tud.swt.cleaningrobots.goals.Goal;
import de.tud.swt.cleaningrobots.hardware.Accu;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.model.World;

public class RobotCore extends Robot {
	
	private List<RobotRole> roles;

	private final Logger logger = LogManager.getRootLogger();
	
	private String name;
	private World world;
	
	private double energieTest;
	private Accu accu;
	
	//Loadsstation info
	public boolean isLoadStation;
	public boolean isLoading;
	public Position loadStationPosition;
	
	private double maxEnergieNeed;
	private double minEnergieNeed;
	private double actualEnergieNeed;
	
	private List<Behaviour> behaviours;
	private List<Goal> goals;
	private List<HardwareComponent> hardwarecomponents;
	private Set<State> supportedStates;
	
	private IPositionProvider positionProvider;
	private INavigationController navigationController;
	private ICommunicationProvider communicationProvider;
	
	private List<Position> path;
	private Position destination;

	private static int counter = 1; // counter for the standard-name

	public RobotCore(IPositionProvider positionProvider,
			INavigationController navigationController,
			ICommunicationProvider communicationProvider, Accu accu) {
		this("Robby_" + counter++, positionProvider, navigationController,
				communicationProvider, accu);
	}

	public RobotCore(String name, IPositionProvider positionProvider,
			INavigationController navigationController,
			ICommunicationProvider communicationProvider, Accu accu) {

		logger.info("Initializing robot \"" + name + "\"");

		this.name = name;
		this.world = new World(this);
		
		//if accu==null the you do not drive to Loadstation
		this.accu = accu;
		
		energieTest = 0.0;
		
		this.roles = new ArrayList<RobotRole>();
		this.hardwarecomponents = new LinkedList<HardwareComponent>();
		this.behaviours = new LinkedList<Behaviour>();
		this.goals = new LinkedList<Goal>();
		this.supportedStates = new HashSet<State>();
		
		this.navigationController = navigationController;
		this.communicationProvider = communicationProvider;
		this.positionProvider = positionProvider;
		
		this.path = null;
		this.destination = null;
		
		isLoading = false;
		loadStationPosition = this.getPosition();
		//System.out.println(loadStationPosition);
		
		//setze Destination informationen
		loadDestination = false;
		destinationSet = false;
		
	}
	
	//action operactions
	public boolean action() {
		//boolean result;
		try {
			//executeBehaviours();
			runGoals();
			getEnergieConsumption();
		} catch (Exception e) {
			logger.error(this + ": Error while action().", e);
		}
		for (Goal goal : goals) {
			//wenn nicht nur noch optionale Goals da sind ist er nicht fertig
			if (!goal.isOptional())
				return false;
		}
		return true;
	}
	
	private void runGoals () {
		boolean test = false;
		for (Goal goal : goals) {
			goal.run();
			if (goal.postCondition())
				test = true;			
		}		
		if (test) {
			List<Goal> copy = new ArrayList<Goal>(goals);
			for (Goal goal : copy) {
				if (goal.postCondition()) {
					goals.remove(goal);
				}
			}
		}
	}
	
	private void executeBehaviours() throws Exception {
		try {
			//Make action of all Behaviours
			for (Behaviour behaviour : behaviours) {
				behaviour.action();
			}
		} catch (Exception e) {
			logger.error(
					"There is no Exception handling defined if a Behaviour goes wrong...",
					e);
		}
		
	}
	
	//Get Energie values of the robot
	public double getMinEnergie() {
		return this.minEnergieNeed;
	}
	
	public double getMaxEnergie() {
		return this.maxEnergieNeed;
	}
	
	public double getActualEnergie() {
		return this.actualEnergieNeed;
	}
	
	public void getEnergieConsumption() {
		actualEnergieNeed = 0.0;
		for (HardwareComponent hard : hardwarecomponents) {
			actualEnergieNeed += hard.getActualEnergie();
		}
		if (accu != null) {
			accu.use(actualEnergieNeed);
			System.out.println("AktuelleKWh: " + accu.getActualKWh());
		}
		//energieTest += actualEnergieNeed;
	}
	
	public void calculateMaxMinEnergieConsumption () {
		minEnergieNeed = 0.0;
		maxEnergieNeed = 0.0;
		for (HardwareComponent hard : hardwarecomponents) {
			minEnergieNeed += hard.getMinEnergie();
			maxEnergieNeed += hard.getMaxEnergie();
		}
	}
	
	public Accu getAccu() {
		return accu;
	}
	
	//Get Methods for all Objects
	public INavigationController getINavigationController () {
		return this.navigationController;
	}
	
	public ICommunicationProvider getICommunicationProvider () {
		return this.communicationProvider;
	}
	
	public List<HardwareComponent> getHardwarecomponents ()	{
		return this.hardwarecomponents;
	}
	
	public List<Goal> getGoals () {
		return this.goals;
	}
	
	public boolean hasActiveHardwareComponent (Components c) {
		for (HardwareComponent hard : hardwarecomponents) {
			if (hard.getComponents() == c && hard.isActive())
				return true;
		}
		return false;
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
		recalculateSupportedStates();
	}
	
	/***
	 * Removes a {@link Behaviour} from the {@link List} of behaviours. This
	 * function behaves like {@link List#remove(int)}.
	 * 
	 * @param behaviour
	 * @return
	 */
	public boolean removeBehaviour(Behaviour behaviour) {
		boolean result = this.behaviours.remove(behaviour);
		recalculateSupportedStates();
		return result;
	}

	public boolean addBehaviour (Behaviour behaviour) {
		boolean result = this.behaviours.add(behaviour);
		recalculateSupportedStates();
		return result;
	}
	
	public boolean addGoal (Goal goal) {
		boolean result = this.goals.add(goal);
		recalculateSupportedStates();
		return result;
	}
	
	public boolean removeGoal (Goal goal) {
		boolean result = this.removeGoal(goal);
		recalculateSupportedStates();
		return result;
	}

	public void addHardwareComponent(HardwareComponent component) {
		hardwarecomponents.add(component);
	}

	public Position getDestination() {
		return this.destination;
	}

	public Position getPosition() {
		return positionProvider.getPosition();
	}
	
	private void recalculateSupportedStates () {
		supportedStates.clear();
		for (Goal goal : goals) {
			for (State state : goal.getSupportedStates()) {
				if (!supportedStates.contains(state))
					supportedStates.add(state);
			}
		}
		for (Behaviour behavior : behaviours) {
			for (State state : behavior.getSupportedStates()) {
				if (!supportedStates.contains(state))
					supportedStates.add(state);
			}
		}
	}
	

	public Collection<State> getSupportedStates() {
		return supportedStates;
	}

	public World getWorld() {
		return this.world;
	}	
	
	private boolean loadDestination;
	private boolean destinationSet;
	
	public boolean isLoadDestination () {
		return loadDestination;
	}
	
	public boolean isDestinationSet () {
		return destinationSet;
	}
	
	public void setDestinationLoadStation () {
		logger.debug("Set destination of " + this + " to LoadStation Position.");		
		loadDestination = true;
		this.destination = loadStationPosition;
		refreshPath();
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
		loadDestination = false;
		if (destination == null) {
			this.destination = null;
			this.path = null;
		} else {
			this.destinationSet = true;
			this.destination = destination;
			refreshPath();
		}
	}

	private void refreshPath() {
		this.path = this.world.getPath(destination);
	}
	
	public List<Position> getPath (Position start, Position dest) {
		return this.world.getPathFromTo(start, dest);
	}
	
	public boolean isAtLoadDestination () {
		return path == null && loadDestination;
	}

	public boolean isAtDestination() {
		return path == null;
	}

	public void moveTowardsDestination() {		
		if (this.path != null) {
			//sollte nicht auftreten
			if (this.path.size() == 0) {
				//refreshPath();
				System.err.println(this.destination);
				path = null;
				return;
			}
			Position nextPosition = this.path.get(0);
			if (world.isPassable(nextPosition)) {
				path.remove(nextPosition);
				if (nextPosition.equals(destination)) {
					//an destination angekommen
					this.destinationSet = false;
					destination = null;
					path = null;
				}
				this.setPosition(nextPosition);
			} else {
				//da sich welt nicht verändert sollte das nicht vorkommen
				refreshPath();
			}
		} else {
			logger.warn(getName()
					+ ": can't move towards destination because no destination given.");
		}
	}

	/**
	 * Set the Position of the robot
	 * @param position
	 */
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
			robot.setWorld(world.exportModel());
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

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public List<RobotRole> getRoles () {
		return roles;
	}
	
	//Funktionalität von Robot
	@Override
	public boolean hasRole(RobotRole role) {
		return roles.contains(role);
	}

	@Override
	public boolean removeRole(RobotRole role) {
		return this.roles.remove(role);		
	}

	@Override
	public boolean addRole(RobotRole role) {
		return this.roles.add(role);		
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj!=null && obj instanceof RobotCore){
			RobotCore tmp = (RobotCore)obj;
			result = tmp.getName().equals(this.getName());
		}
		return result;
	}
}

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
import de.tud.swt.cleaningrobots.measure.RobotMeasurement;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.model.World;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;

public class RobotCore extends Robot {
	
	private RobotMeasurement measure;
	
	private List<RobotRole> roles;
	private List<RobotKnowledge> knowledge;

	private final Logger logger = LogManager.getRootLogger();
	
	private String name;
	private World world;
	
	private Accu accu;
		
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
	
	private DestinationContainer destinationContainer;
	
	private static int counter = 1; // counter for the standard-name
	
	//ob es Ladestation ist
	private boolean loadStation;
	//sagt ob er gerade lädt
	public boolean isLoading;
	
	private boolean shutDown;

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
		this.measure = new RobotMeasurement(name);
		
		//if accu==null the you do not drive to Loadstation
		this.accu = accu;
		
		this.loadStation = false;
		this.isLoading = false;
		this.shutDown = false;
		
		this.roles = new ArrayList<RobotRole>();
		this.hardwarecomponents = new LinkedList<HardwareComponent>();
		this.behaviours = new LinkedList<Behaviour>();
		this.goals = new LinkedList<Goal>();
		this.supportedStates = new HashSet<State>();
		this.knowledge = new LinkedList<RobotKnowledge>();
		
		this.navigationController = navigationController;
		this.communicationProvider = communicationProvider;
		this.positionProvider = positionProvider;
		this.destinationContainer = new DestinationContainer(this);
		
	}
	
	public boolean isShutDown () {
		return this.shutDown;
	}
	
	public boolean isLoadStation () {
		return this.loadStation;
	}
	
	public DestinationContainer getDestinationContainer () {
		return this.destinationContainer;
	}
	
	public RobotMeasurement getMeasurement () {
		return this.measure;
	}
	
	//action operactions
	public boolean action() {
				
		long startTime = System.nanoTime();
		
		//boolean result;
		try {
			//executeBehaviours();
			runGoals();
			getEnergieConsumption();
		} catch (Exception e) {
			logger.error(this + ": Error while action().", e);
		}
		
		long endTime = System.nanoTime();
		
		long time = endTime - startTime;
		measure.completeTime += time;
		measure.timeProTick.add((double) time);
		
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
			//wenn keine Ziele mehr mache alle Hardwarecomponenten aus
			if (goals.isEmpty())
			{
				System.out.println("Alle HardwareComponenten ausgeschaltet von " + this.getName());
				for (HardwareComponent hc : hardwarecomponents) {
					if (hc.isActive())
						hc.changeActive();
				}
				shutDown = true;
			}
			
		}
	}
	
	/*private void executeBehaviours() throws Exception {
		try {
			//Make action of all Behaviours
			for (Behaviour behaviour : behaviours) {
				behaviour.action();
			}
		} catch (Exception e) {
			logger.error(
					"There is no Exception handling defined if a Behaviour goes wrong...",e);
		}		
	}*/
	
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
	
	private void getEnergieConsumption() {
		actualEnergieNeed = 0.0;
		for (HardwareComponent hard : hardwarecomponents) {
			actualEnergieNeed += hard.getActualEnergie();
		}
		if (accu != null) {
			accu.use(actualEnergieNeed);
			//System.out.println("AktuelleKWh: " + accu.getActualKWh());
		}
		//Make Measurement hier
		this.measure.completeEnergie += actualEnergieNeed;
		this.measure.energieProTick.add(actualEnergieNeed);
		this.measure.completeTicks += 1;
		//energieTest += actualEnergieNeed;
	}
	
	private void calculateMaxMinEnergieConsumption () {
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
	
	/*public List<Goal> getGoals () {
		return this.goals;
	}*/
	
	public boolean hasActiveHardwareComponent (Components c) {
		for (HardwareComponent hard : hardwarecomponents) {
			if (hard.getComponents() == c && hard.isActive())
				return true;
		}
		return false;
	}
	
	public boolean hasHardwareComponent (Components c) {
		for (HardwareComponent hard : hardwarecomponents) {
			if (hard.getComponents() == c)
				return true;
		}
		return false;
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
		boolean result = this.goals.remove(goal);
		recalculateSupportedStates();
		return result;
	}

	public void addHardwareComponent(HardwareComponent component) {
		hardwarecomponents.add(component);
		if (component.getComponents() == Components.LOADSTATION)
			this.loadStation = true;
		//Min und Max Engergie aufgrund von neuer Hardwarecomponente bestimmen
		calculateMaxMinEnergieConsumption();
	}

	public Position getPosition() {
		return positionProvider.getPosition();
	}
	
	private void recalculateSupportedStates () {
		supportedStates.clear();
		for (Goal goal : goals) {
			for (State state : goal.getSupportedStates()) {
				supportedStates.add(state);
				//if (!supportedStates.contains(state))
				//	supportedStates.add(state);
			}
		}
		for (Behaviour behavior : behaviours) {
			for (State state : behavior.getSupportedStates()) {
				supportedStates.add(state);
				//if (!supportedStates.contains(state))
				//	supportedStates.add(state);
			}
		}
	}	

	public Collection<State> getSupportedStates() {
		return supportedStates;
	}

	public World getWorld() {
		return this.world;
	}		

	public void moveTowardsDestination() {	
		this.destinationContainer.moveTowardsDestination();
	}

	/**
	 * Set the Position of the robot
	 * @param position
	 */
	public void setPosition(Position position) {
		logger.debug("Set new position of " + this + " to " + position + ".");
		this.navigationController.setPosition(position);
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public List<RobotRole> getRoles () {
		return this.roles;
	}
	
	public List<RobotKnowledge> getKnowledge () {
		return this.knowledge;
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

	public cleaningrobots.Robot exportModel(ImportExportConfiguration config) {
		// TODO: Consider caching
		cleaningrobots.Robot result = null;

		try {
			cleaningrobots.Robot robot = CleaningrobotsFactory.eINSTANCE.createRobot();
			robot.setName(getName());
			if (config.world)
			{
				robot.setWorld(world.exportModel(config));
			}
			if (config.knowledge)
			{
				if (destinationContainer.getLastLoadDestination() != null)
					robot.setDestination(destinationContainer.getLastLoadDestination().exportModel());
				else
					robot.setDestination(null);
				for (HardwareComponent hc : getHardwarecomponents()) {
					robot.getComponents().add(hc.getName());
				}
				for (RobotKnowledge rk : knowledge) {
					robot.getRobotKnowledge().add(rk.exportModel());
				}
				//roles noch hinzufügen
				for (RobotRole rr : getRoles()) {
					if (rr instanceof MasterRole) {
						cleaningrobots.MasterRole master = CleaningrobotsFactory.eINSTANCE.createMasterRole();
						for (RobotRole fr : ((MasterRole)rr).getFollowers()) {
							master.getFollowerNames().add(fr.getRobotCore().getName());
						}
						robot.getRoles().add(master);
					} else {
						cleaningrobots.FollowerRole follower = CleaningrobotsFactory.eINSTANCE.createFollowerRole();
						follower.setMasterName(((FollowerRole)rr).master.getRobotCore().getName());
						robot.getRoles().add(follower);
					}
				}
			}
			if (config.knownstates)
			{
				for (State state : getSupportedStates()) {
					robot.getKnownStates().add(state.exportModel());
				}
			}
			
			result = robot;
		} catch (Exception e) {
			logger.error("An error occured while exporting the model", e);
		}

		return result;
	}
}

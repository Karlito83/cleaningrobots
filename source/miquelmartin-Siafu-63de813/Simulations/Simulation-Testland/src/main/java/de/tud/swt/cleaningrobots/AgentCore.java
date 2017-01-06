package de.tud.swt.cleaningrobots;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import cleaningrobots.CleaningrobotsFactory;
import de.tud.evaluation.ExchangeMeasurement;
import de.tud.swt.cleaningrobots.goals.Goal;
import de.tud.swt.cleaningrobots.hardware.Accu;
import de.tud.swt.cleaningrobots.hardware.ComponentTypes;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;
import de.tud.swt.cleaningrobots.measure.RobotMeasurement;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.model.World;
import de.tud.swt.cleaningrobots.roles.FollowerRole;
import de.tud.swt.cleaningrobots.roles.MasterRole;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;

/**
 * The core class of the robot which has all hardware components roles and the world he knows about.
 * 
 * @author Christopher Werner
 *
 */
public class AgentCore extends Agent {
	
	private Configuration configuration;
	private RobotMeasurement measure;

	private String name;
	private World world;
	private boolean shutDown;
	
	private List<AgentRole> roles;
	private List<AgentKnowledge> knowledge;
	private List<Goal> goals;
	private List<HardwareComponent> hardwarecomponents;
	private Set<State> supportedStates;
	
	private Accu accu;		
	private double maxEnergieNeed;
	private double minEnergieNeed;
	private double actualEnergieNeed;	
	
	private ISimulatorAdapter communicationAdapter;	
	private DestinationContainer destinationContainer;
		
	//say if it is a load station
	private boolean loadStation;
	//is the robot loading
	private boolean loading;
	
	public AgentCore(ISimulatorAdapter communicationAdapter, Accu accu, Configuration configuration) {
		this("Empty", communicationAdapter, accu, configuration);
	}

	public AgentCore(String name, ISimulatorAdapter communicationAdapter, Accu accu, Configuration configuration) {

		this.configuration = configuration;
		this.name = name;
		this.world = new World(this);
		this.measure = new RobotMeasurement(name);
		
		//if accu==null the you do not drive to Loadstation
		this.accu = accu;
		
		this.loadStation = false;
		this.loading = false;
		this.shutDown = false;
		
		this.roles = new ArrayList<AgentRole>();
		this.hardwarecomponents = new LinkedList<HardwareComponent>();
		this.goals = new LinkedList<Goal>();
		this.supportedStates = new HashSet<State>();
		this.knowledge = new LinkedList<AgentKnowledge>();
		
		this.communicationAdapter = communicationAdapter;
		this.destinationContainer = new DestinationContainer(this);		
	}
	
	public Configuration getConfiguration ()
	{
		return this.configuration;
	}
	
	public boolean isLoading ()
	{
		return this.loading;
	}
	
	public void setLoading (boolean loading)
	{
		this.loading = loading;
	}
	
	public boolean createAndInitializeRoleGoals ()
	{
		boolean result = true;
		//create the goals first
		for (AgentRole rr : roles)
		{
			result = result && rr.createGoals();
		}
		if (!result)
			return false;

		//initialize them after
		for (AgentRole rr : roles)
		{
			rr.initializeGoals();
		}
		
		return true;
	}
	
	/**
	 * Is the robot on or off.
	 * @return
	 */
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
	
	/**
	 * Makes the action of each robot in every iteration.
	 */
	public boolean action() {
				
		long startTime = System.nanoTime();
		
		try {
			runGoals();
			getEnergieConsumption();
		} catch (Exception e) {
		}
		
		long endTime = System.nanoTime();
		
		long time = endTime - startTime;
		measure.completeTime += time;
		measure.timeProTick.add((double) time);
		
		for (Goal goal : goals) {
			//if there are non optional goals he is not finished
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
			//if all goals finished then switch off hardware components
			if (goals.isEmpty())
			{
				System.out.println("Alle HardwareComponenten ausgeschaltet von " + this.getName());
				for (HardwareComponent hc : hardwarecomponents) {
					if (hc.isActive())
						hc.switchOff();
				}
				shutDown = true;
			}
			
		}
	}
	
	/**
	 * Get the minimal energy need of the robot.
	 * @return
	 */
	public double getMinEnergie() {
		return this.minEnergieNeed;
	}
	
	/**
	 * Get the maximal energy need of the robot.
	 * @return
	 */
	public double getMaxEnergie() {
		return this.maxEnergieNeed;
	}
	
	/**
	 * Get the actual energy need of the robot.
	 * @return
	 */
	public double getActualEnergie() {
		return this.actualEnergieNeed;
	}
	
	private void getEnergieConsumption() {
		actualEnergieNeed = 0.0;
		for (HardwareComponent hard : hardwarecomponents) {
			actualEnergieNeed += hard.getActualEnergy();
		}
		if (accu != null) {
			accu.use(actualEnergieNeed);
		}
		//do measurement
		this.measure.completeEnergie += actualEnergieNeed;
		this.measure.energieProTick.add(actualEnergieNeed);
		this.measure.completeTicks += 1;
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
	
	public ISimulatorAdapter getICommunicationAdapter () {
		return this.communicationAdapter;
	}
	
	public List<HardwareComponent> getHardwarecomponents ()	{
		return this.hardwarecomponents;
	}
	
	/**
	 * Proofs if he has a component c and if it is active.
	 * @param c
	 * @return
	 */
	public boolean hasActiveHardwareComponent (ComponentTypes c) {
		for (HardwareComponent hard : hardwarecomponents) {
			if (hard.getComponentType() == c && hard.isActive())
				return true;
		}
		return false;
	}
	
	/**
	 * Proofs if he has a component c.
	 * @param c
	 * @return
	 */
	public boolean hasHardwareComponent (ComponentTypes c) {
		for (HardwareComponent hard : hardwarecomponents) {
			if (hard.getComponentType() == c)
				return true;
		}
		return false;
	}
		
	public boolean addGoal (Goal goal) {
		boolean result = this.goals.add(goal);
		recalculateSupportedStates();
		return result;
	}
	
	/***
	 * Removes a {@link Goal} from the {@link List} of goals. This
	 * function behaves like {@link List#remove(int)}.
	 * 
	 * @param goal
	 * @return
	 */
	public boolean removeGoal (Goal goal) {
		boolean result = this.goals.remove(goal);
		recalculateSupportedStates();
		return result;
	}

	public void addHardwareComponent(HardwareComponent component) {
		hardwarecomponents.add(component);
		if (component.getComponentType() == ComponentTypes.LOADSTATION)
			this.loadStation = true;
		//recreate minimal and maximal energy because of new hardware component
		calculateMaxMinEnergieConsumption();
	}

	public Position getPosition() {
		return this.communicationAdapter.getPosition();
	}
	
	private void recalculateSupportedStates () {
		supportedStates.clear();
		for (Goal goal : goals) {
			for (State state : goal.getSupportedStates()) {
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

	public void setName(String name) {
		this.measure.setName(name);
		this.name = name;
	}

	public String getName() {
		return this.name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
	public List<AgentRole> getRoles () {
		return this.roles;
	}
	
	public List<AgentKnowledge> getKnowledge () {
		return this.knowledge;
	}
	
	//functions of abstract class robot
	@Override
	public boolean hasRole(AgentRole role) {
		return roles.contains(role);
	}

	@Override
	public boolean removeRole(AgentRole role) {
		return this.roles.remove(role);		
	}

	@Override
	public boolean addRole(AgentRole role) {
		return this.roles.add(role);	
	}
	
	@Override
	public boolean equals(Object obj) {
		boolean result = false;
		if(obj!=null && obj instanceof AgentCore){
			AgentCore tmp = (AgentCore)obj;
			result = tmp.getName().equals(this.getName());
		}
		return result;
	}
	
	public void addLastMeasurement () {
		ExchangeMeasurement em = new ExchangeMeasurement(name, "FinalModel", 0);
		em.addMeasurement(world.getHoleMeasurement());
		//name not use because he is in
		//accu
		em.addAccuDoubleNumber(3);
		//energy
		em.addAccuDoubleNumber(3);
		//knownstates
		em.addStatesStringNumber(supportedStates.size());
		for (State s : this.supportedStates) {
			em.addStatesStringByteNumber(s.getName().getBytes().length);
		}
		//destination container
		em.addKnowledgeIntegerNumber(6);
		//3 Positions + list Positions
		//knowledge
		for (AgentKnowledge rk : this.knowledge) {
			em.addMeasurement(rk.getMeasurement());
		}
		//roles
		for (AgentRole r : roles) {
			if (r instanceof MasterRole) {
				MasterRole m = (MasterRole)r;
				em.addKnowledgeStringNumber(m.getFollowers().size() + 1);
				em.addKnowledgeStringByteNumber(r.getClass().getName().getBytes().length);
				for (AgentRole rr : m.getFollowers()) {
					em.addKnowledgeStringByteNumber(rr.getRobotCore().getName().getBytes().length);
				}
			} else if (r instanceof FollowerRole) {
				FollowerRole f = (FollowerRole)r;
				em.addKnowledgeStringByteNumber(f.getMaster().getRobotCore().getName().getBytes().length);
				em.addKnowledgeStringByteNumber(r.getClass().getName().getBytes().length);
				em.addKnowledgeStringNumber(2);
			} else {
				em.addKnowledgeStringByteNumber(r.getClass().getName().getBytes().length);
				em.addKnowledgeStringNumber(1);
			}
		}
		configuration.getWc().exchange.add(em);
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
				for (AgentKnowledge rk : knowledge) {
					robot.getRobotKnowledge().add(rk.exportModel());
				}
				// add roles
				for (AgentRole rr : getRoles()) {
					if (rr instanceof MasterRole) {
						cleaningrobots.MasterRole master = CleaningrobotsFactory.eINSTANCE.createMasterRole();
						for (AgentRole fr : ((MasterRole)rr).getFollowers()) {
							master.getFollowerNames().add(fr.getRobotCore().getName());
						}
						robot.getRoles().add(master);
					} else {
						cleaningrobots.FollowerRole follower = CleaningrobotsFactory.eINSTANCE.createFollowerRole();
						follower.setMasterName(((FollowerRole)rr).getMaster().getRobotCore().getName());
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
		}

		return result;
	}
}

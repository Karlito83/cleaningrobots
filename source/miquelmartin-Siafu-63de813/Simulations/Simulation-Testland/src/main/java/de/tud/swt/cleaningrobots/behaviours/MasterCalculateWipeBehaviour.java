package de.tud.swt.cleaningrobots.behaviours;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.hardware.ComponentTypes;
import de.tud.swt.cleaningrobots.merge.PathDestinationMerge;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.roles.MasterRole;
import de.tud.swt.cleaningrobots.util.PathWayMergeInformation;
import de.tud.swt.cleaningrobots.util.RobotDestinationCalculation;

/**
 * Behavior for master which search new destinations for wiper.
 * Always in contact to follower and send the data directly.
 * 
 * @author Christopher Werner
 *
 */
public class MasterCalculateWipeBehaviour extends Behaviour {

	private MasterRole mr;
	
	private State STATE_HOOVE;
	private State STATE_WIPE;	
	private State WORLDSTATE_WIPED;	
	private State WORLDSTATE_HOOVED;
		
	private int calculationAway;
	private PathDestinationMerge mfm;
	
	private Map<String, RobotDestinationCalculation> information;
	
	private boolean relative;
	
	public MasterCalculateWipeBehaviour(RobotCore robot, MasterRole mr, boolean relative) {
		super(robot);	
		
		this.mr = mr;
		this.relative = relative;
		this.mfm = new PathDestinationMerge(this.robot.getConfiguration());
		this.information = new HashMap<String, RobotDestinationCalculation>();		
	}
	
	@Override
	protected void addSupportedStates() {
		//create and add the states
		this.STATE_HOOVE = robot.getConfiguration().createState("Hoove");
		this.STATE_WIPE = robot.getConfiguration().createState("Wipe");		
		this.WORLDSTATE_WIPED = robot.getConfiguration().createState("Wiped");	
		this.WORLDSTATE_HOOVED = robot.getConfiguration().createState("Hooved");

		this.supportedStates.add(this.STATE_HOOVE);
		this.supportedStates.add(this.STATE_WIPE);	
	}

	@Override
	protected void addHardwareComponents() {
		this.d.addDemandPair(ComponentTypes.WLAN, 1);
	}

	@Override
	public boolean action() throws Exception {
		//start all hardware components
		this.d.switchAllOn();
									
		//search all wipe robots
		List<RobotCore> allRobots = this.robot.getICommunicationAdapter().getAllRobots();
		allRobots.remove(this.robot);
										
		for (RobotDestinationCalculation rdc : information.values()) {
			//set all NeedNew to false
			rdc.needNew = false;
			//change new and old destination
			if (rdc.newDest != null)
			{
				//set newDest to null and set oldDest
				rdc.oldDest = rdc.newDest;
				rdc.newDest = null;
			}
		}
								
		//search robots which are loading and not have a newDest and set Variable			
		boolean newOneFind = false;
								
		for (RobotCore oneRobot : allRobots) {
			//run Values and search the same robot
			for (RobotDestinationCalculation rdc : information.values()) {
				if (oneRobot.getName().equals(rdc.getName())) 
				{
					if (oneRobot.hasNewInformation() && oneRobot.getDestinationContainer().isAtLoadDestination())
					{
						newOneFind = true;
						rdc.needNew = true;
						oneRobot.setNewInformation(false);
					}
				}
			}			
		}
						
		//if new one find then calculate new destination and set it
		if (newOneFind) {
			Map<String, RobotDestinationCalculation> result = this.robot.getWorld().getNextPassablePositionsByStateWithoutState(information, calculationAway, STATE_HOOVE, STATE_WIPE);
							
			if (result != null) {			
				information = result; 
								
				//send new information
				for (RobotCore oneRobot : allRobots) {
					for (RobotDestinationCalculation rdc : information.values()) {
						if (rdc.getName().equals(oneRobot.getName()) && rdc.needNew)
						{
							PathWayMergeInformation path = new PathWayMergeInformation(rdc.newDest, robot.getWorld().getPath(rdc.newDest));
							mfm.run(this.robot, oneRobot, path);
							rdc.actualPosition = rdc.newDest;
						}
					}
				}
			} else {
				for (RobotDestinationCalculation rdc : information.values()) {
					if (rdc.needNew)
						rdc.finish = true;
				}
			}
		}
						
		for (RobotCore oneRobot : allRobots) {
			//search the same robot
			for (RobotDestinationCalculation rdc : information.values()) {
				if (oneRobot.getName().equals(rdc.getName())) 
				{
					if (oneRobot.hasNewInformation())
					{						
						Position nextWipePosition; 
						//proof if you need relative or not relative position
						if (relative)
							nextWipePosition = this.robot.getWorld().getNextPassableRelativePositionByStateWithoutState(rdc.actualPosition, rdc.oldDest, STATE_HOOVE, STATE_WIPE); 
						else
							nextWipePosition = this.robot.getWorld().getNextPassablePositionByStateWithoutState(rdc.actualPosition, STATE_HOOVE, STATE_WIPE);
									
						if(nextWipePosition != null){								
							//if the robot has a Accu proof the destination
							if (oneRobot.getAccu() != null)
							{
								//distance robot to destination
								int sizeOne = robot.getWorld().getPathFromTo(rdc.actualPosition, nextWipePosition).size();
								//distance destination to load station
								int sizeThree = robot.getWorld().getPathFromTo(nextWipePosition, robot.getPosition()).size();
								int size = sizeOne + sizeThree;
								size +=2;
								//if Accu is to low
								if (size * oneRobot.getActualEnergie() > oneRobot.getAccu().getRestKWh())
								{
									//Robot must load before drive to the destination
									if (rdc.actualPosition.equals(robot.getPosition()))
									{
										System.out.println("Robot erreicht keine Unknownposition mehr obwohl diese noch existiert!");
										rdc.finish = true;
									} else {
										PathWayMergeInformation path = new PathWayMergeInformation(robot.getPosition(), robot.getWorld().getPathFromTo(rdc.actualPosition, robot.getPosition()));
										mfm.run(this.robot, oneRobot, path);
										rdc.actualPosition = robot.getPosition();
									}
								} else {
									//robot has enough Accu he can drive to destination
									PathWayMergeInformation path = new PathWayMergeInformation(nextWipePosition, robot.getWorld().getPathFromTo(rdc.actualPosition, nextWipePosition));
									mfm.run(this.robot, oneRobot, path);
									rdc.actualPosition = nextWipePosition;
								}
							} else {
								PathWayMergeInformation path = new PathWayMergeInformation(nextWipePosition, robot.getWorld().getPathFromTo(rdc.actualPosition, nextWipePosition));
								mfm.run(this.robot, oneRobot, path);
								rdc.actualPosition = nextWipePosition;
							}
						}
						else 
						{
							//proof if the world is complete hooved
							if (this.robot.getWorld().containsWorldState(WORLDSTATE_HOOVED)) {
								this.robot.getWorld().addWorldState(WORLDSTATE_WIPED);
								if(!rdc.actualPosition.equals(robot.getPosition()))
								{
									//arrived at load station
									PathWayMergeInformation path = new PathWayMergeInformation(robot.getPosition(), robot.getWorld().getPathFromTo(rdc.actualPosition, robot.getPosition()));
									mfm.run(this.robot, oneRobot, path);
									rdc.actualPosition = robot.getPosition();
								} else {
									rdc.finish = true;
								}
							}
						}						
						//calculate a new position for the robot
						oneRobot.setNewInformation(false);
					}
				}
			}			
		}				
		return false;
	}
	
	public boolean isFinishWiping () {
		for (RobotDestinationCalculation rdc : information.values()) {
			if (!rdc.finish)
				return false;
		}
		if (this.robot.getWorld().containsWorldState(WORLDSTATE_WIPED))
		{
			for (RobotCore core : this.robot.getICommunicationAdapter().getAllRobots())
				core.getWorld().addWorldState(WORLDSTATE_WIPED);
			return true;
		} else {
			System.out.println("Roboter brauchen größeren Accu können Welt nicht mehr erkunden!");
			/*try {
				Thread.sleep(40000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}*/
		}
		return false;
	}

	@Override
	public void initialiseBehaviour() {
		
		double maxAway = 0;
		//search near Wipe Robots
		List<RobotRole> follower = this.mr.getFollowers();
					
		for (RobotRole rr : follower) {
			RobotCore core = rr.getRobotCore();
			if (core.hasHardwareComponent(ComponentTypes.WLAN) && core.hasHardwareComponent(ComponentTypes.WIPER))
			{
				//add Robot to Map
				RobotDestinationCalculation rdc = new RobotDestinationCalculation(core.getName());
				rdc.actualPosition = core.getPosition();
				information.put(core.getName(), rdc);
						
				double away = Math.sqrt(core.getAccu().getMaxFieldGoes(core.getMinEnergie()));
						
				if (maxAway < away)
					maxAway = away;
			}
		}
						
		calculationAway = (int) maxAway;		
	}

}

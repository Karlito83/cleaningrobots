package de.tud.swt.cleaningrobots.behaviours;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Search next not wipe position and drive throw, if the Accu is to low drive to load station
 * and if there is no more not wipe position drive back to load station again
 *  
 * @author ChrissiMobil
 *
 */
public class WipeBehaviour extends Behaviour{

	private State STATE_HOOVE;
	private State STATE_WIPE;	
	private State WORLDSTATE_WIPED;
	private State WORLDSTATE_HOOVED;
	
	private boolean finishWiping;
	private boolean relative;

	public WipeBehaviour(RobotCore robot, boolean relative) {
		super(robot);
				
		this.relative = relative;
		this.finishWiping = false;	
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
		//no hardware components needed...
	}
	
	public boolean isFinishWipe () {
		return finishWiping;
	}

	@Override
	public boolean action() throws Exception {
											
		if(robot.getDestinationContainer().isAtDestination()){
			
			//if you find more than the value of new field drive back to load station and give information to master
			if (this.robot.getConfiguration().getWc().new_field_count > 0 && this.robot.getWorld().getNewInformationCounter() > this.robot.getConfiguration().getWc().new_field_count) {
				robot.getDestinationContainer().setDestinationLoadStation();
				this.robot.getWorld().resetNewInformationCounter();
				return false;
			}
			
			Position nextNotWipePosition;
			//Look if you must use relative or non relative algorithm 
			if (relative)
				nextNotWipePosition = this.robot.getWorld().getNextPassableRelativePositionByStateWithoutState(this.robot.getDestinationContainer().getLastLoadDestination(), STATE_HOOVE, STATE_WIPE);
			else
				nextNotWipePosition = this.robot.getWorld().getNextPassablePositionByStateWithoutState(STATE_HOOVE, STATE_WIPE);
						
			if(nextNotWipePosition != null){
				robot.getDestinationContainer().setDestination(nextNotWipePosition, false);
				
				//if there is a Accu proof if you can come to the next destination if not drive to load station
				if (robot.getAccu() != null)
				{
					if (robot.isLoading())
						return false;
					
					//distance between robot and destination
					int sizeOne = robot.getDestinationContainer().getPathFromTo(robot.getPosition(), nextNotWipePosition).size();
					//distance between robot and load station
					//int sizeTwo = robot.getPath(robot.getPosition(), robot.loadStationPosition).size();
					//distance between destination and load station
					int sizeThree = robot.getDestinationContainer().getPathFromTo(nextNotWipePosition, robot.getDestinationContainer().getLoadStationPosition()).size();
					int size = sizeOne + sizeThree;
					size +=2;
					//proof Accu
					if (size * robot.getActualEnergie() > robot.getAccu().getRestKWh())
					{
						//drive back to load station
						robot.getDestinationContainer().setDestinationLoadStation();
						//
						if (robot.getDestinationContainer().getLoadStationPosition().equals(robot.getPosition()))
						{
							//robot can not come to any destination should finish
							System.out.println("Robot erreicht keine Hooveposition mehr obwohl diese noch existiert!");
							finishWiping = true;
							return true;
						}
					}
				}
			} else {
				//no more wipe position found
				if (this.robot.getWorld().containsWorldState(WORLDSTATE_HOOVED))
				{
					if (!this.robot.getWorld().containsWorldState(WORLDSTATE_WIPED)) {
						this.robot.getWorld().addWorldState(WORLDSTATE_WIPED);
						//Activate flag that he has new information
						for (RobotRole rr : robot.getRoles()) {
							rr.setNewInformation(true);
						}
					}
					//finish back to load station
					if(!robot.getPosition().equals(robot.getDestinationContainer().getLoadStationPosition()))
					{
						//must drive back to load station
						robot.getDestinationContainer().setDestinationLoadStation();
					} else {
						//is at load station
						finishWiping = true;
						return true;
					}
				} else {
					//not finish wait for new data, drive back to master
					robot.getDestinationContainer().setDestinationLoadStation();
				}
			}
		}
		return false;		
	}

	@Override
	public void initialiseBehaviour() {
		//do nothing before first start		
	}

}

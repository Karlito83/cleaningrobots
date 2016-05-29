package de.tud.swt.cleaningrobots.behaviours;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Search next not hoove position and drive throw, if the Accu is to low drive to load station
 * and if there is no more not hoove position drive back to load station again
 *  
 * @author ChrissiMobil
 *
 */
public class HooveBehaviour extends Behaviour {
	
	private State STATE_HOOVE;
	private State STATE_FREE;	
	private State WORLDSTATE_DISCOVERED;
	private State WORLDSTATE_HOOVED;
	
	private boolean finishHooving;
	private boolean relative;

	public HooveBehaviour(RobotCore robot, boolean relative) {
		super(robot);
				
		this.relative = relative;
		this.finishHooving = false;		
	}
	
	@Override
	protected void addSupportedStates() {
		//create and add the states
		this.STATE_HOOVE = robot.configuration.createState("Hoove");
		this.STATE_FREE = robot.configuration.createState("Free");		
		this.WORLDSTATE_DISCOVERED = robot.configuration.createState("Discovered");
		this.WORLDSTATE_HOOVED = robot.configuration.createState("Hooved");
				
		this.supportedStates.add(this.STATE_HOOVE);
		this.supportedStates.add(this.STATE_FREE);		
	}

	@Override
	protected void addHardwareComponents() {
		//no hardware components needed...		
	}
	
	public boolean isFinishHoove () {
		return finishHooving;
	}

	@Override
	public boolean action() throws Exception {
											
		if(robot.getDestinationContainer().isAtDestination()){
			
			//if you find more than the value of new field drive back to load station and give information to master
			if (this.robot.configuration.wc.new_field_count > 0 && this.robot.getWorld().getNewInformationCounter() > this.robot.configuration.wc.new_field_count) {
				robot.getDestinationContainer().setDestinationLoadStation();
				this.robot.getWorld().resetNewInformationCounter();
				return false;
			}
			
			Position nextNotHoovePosition;
			//Look if you must use relative or non relative algorithm 
			if (relative)
				nextNotHoovePosition = this.robot.getWorld().getNextPassablePositionRelativeWithoutState(this.robot.getDestinationContainer().getLastLoadDestination(), STATE_HOOVE);
			else
				nextNotHoovePosition = this.robot.getWorld().getNextPassablePositionWithoutState(STATE_HOOVE); 
			
			if(nextNotHoovePosition != null){
				robot.getDestinationContainer().setDestination(nextNotHoovePosition, false);
				
				//if there is a Accu proof if you can come to the next destination if not drive to load station
				if (robot.getAccu() != null)
				{
					if (robot.isLoading)
						return false;
					
					//distance between robot and destination
					int sizeOne = robot.getDestinationContainer().getPathFromTo(robot.getPosition(), nextNotHoovePosition).size();
					//distance between robot and load station
					//int sizeTwo = robot.getPath(robot.getPosition(), robot.loadStationPosition).size();
					//distance between destination and load station
					int sizeThree = robot.getDestinationContainer().getPathFromTo(nextNotHoovePosition, robot.getDestinationContainer().getLoadStationPosition()).size();
					int size = sizeOne + sizeThree;
					size +=2;
					//proof Accu 
					if (size * robot.getActualEnergie() > robot.getAccu().getRestKWh())
					{
						//drive back to load station
						robot.getDestinationContainer().setDestinationLoadStation();
						if (robot.getDestinationContainer().getLoadStationPosition().equals(robot.getPosition()))
						{
							//robot can not come to any destination should finish
							System.out.println("Robot erreicht keine Hooveposition mehr obwohl diese noch existiert!");
							finishHooving = true;
							return true;
						}
					}
				}				
			} else {
				//no more hoove position found
				//need no blocked field and proof if the hole world is discovered
				if (this.robot.getWorld().containsWorldState(WORLDSTATE_DISCOVERED))
				{
					if (!this.robot.getWorld().containsWorldState(WORLDSTATE_HOOVED)) {
						this.robot.getWorld().addWorldState(WORLDSTATE_HOOVED);
						//Activate flag that he has new information
						for (RobotRole rr : robot.getRoles()) {
							rr.setNewInformation(true);
						}
					}
					//finish back to load station
					if(!robot.getPosition().equals(robot.getDestinationContainer().getLoadStationPosition()))
					{
						//must drive to load station for end
						robot.getDestinationContainer().setDestinationLoadStation();
					} else {
						//is at load station
						finishHooving = true;
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

}

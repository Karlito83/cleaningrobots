package de.tud.swt.cleaningrobots.behaviours;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Search next unknown position and drive throw, if the Accu is to low drive to load station
 * and if there is no more unknown position drive back to load station again
 *  
 * @author ChrissiMobil
 *
 */
public class DiscoverBehaviour extends Behaviour {
	
	private State STATE_BLOCKED;
	private State STATE_FREE;	
	private State WORLDSTATE_DISCOVERED;

	private boolean noMoreDiscovering;	
	private boolean relative;
	
	public DiscoverBehaviour(RobotCore robot, boolean relative) {
		super(robot);
				
		this.relative = relative;
		this.noMoreDiscovering = false;					
	}

	@Override
	protected void addSupportedStates() {
		//create and add the states
		this.STATE_BLOCKED = robot.getConfiguration().createState("Blocked");
		this.STATE_FREE = robot.getConfiguration().createState("Free");		
		this.WORLDSTATE_DISCOVERED = robot.getConfiguration().createState("Discovered");
				
		this.supportedStates.add(this.STATE_BLOCKED);
		this.supportedStates.add(this.STATE_FREE);		
	}

	@Override
	protected void addHardwareComponents() {
		//no hardware components needed...		
	}	

	public boolean isFinishDiscovering () {
		return noMoreDiscovering;
	}
	
	@Override
	public boolean action() throws Exception {
			
		if(robot.getDestinationContainer().isAtDestination()) {
			
			//if you find more than the value of new field drive back to load station and give information to master
			if (this.robot.getConfiguration().getWc().new_field_count > 0 && this.robot.getWorld().getNewInformationCounter() > this.robot.getConfiguration().getWc().new_field_count) {
				robot.getDestinationContainer().setDestinationLoadStation();
				this.robot.getWorld().resetNewInformationCounter();
				return false;
			}
			
			Position nextUnknownPosition;
			//Look if you must use relative or non relative algorithm 
			if (relative)
				nextUnknownPosition = this.robot.getWorld().getNextUnknownRelativeFieldPosition(this.robot.getDestinationContainer().getLastLoadDestination()); 
			else
				nextUnknownPosition = this.robot.getWorld().getNextUnknownFieldPosition(); 
			
			if(nextUnknownPosition != null){
				robot.getDestinationContainer().setDestination(nextUnknownPosition, false);
				
				//if there is a Accu proof if you can come to the next destination if not drive to load station
				if (robot.getAccu() != null)
				{
					if (robot.isLoading())
						return false;
					
					//distance between robot and destination
					int sizeOne = robot.getDestinationContainer().getPathFromTo(robot.getPosition(), nextUnknownPosition).size();
					//distance between robot and load station
					//int sizeTwo = robot.getPath(robot.getPosition(), robot.loadStationPosition).size();
					//distance between destination and load station
					int sizeThree = robot.getDestinationContainer().getPathFromTo(nextUnknownPosition, robot.getDestinationContainer().getLoadStationPosition()).size();
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
							System.out.println("Robot erreicht keine Unknownposition mehr obwohl diese noch existiert!");
							noMoreDiscovering = true;
							return true;
						}
					}
				}
			}
			else 
			{
				if (!this.robot.getWorld().containsWorldState(WORLDSTATE_DISCOVERED)) {
					this.robot.getWorld().addWorldState(WORLDSTATE_DISCOVERED);
					//Activate flag that he has new information
					for (RobotRole rr : robot.getRoles()) {
						rr.setNewInformation(true);
					}
				}
				if(!robot.getPosition().equals(robot.getDestinationContainer().getLoadStationPosition()))
				{					
					robot.getDestinationContainer().setDestinationLoadStation();
				} else {
					//return true if all is finish
					//no unknown Position and at load station
					noMoreDiscovering = true;
					return true;
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

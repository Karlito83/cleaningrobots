package de.tud.swt.cleaningrobots.behaviours;

import java.util.EnumMap;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.hardware.Components;
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
		
		//create and add the states
		this.STATE_BLOCKED = robot.configuration.createState("Blocked");
		this.STATE_FREE = robot.configuration.createState("Free");		
		this.WORLDSTATE_DISCOVERED = robot.configuration.createState("Discovered");
		
		supportedStates.add(STATE_BLOCKED);
		supportedStates.add(STATE_FREE);
		
		this.relative = relative;
		this.noMoreDiscovering = false;		
		
		//add the hardware components and proof there correctness
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		//do not add motor because of you only search destination you don't move
		//hardware.put(Components.MOTOR, 1);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();		
	}	

	public boolean isFinishDiscovering () {
		return noMoreDiscovering;
	}
	
	@Override
	public boolean action() throws Exception {
			
		if(robot.getDestinationContainer().isAtDestination()) {
			
			//if you find more than the value of new field drive back to load station and give information to master
			if (this.robot.configuration.wc.new_field_count > 0 && this.robot.getWorld().getNewInformationCounter() > this.robot.configuration.wc.new_field_count) {
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
				robot.getDestinationContainer().setDestination(nextUnknownPosition);
				
				//if there is a Accu proof if you can come to the next destination if not drive to load station
				if (robot.getAccu() != null)
				{
					if (robot.isLoading)
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
}

package de.tud.swt.cleaningrobots.behaviours;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.AgentRole;
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
	
	public DiscoverBehaviour(AgentRole role, boolean relative) {
		super(role);
				
		this.relative = relative;
		this.noMoreDiscovering = false;					
	}

	@Override
	protected void addSupportedStates() {
		//create and add the states
		this.STATE_BLOCKED = agentCore.getConfiguration().createState("Blocked");
		this.STATE_FREE = agentCore.getConfiguration().createState("Free");		
		this.WORLDSTATE_DISCOVERED = agentCore.getConfiguration().createState("Discovered");
				
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
			
		if(agentCore.getDestinationContainer().isAtDestination()) {
			
			//if you find more than the value of new field drive back to load station and give information to master
			if (this.agentCore.getConfiguration().getWc().new_field_count > 0 && this.agentCore.getWorld().getNewInformationCounter() > this.agentCore.getConfiguration().getWc().new_field_count) {
				agentCore.getDestinationContainer().setDestinationLoadStation();
				this.agentCore.getWorld().resetNewInformationCounter();
				return false;
			}
			
			Position nextUnknownPosition;
			//Look if you must use relative or non relative algorithm 
			if (relative)
				nextUnknownPosition = this.agentCore.getWorld().getNextUnknownRelativeFieldPosition(this.agentCore.getDestinationContainer().getLastLoadDestination()); 
			else
				nextUnknownPosition = this.agentCore.getWorld().getNextUnknownFieldPosition(); 
			
			if(nextUnknownPosition != null){
				agentCore.getDestinationContainer().setDestination(nextUnknownPosition, false);
				
				//if there is a Accu proof if you can come to the next destination if not drive to load station
				if (agentCore.getAccu() != null)
				{
					if (agentCore.isLoading())
						return false;
					
					//distance between robot and destination
					int sizeOne = agentCore.getDestinationContainer().getPathFromTo(agentCore.getPosition(), nextUnknownPosition).size();
					//distance between robot and load station
					//int sizeTwo = robot.getPath(robot.getPosition(), robot.loadStationPosition).size();
					//distance between destination and load station
					int sizeThree = agentCore.getDestinationContainer().getPathFromTo(nextUnknownPosition, agentCore.getDestinationContainer().getLoadStationPosition()).size();
					int size = sizeOne + sizeThree;
					size +=2;
					//proof Accu 
					if (size * agentCore.getActualEnergie() > agentCore.getAccu().getRestKWh())
					{
						//drive back to load station
						agentCore.getDestinationContainer().setDestinationLoadStation();
						if (agentCore.getDestinationContainer().getLoadStationPosition().equals(agentCore.getPosition()))
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
				if (!this.agentCore.getWorld().containsWorldState(WORLDSTATE_DISCOVERED)) 
				{
					this.agentCore.getWorld().addWorldState(WORLDSTATE_DISCOVERED);
					//Activate flag that he has new information
					agentRole.setNewInformation(true);
				}
				if(!agentCore.getPosition().equals(agentCore.getDestinationContainer().getLoadStationPosition()))
				{					
					agentCore.getDestinationContainer().setDestinationLoadStation();
				} 
				else 
				{
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

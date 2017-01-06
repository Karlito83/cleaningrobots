package de.tud.swt.cleaningrobots.behaviours;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.AgentRole;
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

	public WipeBehaviour(AgentRole role, boolean relative) {
		super(role);
				
		this.relative = relative;
		this.finishWiping = false;	
	}	
	
	@Override
	protected void addSupportedStates() {
		//create and add the states
		this.STATE_HOOVE = agentCore.getConfiguration().createState("Hoove");
		this.STATE_WIPE = agentCore.getConfiguration().createState("Wipe");		
		this.WORLDSTATE_WIPED = agentCore.getConfiguration().createState("Wiped");
		this.WORLDSTATE_HOOVED = agentCore.getConfiguration().createState("Hooved");
				
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
											
		if(agentCore.getDestinationContainer().isAtDestination()){
			
			//if you find more than the value of new field drive back to load station and give information to master
			if (this.agentCore.getConfiguration().getWc().new_field_count > 0 && this.agentCore.getWorld().getNewInformationCounter() > this.agentCore.getConfiguration().getWc().new_field_count) {
				agentCore.getDestinationContainer().setDestinationLoadStation();
				this.agentCore.getWorld().resetNewInformationCounter();
				return false;
			}
			
			Position nextNotWipePosition;
			//Look if you must use relative or non relative algorithm 
			if (relative)
				nextNotWipePosition = this.agentCore.getWorld().getNextPassableRelativePositionByStateWithoutState(this.agentCore.getDestinationContainer().getLastLoadDestination(), STATE_HOOVE, STATE_WIPE);
			else
				nextNotWipePosition = this.agentCore.getWorld().getNextPassablePositionByStateWithoutState(STATE_HOOVE, STATE_WIPE);
						
			if(nextNotWipePosition != null){
				agentCore.getDestinationContainer().setDestination(nextNotWipePosition, false);
				
				//if there is a Accu proof if you can come to the next destination if not drive to load station
				if (agentCore.getAccu() != null)
				{
					if (agentCore.isLoading())
						return false;
					
					//distance between robot and destination
					int sizeOne = agentCore.getDestinationContainer().getPathFromTo(agentCore.getPosition(), nextNotWipePosition).size();
					//distance between robot and load station
					//int sizeTwo = robot.getPath(robot.getPosition(), robot.loadStationPosition).size();
					//distance between destination and load station
					int sizeThree = agentCore.getDestinationContainer().getPathFromTo(nextNotWipePosition, agentCore.getDestinationContainer().getLoadStationPosition()).size();
					int size = sizeOne + sizeThree;
					size +=2;
					//proof Accu
					if (size * agentCore.getActualEnergie() > agentCore.getAccu().getRestKWh())
					{
						//drive back to load station
						agentCore.getDestinationContainer().setDestinationLoadStation();
						//
						if (agentCore.getDestinationContainer().getLoadStationPosition().equals(agentCore.getPosition()))
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
				if (this.agentCore.getWorld().containsWorldState(WORLDSTATE_HOOVED))
				{
					if (!this.agentCore.getWorld().containsWorldState(WORLDSTATE_WIPED)) 
					{
						this.agentCore.getWorld().addWorldState(WORLDSTATE_WIPED);
						//Activate flag that he has new information
						agentRole.setNewInformation(true);
					}
					//finish back to load station
					if(!agentCore.getPosition().equals(agentCore.getDestinationContainer().getLoadStationPosition()))
					{
						//must drive back to load station
						agentCore.getDestinationContainer().setDestinationLoadStation();
					} 
					else 
					{
						//is at load station
						finishWiping = true;
						return true;
					}
				} 
				else 
				{
					//not finish wait for new data, drive back to master
					agentCore.getDestinationContainer().setDestinationLoadStation();
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

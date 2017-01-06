package de.tud.swt.cleaningrobots.behaviours;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.AgentRole;
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

	public HooveBehaviour(AgentRole role, boolean relative) {
		super(role);
				
		this.relative = relative;
		this.finishHooving = false;		
	}
	
	@Override
	protected void addSupportedStates() {
		//create and add the states
		this.STATE_HOOVE = agentCore.getConfiguration().createState("Hoove");
		this.STATE_FREE = agentCore.getConfiguration().createState("Free");		
		this.WORLDSTATE_DISCOVERED = agentCore.getConfiguration().createState("Discovered");
		this.WORLDSTATE_HOOVED = agentCore.getConfiguration().createState("Hooved");
				
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
											
		if(agentCore.getDestinationContainer().isAtDestination()){
			
			//if you find more than the value of new field drive back to load station and give information to master
			if (this.agentCore.getConfiguration().getWc().new_field_count > 0 && this.agentCore.getWorld().getNewInformationCounter() > this.agentCore.getConfiguration().getWc().new_field_count) {
				agentCore.getDestinationContainer().setDestinationLoadStation();
				this.agentCore.getWorld().resetNewInformationCounter();
				return false;
			}
			
			Position nextNotHoovePosition;
			//Look if you must use relative or non relative algorithm 
			if (relative)
				nextNotHoovePosition = this.agentCore.getWorld().getNextPassablePositionRelativeWithoutState(this.agentCore.getDestinationContainer().getLastLoadDestination(), STATE_HOOVE);
			else
				nextNotHoovePosition = this.agentCore.getWorld().getNextPassablePositionWithoutState(STATE_HOOVE); 
			
			if(nextNotHoovePosition != null){
				agentCore.getDestinationContainer().setDestination(nextNotHoovePosition, false);
				
				//if there is a Accu proof if you can come to the next destination if not drive to load station
				if (agentCore.getAccu() != null)
				{
					if (agentCore.isLoading())
						return false;
					
					//distance between robot and destination
					int sizeOne = agentCore.getDestinationContainer().getPathFromTo(agentCore.getPosition(), nextNotHoovePosition).size();
					//distance between robot and load station
					//int sizeTwo = robot.getPath(robot.getPosition(), robot.loadStationPosition).size();
					//distance between destination and load station
					int sizeThree = agentCore.getDestinationContainer().getPathFromTo(nextNotHoovePosition, agentCore.getDestinationContainer().getLoadStationPosition()).size();
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
							System.out.println("Robot erreicht keine Hooveposition mehr obwohl diese noch existiert!");
							finishHooving = true;
							return true;
						}
					}
				}				
			} else {
				//no more hoove position found
				//need no blocked field and proof if the hole world is discovered
				if (this.agentCore.getWorld().containsWorldState(WORLDSTATE_DISCOVERED))
				{
					if (!this.agentCore.getWorld().containsWorldState(WORLDSTATE_HOOVED)) 
					{
						this.agentCore.getWorld().addWorldState(WORLDSTATE_HOOVED);
						//Activate flag that he has new information
						agentRole.setNewInformation(true);
					}
					//finish back to load station
					if(!agentCore.getPosition().equals(agentCore.getDestinationContainer().getLoadStationPosition()))
					{
						//must drive to load station for end
						agentCore.getDestinationContainer().setDestinationLoadStation();
					} 
					else 
					{
						//is at load station
						finishHooving = true;
						return true;
					}
				} else 
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

package de.tud.swt.cleaningrobots.behaviours;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.hardware.ComponentTypes;

/**
 * Behavior that realize the move of an robot to a destination. 
 * Every time it drives one step to the new destination. 
 * 
 * @author Christopher Werner
 *
 */
public class MoveBehaviour extends Behaviour {
		
	public MoveBehaviour(AgentRole role) {
		super(role);
	}
	
	@Override
	protected void addSupportedStates() {
		//no states needed...		
	}

	@Override
	protected void addHardwareComponents() {
		this.demand.addDemandPair(ComponentTypes.ACTUATOR, 1);
	}

	@Override
	public boolean action() throws Exception {
		
		//if he is not at the destination and not loading he should move
		if (!agentCore.getDestinationContainer().isAtDestination() && !agentCore.isLoading()){
			//start all hardware components
			this.demand.switchAllOn();
			
			//make the move
			agentCore.getDestinationContainer().moveTowardsDestination(false);
		} else {
			//switch off all hardware components
			this.demand.switchAllOff();
		}		
		return false;
	}

	@Override
	public void initialiseBehaviour() {
		//do nothing before first start		
	}
}

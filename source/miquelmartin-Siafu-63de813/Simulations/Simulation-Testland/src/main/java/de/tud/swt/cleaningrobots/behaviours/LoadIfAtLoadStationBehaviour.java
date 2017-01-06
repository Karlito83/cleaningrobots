package de.tud.swt.cleaningrobots.behaviours;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.AgentRole;

/**
 * Set loading if the robot arrived at load station.
 * 
 * @author Christopher Werner
 *
 */
public class LoadIfAtLoadStationBehaviour extends Behaviour {
	
	public LoadIfAtLoadStationBehaviour(AgentRole role) {
		super(role);		
	}
	
	@Override
	protected void addSupportedStates() {
		//no states needed...		
	}

	@Override
	protected void addHardwareComponents() {
		//no hardware components needed...		
	}

	@Override
	public boolean action() throws Exception {

		//if at load destination activate loading
		if (agentCore.getDestinationContainer().isAtLoadDestination())
		{
			agentCore.setLoading(true);
		}		
		return false;
	}

	@Override
	public void initialiseBehaviour() {
		//do nothing before first start		
	}
}

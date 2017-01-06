package de.tud.swt.cleaningrobots.behaviours;

import java.util.List;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.hardware.ComponentTypes;
import de.tud.swt.cleaningrobots.hardware.LoadStation;

/**
 * Old behavior.
 * Load the near robots if there Accu is to low and stop if it is full.
 * 
 * @author Christopher Werner
 *
 */
public class LoadBehaviour extends Behaviour {

	private LoadStation loadStation;
	
	public LoadBehaviour(AgentRole role) {
		super(role);

		this.loadStation = (LoadStation) this.demand.getHardwareComponent(ComponentTypes.LOADSTATION);
	}
	
	@Override
	protected void addSupportedStates() {
		//no states needed...		
	}

	@Override
	protected void addHardwareComponents() {
		this.demand.addDemandPair(ComponentTypes.LOADSTATION, 1);		
	}

	@Override
	public boolean action() throws Exception {

		List<AgentCore> nearRobots = this.agentCore.getICommunicationAdapter().getNearRobots(loadStation.getMeasurementRange());
		nearRobots.remove(this.agentCore);
		for (AgentCore nearRobot : nearRobots) {
			if (nearRobot.getAccu() != null)
			{
				if (nearRobot.isLoading())
					nearRobot.getAccu().load(loadStation.getLoadValue());
				//if the Accu is less than half -> load
				if (!nearRobot.isLoading() && nearRobot.getAccu().getActualKWh() < nearRobot.getAccu().getMaxKWh()/2)
					nearRobot.setLoading(true);
				//if the Accu is full -> stop loading
				if (nearRobot.getAccu().isFull()) {
					System.out.println("Accu Full");
					nearRobot.setLoading(false);
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

package de.tud.swt.cleaningrobots.behaviours;

import java.util.List;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.hardware.ComponentTypes;
import de.tud.swt.cleaningrobots.hardware.LoadStation;

/**
 * Load the near robots if they set loading.
 * 
 * @author Christopher Werner
 *
 */
public class LoadIfRobotWantBehaviour extends Behaviour {

	private LoadStation loadStation;
	
	public LoadIfRobotWantBehaviour(RobotRole role) {
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
		
		List<RobotCore> nearRobots = this.agentCore.getICommunicationAdapter().getNearRobots(loadStation.getMeasurementRange());
		nearRobots.remove(this.agentCore);
		for (RobotCore nearRobot : nearRobots) {
			if (nearRobot.getAccu() != null)
			{
				if (nearRobot.isLoading()) {
					nearRobot.getAccu().load(loadStation.getLoadValue());
				}
				//when the Accu is full do not load anymore
				if (nearRobot.getAccu().isFull()) {
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

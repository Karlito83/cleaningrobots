package de.tud.swt.cleaningrobots.behaviours;

import java.util.List;
import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.RobotCore;
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
	
	public LoadIfRobotWantBehaviour(RobotCore robot) {
		super(robot);

		this.loadStation = (LoadStation) this.d.getHardwareComponent(ComponentTypes.LOADSTATION);
	}
	
	@Override
	protected void addSupportedStates() {
		//no states needed...		
	}

	@Override
	protected void addHardwareComponents() {
		this.d.addDemandPair(ComponentTypes.LOADSTATION, 1);		
	}

	@Override
	public boolean action() throws Exception {
		
		List<RobotCore> nearRobots = this.robot.getICommunicationAdapter().getNearRobots(loadStation.getLoadRadius());
		nearRobots.remove(this.robot);
		for (RobotCore nearRobot : nearRobots) {
			if (nearRobot.getAccu() != null)
			{
				if (nearRobot.isLoading) {
					nearRobot.getAccu().load(loadStation.getLoadValue());
				}
				//when the Accu is full do not load anymore
				if (nearRobot.getAccu().isFull()) {
					nearRobot.isLoading = false;
				}
			}
		}
		
		return false;
	}
}

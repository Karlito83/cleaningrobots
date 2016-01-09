package de.tud.swt.cleaningrobots.behaviours;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Demand;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;
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

		//add components
		Map<Components, Integer> hardware = new EnumMap<Components, Integer> (Components.class);
		hardware.put(Components.LOADSTATION, 1);
		
		d = new Demand(hardware, robot);
		hardwarecorrect = d.isCorrect();
		
		//get vision Radius from load station component
		for (HardwareComponent hard : d.getHcs())
		{
			if (hard.getComponents() == Components.LOADSTATION)
			{
				loadStation = (LoadStation)hard;
			}
		}
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

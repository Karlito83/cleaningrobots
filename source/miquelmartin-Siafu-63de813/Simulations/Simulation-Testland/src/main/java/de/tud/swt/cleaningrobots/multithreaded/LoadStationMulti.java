package de.tud.swt.cleaningrobots.multithreaded;

import de.nec.nle.siafu.model.MultiWorld;
import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.goals.optional.LoadIfRobotWantGoal;
import de.tud.swt.cleaningrobots.hardware.LoadStation;
import de.tud.swt.cleaningrobots.model.Position;

/**
 * Used without user interface in Siafu.
 * Create a load station with its hardware components without pc component and can add specific goals to him.
 * 
 * @author Christopher Werner
 *
 */
public class LoadStationMulti extends RobotAgentMulti {

	public LoadStationMulti (Position start, MultiWorld world, Configuration configuration) {
		super(start, world);

		cleaningRobot = new RobotCore(this, null, configuration);
		
		cleaningRobot.addHardwareComponent(new LoadStation());
	}
		
	/**
	 * Add the goal to load robots.
	 */
	public void addLoadIfRobotWantGoal () {		
		LoadIfRobotWantGoal lirwg = new LoadIfRobotWantGoal(cleaningRobot);		
		if (lirwg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(lirwg);
		}
	}	
}

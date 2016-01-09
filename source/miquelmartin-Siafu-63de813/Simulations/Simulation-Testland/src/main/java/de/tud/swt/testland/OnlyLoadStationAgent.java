package de.tud.swt.testland;

import de.nec.nle.siafu.model.Position;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.Configuration;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.goals.optional.LoadIfRobotWantGoal;
import de.tud.swt.cleaningrobots.hardware.LoadStation;

/**
 * Used with user interface in Siafu.
 * Create a load station with its hardware components without pc component and can add specific goals to him.
 * 
 * @author Christopher Werner
 *
 */
public class OnlyLoadStationAgent extends RobotAgent {

	public OnlyLoadStationAgent(Position start, String image, World world, Configuration configuration) {
		super(start, image, world);
		
		siafuWorld = world;
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

package de.tud.swt.testland;

import de.nec.nle.siafu.model.Position;
import de.nec.nle.siafu.model.World;
import de.tud.evaluation.WorkingConfiguration;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.goals.optional.LoadIfRobotWantGoal;
import de.tud.swt.cleaningrobots.hardware.LoadStation;

public class OnlyLoadStationAgent extends RobotAgent {

	public OnlyLoadStationAgent(Position start, String image, World world, WorkingConfiguration configuration) {
		super(start, image, world);
		
		siafuWorld = world;
		cleaningRobot = new RobotCore(this, null, configuration);
		
		cleaningRobot.addHardwareComponent(new LoadStation());
	}
	
	//Load and dump goals
	public void addLoadIfRobotWantGoal () {		
		LoadIfRobotWantGoal lirwg = new LoadIfRobotWantGoal(cleaningRobot);		
		if (lirwg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(lirwg);
		}
	}
}

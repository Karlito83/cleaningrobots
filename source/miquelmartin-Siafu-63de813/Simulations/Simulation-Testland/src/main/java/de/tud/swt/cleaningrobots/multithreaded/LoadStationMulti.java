package de.tud.swt.cleaningrobots.multithreaded;

import de.nec.nle.siafu.model.MultiWorld;
import de.tud.evaluation.WorkingConfiguration;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.goals.optional.LoadIfRobotWantGoal;
import de.tud.swt.cleaningrobots.hardware.LoadStation;
import de.tud.swt.cleaningrobots.model.Position;

public class LoadStationMulti extends RobotAgentMulti {

	public LoadStationMulti (Position start, MultiWorld world, WorkingConfiguration configuration) {
		super(start, world);

		cleaningRobot = new RobotCore(this, null, configuration);
		
		cleaningRobot.addHardwareComponent(new LoadStation());
	}
		
	//Load goal
	public void addLoadIfRobotWantGoal () {		
		LoadIfRobotWantGoal lirwg = new LoadIfRobotWantGoal(cleaningRobot);		
		if (lirwg.isHardwareCorrect())
		{
			cleaningRobot.addGoal(lirwg);
		}
	}	
}

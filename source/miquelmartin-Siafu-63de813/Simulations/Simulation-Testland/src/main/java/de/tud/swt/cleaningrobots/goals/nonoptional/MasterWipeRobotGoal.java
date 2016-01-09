package de.tud.swt.cleaningrobots.goals.nonoptional;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.MasterMoveBehaviour;
import de.tud.swt.cleaningrobots.behaviours.MasterWipeAroundBehaviour;
import de.tud.swt.cleaningrobots.behaviours.WlanOnBehaviour;
import de.tud.swt.cleaningrobots.goals.NonOptionalGoal;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Non optional goal for a follower which get information from a master.
 * Drive to a place wipe it and give the information back to the master. 
 * 
 * @author Christopher Werner
 *
 */
public class MasterWipeRobotGoal extends NonOptionalGoal {

	private State WORLDSTATE_WIPED;
	
	public MasterWipeRobotGoal(RobotCore robot) {
		super(robot);
		
		this.WORLDSTATE_WIPED = robot.configuration.createState("Wiped");
		
		WlanOnBehaviour w = new WlanOnBehaviour(robot);
		System.out.println("Correct SeeAround: " + w.isHardwarecorrect());
		if (w.isHardwarecorrect()) {
			behaviours.add(w);
		} else {
			correct = false;
		}
		
		MasterMoveBehaviour m = new MasterMoveBehaviour(robot);
		System.out.println("Correct Move: " + m.isHardwarecorrect());
		if (m.isHardwarecorrect()) {
			behaviours.add(m);
		} else {
			correct = false;
		}
		
		MasterWipeAroundBehaviour s = new MasterWipeAroundBehaviour(robot);
		System.out.println("Correct Discover: " + s.isHardwarecorrect());
		if (s.isHardwarecorrect()) {
			behaviours.add(s);
		} else {
			correct = false;
		}
	}

	@Override
	public boolean preCondition() {
		if (getRobotCore().getWorld().containsWorldState(WORLDSTATE_WIPED))
			return false;
		return true;
	}

	@Override
	public boolean postCondition() {
		if (getRobotCore().getPosition().equals(getRobotCore().getDestinationContainer().getLoadStationPosition()) && 
				getRobotCore().getWorld().containsWorldState(WORLDSTATE_WIPED))
			return true;
		return false;
	}
}

package de.tud.swt.cleaningrobots.goals.nonoptional;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.LoadIfAtLoadStationBehaviour;
import de.tud.swt.cleaningrobots.behaviours.MergeAllOfNearWithoutModel;
import de.tud.swt.cleaningrobots.behaviours.MoveBehaviour;
import de.tud.swt.cleaningrobots.behaviours.WipeAroundAtDestinationBehaviour;
import de.tud.swt.cleaningrobots.behaviours.WipeBehaviour;
import de.tud.swt.cleaningrobots.goals.NonOptionalGoal;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Non optional goal to wipe the world without any control of a master. 
 * 
 * @author Christopher Werner
 *
 */
public class WithoutMasterWipeGoal extends NonOptionalGoal {

	private WipeBehaviour d;
	private State WORLDSTATE_WIPED;
	
	public WithoutMasterWipeGoal(RobotCore robot) {
		super(robot);
		
		this.WORLDSTATE_WIPED = robot.configuration.createState("Wiped");
		
		WipeAroundAtDestinationBehaviour s = new WipeAroundAtDestinationBehaviour(robot);
		System.out.println("Correct SeeAround: " + s.isHardwarecorrect());
		if (s.isHardwarecorrect()) {
			behaviours.add(s);
		} else {
			correct = false;
		}
		
		d = new WipeBehaviour(robot, false);
		System.out.println("Correct Discover: " + d.isHardwarecorrect());
		if (d.isHardwarecorrect()) {
			behaviours.add(d);
		} else {
			correct = false;
		}
		
		MoveBehaviour m = new MoveBehaviour(robot);
		System.out.println("Correct Move: " + m.isHardwarecorrect());
		if (m.isHardwarecorrect()) {
			behaviours.add(m);
		} else {
			correct = false;
		}
		
		MergeAllOfNearWithoutModel mar = new MergeAllOfNearWithoutModel(robot);
		System.out.println("Correct MergeRonny: " + mar.isHardwarecorrect());
		if (mar.isHardwarecorrect()) {
			behaviours.add(mar);
		} else {
			correct = false;
		}
		
		LoadIfAtLoadStationBehaviour lialsb = new LoadIfAtLoadStationBehaviour(robot);
		System.out.println("Correct LoadIfAtLoadStation: " + lialsb.isHardwarecorrect());
		if (lialsb.isHardwarecorrect()) {
			behaviours.add(lialsb);
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
		//must look if the wipe behavior is finish
		return d.isFinishWipe();
	}
}

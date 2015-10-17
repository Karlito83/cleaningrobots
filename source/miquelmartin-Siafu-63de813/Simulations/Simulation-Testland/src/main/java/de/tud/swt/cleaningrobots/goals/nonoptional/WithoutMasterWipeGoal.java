package de.tud.swt.cleaningrobots.goals.nonoptional;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.LoadIfAtLoadStationBehaviour;
import de.tud.swt.cleaningrobots.behaviours.MergeAllOfNearBehaviour;
import de.tud.swt.cleaningrobots.behaviours.MoveBehaviour;
import de.tud.swt.cleaningrobots.behaviours.WipeAroundAtDestinationBehaviour;
import de.tud.swt.cleaningrobots.behaviours.WipeBehaviour;
import de.tud.swt.cleaningrobots.goals.NonOptionalGoal;
import de.tud.swt.cleaningrobots.model.State;

public class WithoutMasterWipeGoal extends NonOptionalGoal {

	private WipeBehaviour d;
	private final State WORLDSTATE_WIPED = State.createState("Wiped");
	
	public WithoutMasterWipeGoal(RobotCore robot) {
		super(robot);
		
		WipeAroundAtDestinationBehaviour s = new WipeAroundAtDestinationBehaviour(robot);
		System.out.println("Correct SeeAround: " + s.isHardwarecorrect());
		if (s.isHardwarecorrect()) {
			//robot.addBehaviour(s);
			behaviours.add(s);
		} else {
			correct = false;
		}
		
		d = new WipeBehaviour(robot, false);
		System.out.println("Correct Discover: " + d.isHardwarecorrect());
		if (d.isHardwarecorrect()) {
			//robot.addBehaviour(d);
			behaviours.add(d);
		} else {
			correct = false;
		}
		
		MoveBehaviour m = new MoveBehaviour(robot);
		System.out.println("Correct Move: " + m.isHardwarecorrect());
		if (m.isHardwarecorrect()) {
			//robot.addBehaviour(m);
			behaviours.add(m);
		} else {
			correct = false;
		}
		
		MergeAllOfNearBehaviour mar = new MergeAllOfNearBehaviour(robot);
		System.out.println("Correct MergeRonny: " + mar.isHardwarecorrect());
		if (mar.isHardwarecorrect()) {
			behaviours.add(mar);
		} else {
			correct = false;
		}
		
		LoadIfAtLoadStationBehaviour lialsb = new LoadIfAtLoadStationBehaviour(robot);
		System.out.println("Correct LoadIfAtLoadStation: " + lialsb.isHardwarecorrect());
		if (lialsb.isHardwarecorrect()) {
			//robot.addBehaviour(b);
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
		//muss nur schauen ob discover behaviour fertig ist
		return d.isFinishWipe();
	}
}
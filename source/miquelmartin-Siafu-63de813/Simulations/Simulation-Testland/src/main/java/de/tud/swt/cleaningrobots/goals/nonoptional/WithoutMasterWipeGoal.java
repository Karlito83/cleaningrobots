package de.tud.swt.cleaningrobots.goals.nonoptional;

import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.behaviours.LoadIfAtLoadStationBehaviour;
import de.tud.swt.cleaningrobots.behaviours.MoveBehaviour;
import de.tud.swt.cleaningrobots.behaviours.WipeAroundAtDestinationBehaviour;
import de.tud.swt.cleaningrobots.behaviours.WipeBehaviour;
import de.tud.swt.cleaningrobots.behaviours.merge.MergeAllNearBehaviour;
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
	
	public WithoutMasterWipeGoal(AgentRole role) {
		super(role);
		
		this.WORLDSTATE_WIPED = getAgentCore().getConfiguration().createState("Wiped");
		
		WipeAroundAtDestinationBehaviour s = new WipeAroundAtDestinationBehaviour(role);
		System.out.println("Correct SeeAround: " + s.isHardwarecorrect());
		if (s.isHardwarecorrect()) {
			behaviours.add(s);
		} else {
			correct = false;
		}
		
		d = new WipeBehaviour(role, false);
		System.out.println("Correct Discover: " + d.isHardwarecorrect());
		if (d.isHardwarecorrect()) {
			behaviours.add(d);
		} else {
			correct = false;
		}
		
		MoveBehaviour m = new MoveBehaviour(role);
		System.out.println("Correct Move: " + m.isHardwarecorrect());
		if (m.isHardwarecorrect()) {
			behaviours.add(m);
		} else {
			correct = false;
		}
		
		MergeAllNearBehaviour mar = new MergeAllNearBehaviour(role, false);
		System.out.println("Correct MergeRonny: " + mar.isHardwarecorrect());
		if (mar.isHardwarecorrect()) {
			behaviours.add(mar);
		} else {
			correct = false;
		}
		
		LoadIfAtLoadStationBehaviour lialsb = new LoadIfAtLoadStationBehaviour(role);
		System.out.println("Correct LoadIfAtLoadStation: " + lialsb.isHardwarecorrect());
		if (lialsb.isHardwarecorrect()) {
			behaviours.add(lialsb);
		} else {
			correct = false;
		}
	}

	@Override
	public boolean preCondition() {		
		if (getAgentCore().getWorld().containsWorldState(WORLDSTATE_WIPED))
			return false;
		return true;
	}

	@Override
	public boolean postCondition() {
		//must look if the wipe behavior is finish
		return d.isFinishWipe();
	}
}

package de.tud.swt.cleaningrobots.goals.nonoptional;

import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.behaviours.HooveAroundAtDestinationBehaviour;
import de.tud.swt.cleaningrobots.behaviours.HooveBehaviour;
import de.tud.swt.cleaningrobots.behaviours.LoadIfAtLoadStationBehaviour;
import de.tud.swt.cleaningrobots.behaviours.MoveBehaviour;
import de.tud.swt.cleaningrobots.behaviours.merge.MergeAllNearBehaviour;
import de.tud.swt.cleaningrobots.goals.NonOptionalGoal;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Non optional goal to hoove the world without any control of a master. 
 * 
 * @author Christopher Werner
 *
 */
public class WithoutMasterHooveGoal extends NonOptionalGoal {

	private HooveBehaviour d;
	private State WORLDSTATE_HOOVED;
	
	public WithoutMasterHooveGoal(AgentRole role) {
		super(role);
		
		this.WORLDSTATE_HOOVED = getAgentCore().getConfiguration().createState("Hooved");
		
		HooveAroundAtDestinationBehaviour s = new HooveAroundAtDestinationBehaviour(role);
		System.out.println("Correct SeeAround: " + s.isHardwarecorrect());
		if (s.isHardwarecorrect()) {
			behaviours.add(s);
		} else {
			correct = false;
		}
		
		d = new HooveBehaviour(role, false);
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
		if (getAgentCore().getWorld().containsWorldState(WORLDSTATE_HOOVED))
			return false;
		return true;
	}

	@Override
	public boolean postCondition() {
		//must look if hoove behavior is finish
		return d.isFinishHoove();
	}
}

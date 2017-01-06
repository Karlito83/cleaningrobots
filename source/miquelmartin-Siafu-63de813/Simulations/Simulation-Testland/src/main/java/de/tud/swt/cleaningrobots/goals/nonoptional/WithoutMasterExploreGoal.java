package de.tud.swt.cleaningrobots.goals.nonoptional;

import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.behaviours.DiscoverBehaviour;
import de.tud.swt.cleaningrobots.behaviours.LoadIfAtLoadStationBehaviour;
import de.tud.swt.cleaningrobots.behaviours.MoveBehaviour;
import de.tud.swt.cleaningrobots.behaviours.DiscoverAroundAtDestinationBehaviour;
import de.tud.swt.cleaningrobots.behaviours.merge.MergeAllNearBehaviour;
import de.tud.swt.cleaningrobots.goals.NonOptionalGoal;

/**
 * Non optional goal to discover the world without any control of a master. 
 * 
 * @author Christopher Werner
 *
 */
public class WithoutMasterExploreGoal extends NonOptionalGoal {

	private DiscoverBehaviour d;
	
	public WithoutMasterExploreGoal(AgentRole role) {
		super(role);
		
		DiscoverAroundAtDestinationBehaviour s = new DiscoverAroundAtDestinationBehaviour(role);
		System.out.println("Correct SeeAround: " + s.isHardwarecorrect());
		if (s.isHardwarecorrect()) {
			behaviours.add(s);
		} else {
			correct = false;
		}
		
		d = new DiscoverBehaviour(role, false);
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
		if (this.getAgentCore().getWorld().getNextUnknownFieldPosition() != null)
			return true;
		return false;
	}

	@Override
	public boolean postCondition() {
		//must look if the discover behavior is finish
		return d.isFinishDiscovering();
	}
}

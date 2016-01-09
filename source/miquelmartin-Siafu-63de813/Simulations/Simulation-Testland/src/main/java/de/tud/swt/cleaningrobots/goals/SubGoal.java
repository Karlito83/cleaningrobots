package de.tud.swt.cleaningrobots.goals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.model.State;

/**
 * A abstract goal which should not be implemented and do some specific work.
 * 
 * @author Christopher Werner
 *
 */
public abstract class SubGoal extends Goal {

	protected boolean correct;
	protected List<Behaviour> behaviours;
	
	public SubGoal(RobotCore robot, boolean optional) {
		super(robot);
		this.optional = optional;
		correct = true;
		behaviours = new ArrayList<Behaviour>();
	}
	
	@Override
	public boolean isOptional () {
		return optional;
	}
	
	@Override
	public void run() {
		for (Behaviour behaviour : behaviours) {
			try {
				behaviour.action();
			} catch (Exception e) {
				//run all actions of all behaviors
				e.printStackTrace();
			}
		}		
	}
	
	@Override
	public boolean isHardwareCorrect() {
		return correct;
	}
	
	@Override
	public Collection<State> getSupportedStates() {
		Set<State> supportedStates = new HashSet<State>();
		for (Behaviour beahaviour : behaviours) {
			supportedStates.addAll(beahaviour.getSupportedStates());
		}
		return supportedStates;
	}

}

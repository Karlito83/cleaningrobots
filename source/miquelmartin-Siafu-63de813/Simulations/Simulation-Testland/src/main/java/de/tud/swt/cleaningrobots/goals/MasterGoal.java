package de.tud.swt.cleaningrobots.goals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.tud.swt.cleaningrobots.AgentCore;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Master goal contains a list of goals.
 * 
 * @author Christopher Werner
 *
 */
public class MasterGoal extends Goal {

	public List<Goal> subGoals;

	public MasterGoal(AgentCore core) {
		super(core);

		this.subGoals = new ArrayList<Goal>();
	}

	@Override
	public void run() {
		//boolean test = false;
		for (Goal goal : subGoals) {
			goal.run();
			//if (goal.postCondition())
			//	test = true;			
		}		
		/*if (test) {
			List<Goal> copy = new ArrayList<Goal>(subGoals);
			for (Goal goal : copy) {
				if (goal.postCondition()) {
					subGoals.remove(goal);
				}
			}
		}*/
	}

	@Override
	public boolean isHardwareCorrect() {
		for (Goal goal : subGoals) {
			if (!goal.isHardwareCorrect())
				return false;
		}
		return true;
	}

	@Override
	public boolean preCondition() {
		for (Goal goal : subGoals) {
			if (!goal.preCondition())
				return false;
		}
		return true;
	}

	@Override
	public boolean postCondition() {
		//if goal is optional then return false
		if (isOptional())
			return false;
		//if sub goal post condition is false and it is not optional then return false
		for (Goal goal : subGoals) {
			if (!goal.postCondition() && !goal.isOptional())
				return false;
		}
		return true;
	}

	@Override
	public boolean isOptional() {
		for (Goal goal : subGoals) {
			if (!goal.isOptional())
				return false;
		}
		return true;
	}

	@Override
	public Collection<State> getSupportedStates() {
		Set<State> supportedStates = new HashSet<State>();
		for (Goal goal : subGoals) {			
			supportedStates.addAll(goal.getSupportedStates());
		}
		return supportedStates;
	}

	@Override
	public void initialize() {
		//Initialize all Subgoals
		for (Goal g : subGoals) {
			g.initialize();
		}
	}
	
	
}

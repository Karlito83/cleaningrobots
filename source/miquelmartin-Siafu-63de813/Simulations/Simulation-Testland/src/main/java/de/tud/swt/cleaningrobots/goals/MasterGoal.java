package de.tud.swt.cleaningrobots.goals;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.model.State;

public class MasterGoal extends Goal {

	public List<Goal> subGoals;

	public MasterGoal(RobotCore robot) {
		super(robot);

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
		//wenn alle unter Ziele optional sind dann ist Postcondition immer false
		if (isOptional())
			return false;
		//falls eine Post beim teilziel nicht true ist und das ziel nicht optional ist, ist post false
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
	
	
}

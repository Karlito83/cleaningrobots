package de.tud.swt.cleaningrobots.goals.nonoptional;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.MasterHooveAroundBehaviour;
import de.tud.swt.cleaningrobots.behaviours.MasterMoveBehaviour;
import de.tud.swt.cleaningrobots.behaviours.WlanOnBehaviour;
import de.tud.swt.cleaningrobots.goals.NonOptionalGoal;
import de.tud.swt.cleaningrobots.model.State;

public class MasterHooveRobotGoal extends NonOptionalGoal {

	private State WORLDSTATE_HOOVED;
	
	public MasterHooveRobotGoal(RobotCore robot) {
		super(robot);
		
		this.WORLDSTATE_HOOVED = ((State)robot.configuration.as).createState("Hooved");
		
		WlanOnBehaviour w = new WlanOnBehaviour(robot);
		System.out.println("Correct SeeAround: " + w.isHardwarecorrect());
		if (w.isHardwarecorrect()) {
			//robot.addBehaviour(s);
			behaviours.add(w);
		} else {
			correct = false;
		}
		
		MasterHooveAroundBehaviour s = new MasterHooveAroundBehaviour(robot);
		System.out.println("Correct Discover: " + s.isHardwarecorrect());
		if (s.isHardwarecorrect()) {
			//robot.addBehaviour(d);
			behaviours.add(s);
		} else {
			correct = false;
		}
		
		MasterMoveBehaviour m = new MasterMoveBehaviour(robot);
		System.out.println("Correct Move: " + m.isHardwarecorrect());
		if (m.isHardwarecorrect()) {
			//robot.addBehaviour(m);
			behaviours.add(m);
		} else {
			correct = false;
		}
	}

	@Override
	public boolean preCondition() {
		if (getRobotCore().getWorld().containsWorldState(WORLDSTATE_HOOVED))
			return false;
		return true;
	}

	@Override
	public boolean postCondition() {
		if (getRobotCore().getPosition().equals(getRobotCore().getDestinationContainer().getLoadStationPosition()) && 
				getRobotCore().getWorld().containsWorldState(WORLDSTATE_HOOVED))
			return true;
		return false;
	}
}

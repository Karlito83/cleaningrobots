package de.tud.swt.cleaningrobots.goals;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.HooveAroundBehaviour;
import de.tud.swt.cleaningrobots.behaviours.HooveBehaviour;
import de.tud.swt.cleaningrobots.behaviours.MoveBehaviour;
import de.tud.swt.cleaningrobots.model.State;

public class HooveLoadGoal extends NonOptionalGoal {

	private HooveBehaviour d;
	private final State STATE_HOOVE = State.createState("Hoove");
	
	public HooveLoadGoal(RobotCore robot) {
		super(robot);
		
		HooveAroundBehaviour s = new HooveAroundBehaviour(robot);
		System.out.println("Correct SeeAround: " + s.isHardwarecorrect());
		if (s.isHardwarecorrect()) {
			//robot.addBehaviour(s);
			behaviours.add(s);
		} else {
			correct = false;
		}
		
		d = new HooveBehaviour(robot);
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
	}

	@Override
	public boolean preCondition() {		
		if (this.getRobotCore().getWorld().getNextUnknownFieldPosition() != null || 
				this.getRobotCore().getWorld().getNextPassablePositionWithoutState(STATE_HOOVE) != null)
			return true;
		return false;
	}

	@Override
	public boolean postCondition() {
		return d.isFinishHoove();
		/*if (d.noMoreDiscovering)
			return true;
		if (this.getRobotCore().getWorld().getNextUnknownFieldPosition() == null 
				&& this.getRobotCore().getPosition().equals(this.getRobotCore().loadStationPosition))
			return true;
		return false;*/
	}
}

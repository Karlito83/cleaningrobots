package de.tud.swt.cleaningrobots.goals.nonoptional;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.behaviours.HooveAroundAtDestinationBehaviour;
import de.tud.swt.cleaningrobots.behaviours.HooveBehaviour;
import de.tud.swt.cleaningrobots.behaviours.MoveBehaviour;
import de.tud.swt.cleaningrobots.goals.NonOptionalGoal;
import de.tud.swt.cleaningrobots.model.State;

public class HooveLoadGoal extends NonOptionalGoal {

	private HooveBehaviour d;
	
	private State STATE_HOOVE;	
	private State WORLDSTATE_DISCOVERED;
	
	public HooveLoadGoal(RobotCore robot, boolean relative) {
		super(robot);
		
		this.STATE_HOOVE = ((State)robot.configuration.as).createState("Hoove");
		this.WORLDSTATE_DISCOVERED = ((State)robot.configuration.as).createState("Discovered");
		
		HooveAroundAtDestinationBehaviour s = new HooveAroundAtDestinationBehaviour(robot);
		System.out.println("Correct SeeAround: " + s.isHardwarecorrect());
		if (s.isHardwarecorrect()) {
			//robot.addBehaviour(s);
			behaviours.add(s);
		} else {
			correct = false;
		}
		
		d = new HooveBehaviour(robot, relative);
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
		boolean discovered = this.getRobotCore().getWorld().containsWorldState(WORLDSTATE_DISCOVERED);
		if (!discovered || this.getRobotCore().getWorld().getNextPassablePositionWithoutState(STATE_HOOVE) != null)
			return true;
		return false;
	}

	@Override
	public boolean postCondition() {
		//muss auch als Follower alle Informationen abgegeben haben bevor Ziel erfüllt ist
		if (!d.isFinishHoove())
			return false;
		else {
			boolean proof = true;
			for (RobotRole rr : getRobotCore().getRoles()) {
				if (rr.hasNewInformation())
					proof = false;
			}
			return proof;
		}
		//return d.isFinishHoove();
		/*if (d.noMoreDiscovering)
			return true;
		if (this.getRobotCore().getWorld().getNextUnknownFieldPosition() == null 
				&& this.getRobotCore().getPosition().equals(this.getRobotCore().loadStationPosition))
			return true;
		return false;*/
	}
}

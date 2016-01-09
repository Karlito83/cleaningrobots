package de.tud.swt.cleaningrobots.goals.nonoptional;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.behaviours.MoveBehaviour;
import de.tud.swt.cleaningrobots.behaviours.WipeAroundAtDestinationBehaviour;
import de.tud.swt.cleaningrobots.behaviours.WipeBehaviour;
import de.tud.swt.cleaningrobots.goals.NonOptionalGoal;
import de.tud.swt.cleaningrobots.model.State;

/**
 * Non optional goal to wipe the world without any merge functions. 
 * 
 * @author Christopher Werner
 *
 */
public class WipeLoadGoal extends NonOptionalGoal {

	private WipeBehaviour d;
	
	private State STATE_WIPE;	
	private State WORLDSTATE_DISCOVERED;
	
	public WipeLoadGoal(RobotCore robot, boolean relative) {
		super(robot);
		
		this.STATE_WIPE = robot.configuration.createState("Wipe");	
		this.WORLDSTATE_DISCOVERED = robot.configuration.createState("Discovered");
		
		WipeAroundAtDestinationBehaviour s = new WipeAroundAtDestinationBehaviour(robot);
		System.out.println("Correct SeeAround: " + s.isHardwarecorrect());
		if (s.isHardwarecorrect()) {
			behaviours.add(s);
		} else {
			correct = false;
		}
		
		d = new WipeBehaviour(robot, relative);
		System.out.println("Correct Discover: " + d.isHardwarecorrect());
		if (d.isHardwarecorrect()) {
			behaviours.add(d);
		} else {
			correct = false;
		}
		
		MoveBehaviour m = new MoveBehaviour(robot);
		System.out.println("Correct Move: " + m.isHardwarecorrect());
		if (m.isHardwarecorrect()) {
			behaviours.add(m);
		} else {
			correct = false;
		}
	}

	@Override
	public boolean preCondition() {		
		boolean discovered = this.getRobotCore().getWorld().containsWorldState(WORLDSTATE_DISCOVERED);
		if (!discovered || this.getRobotCore().getWorld().getNextPassablePositionWithoutState(STATE_WIPE) != null)
			return true;
		return false;
	}

	@Override
	public boolean postCondition() {
		if (!d.isFinishWipe())
			return false;
		else {
			boolean proof = true;
			for (RobotRole rr : getRobotCore().getRoles()) {
				if (rr.hasNewInformation())
					proof = false;
			}
			return proof;
		}
		//return d.isFinishWipe();
		/*if (d.noMoreDiscovering)
			return true;
		if (this.getRobotCore().getWorld().getNextUnknownFieldPosition() == null 
				&& this.getRobotCore().getPosition().equals(this.getRobotCore().loadStationPosition))
			return true;
		return false;*/
	}
}

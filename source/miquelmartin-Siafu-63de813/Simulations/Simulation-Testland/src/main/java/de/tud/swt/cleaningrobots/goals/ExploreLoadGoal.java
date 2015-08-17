package de.tud.swt.cleaningrobots.goals;

import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.behaviours.DiscoverBehaviour;
import de.tud.swt.cleaningrobots.behaviours.MoveBehaviour;
import de.tud.swt.cleaningrobots.behaviours.SeeAroundAtDestinationBehaviour;

/**
 * Discovers as long as no more unknown field exists and then drive back to Loadstation
 * 
 * @author ChrissiMobil
 *
 */
public class ExploreLoadGoal extends SubGoal {

	private DiscoverBehaviour d;
	
	public ExploreLoadGoal(RobotCore robot) {
		super(robot, false);
		
		SeeAroundAtDestinationBehaviour s = new SeeAroundAtDestinationBehaviour(robot);
		System.out.println("Correct SeeAround: " + s.isHardwarecorrect());
		if (s.isHardwarecorrect()) {
			//robot.addBehaviour(s);
			behaviours.add(s);
		} else {
			correct = false;
		}
		
		d = new DiscoverBehaviour(robot);
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
		if (this.getRobotCore().getWorld().getNextUnknownFieldPosition() != null)
			return true;
		return false;
	}

	@Override
	public boolean postCondition() {
		return d.isFinishDiscovering();
		/*if (d.noMoreDiscovering)
			return true;
		if (this.getRobotCore().getWorld().getNextUnknownFieldPosition() == null 
				&& this.getRobotCore().getPosition().equals(this.getRobotCore().loadStationPosition))
			return true;
		return false;*/
	}

}

package de.tud.swt.cleaningrobots.goals.nonoptional;

import de.tud.swt.cleaningrobots.RobotRole;
import de.tud.swt.cleaningrobots.behaviours.MasterCalculateExploreBehaviour;
import de.tud.swt.cleaningrobots.goals.NonOptionalGoal;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.roles.MasterRole;

/**
 * Non optional goal for a master to coordinate the followers to discover the world. 
 * 
 * @author Christopher Werner
 *
 */
public class MasterExploreMasterGoal extends NonOptionalGoal {
	
	private MasterCalculateExploreBehaviour mceb;
	private State WORLDSTATE_DISCOVERED;

	public MasterExploreMasterGoal(RobotRole role, boolean relative) {
		super(role);
		
		this.WORLDSTATE_DISCOVERED = getRobotCore().getConfiguration().createState("Discovered");
		
		mceb = new MasterCalculateExploreBehaviour(getRobotCore(), (MasterRole) role, relative);
		System.out.println("Correct SeeAround: " +mceb.isHardwarecorrect());
		if (mceb.isHardwarecorrect()) {
			behaviours.add(mceb);
		} else {
			correct = false;
		}
	}

	@Override
	public boolean preCondition() {
		if (getRobotCore().getWorld().containsWorldState(WORLDSTATE_DISCOVERED))
			return false;
		return true;
	}

	@Override
	public boolean postCondition() {
		return mceb.isFinishDisscovering();
	}
}

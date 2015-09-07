package de.tud.swt.cleaningrobots.behaviours;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.Position;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.model.World;

public class CleanBehaviour extends Behaviour {
	public CleanBehaviour(RobotCore robot) {
		super(robot);
		// TODO Auto-generated constructor stub
	}

	private final State CONST_STATE_DIRTY = State.createState("Dirty");
	private final State CONST_STATE_CLEAN = State.createState("Clean");

	@Override
	public boolean action() throws Exception {
		boolean result = false;
		World RobotsWorld = getRobot().getWorld();
		Position nextDirtyField = getRobot().getWorld().getNextPassablePositionWithoutState(
				CONST_STATE_DIRTY);
		if (nextDirtyField == null) {
			result = false;
		} else {
			//TODO: Implement
			System.out.println("The cleaning behaviour is not yet implemented!");
			result = false;
			//throw new Exception("The cleaning behaviour is not yet implemented");
		}
		
		return result;

	}

}

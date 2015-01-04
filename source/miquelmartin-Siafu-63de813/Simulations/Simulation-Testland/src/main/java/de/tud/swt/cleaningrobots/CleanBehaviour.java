package de.tud.swt.cleaningrobots;

public class CleanBehaviour extends Behaviour {
	private final String CONST_STATE_DIRTY = "Dirty";
	private final String CONST_STATE_CLEAN = "Clean";

	@Override
	public void action() {

		World RobotsWorld = getRobot().getWorld();
		Field nextDirtyField = getRobot().getWorld().getNextFieldByState(
				CONST_STATE_DIRTY);
	}

}

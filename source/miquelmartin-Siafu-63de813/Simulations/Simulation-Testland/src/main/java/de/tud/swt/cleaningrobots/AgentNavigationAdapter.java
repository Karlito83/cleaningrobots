package de.tud.swt.cleaningrobots;

import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.model.Position;

public class AgentNavigationAdapter implements INavigationController {

	private Agent agent;
	private World siafuWorld;

	public AgentNavigationAdapter(Agent agent, World siafuWorld) {
		this.agent = agent;
		this.siafuWorld = siafuWorld;
	}

	@Override
	public void setPosition(Position position) {
		de.nec.nle.siafu.model.Position siafuPosition = new de.nec.nle.siafu.model.Position(
				position.getY(), position.getX());
		agent.setPos(siafuPosition);
	}

	@Override
	public boolean isWall(int row, int col) {
		return siafuWorld.isAWall(new de.nec.nle.siafu.model.Position(row, col));
	}

	@Override
	public int getCol() {
		return agent.getPos().getCol();
	}

	@Override
	public int getRow() {
		return agent.getPos().getRow();
	}
}

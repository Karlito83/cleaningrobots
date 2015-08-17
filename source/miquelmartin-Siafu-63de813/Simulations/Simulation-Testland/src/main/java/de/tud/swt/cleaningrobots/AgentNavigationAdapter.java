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
	
	public Agent getAgent()	{
		return agent;
	}

	public World getSiafuWorld() {
		return siafuWorld;
	}
	
	public void moveTowardsDestination() {
		agent.moveTowardsDestination();
	}

	public boolean isAtDestination() {
		return agent.isAtDestination();
	}

	public void setPosition(Position position) {
		de.nec.nle.siafu.model.Position siafuPosition = new de.nec.nle.siafu.model.Position(
				position.getY(), position.getX());
		agent.setPos(siafuPosition);
	}
}

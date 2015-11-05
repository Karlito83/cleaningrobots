package de.tud.swt.cleaningrobots;

import de.nec.nle.siafu.model.MultiAgent;
import de.nec.nle.siafu.model.MultiWorld;
import de.tud.swt.cleaningrobots.model.Position;

public class AgentNavigationAdapterMulti implements INavigationController {

	private MultiAgent agent;
	private MultiWorld siafuWorld;
	
	public AgentNavigationAdapterMulti (MultiAgent agent, MultiWorld siafuWorld) {
		this.agent = agent;
		this.siafuWorld = siafuWorld;
	}
	
	@Override
	public void setPosition(Position position) {
		agent.setCol(position.getX());
		agent.setRow(position.getY());		
	}

	@Override
	public boolean isWall(int row, int col) {
		return siafuWorld.isAWall(row, col);
	}

	@Override
	public int getCol() {
		return agent.getCol();
	}

	@Override
	public int getRow() {
		return agent.getRow();
	}

}

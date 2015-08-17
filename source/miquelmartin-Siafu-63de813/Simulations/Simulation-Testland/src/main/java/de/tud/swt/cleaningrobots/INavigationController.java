package de.tud.swt.cleaningrobots;

import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.model.Position;

public interface INavigationController {
	//public Position getDestination();
	//public void setDestination(Position destination, Position start);
	public void moveTowardsDestination();
	public boolean isAtDestination();
	public void setPosition(Position position);
	public Agent getAgent();
	public World getSiafuWorld();
}

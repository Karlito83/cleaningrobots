package de.nec.nle.siafu.testland;

import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.Place;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.INavigationController;
import de.tud.swt.cleaningrobots.Position;

public class AgentDestinationAdapter implements INavigationController {

	private Agent agent;
	private World siafuWorld;

	public AgentDestinationAdapter(Agent agent, World siafuWorld) {
		this.agent = agent;
		this.siafuWorld = siafuWorld;
	}

	/***
	 * returns the destination or null if no destination is set
	 */
	public Position getDestination() {
		Position result = null;
		
		if(agent.getDestination()!=null){
			de.nec.nle.siafu.model.Position siafuPosition = agent.getDestination().getPos();
			result = new Position(siafuPosition.getCol(), siafuPosition.getRow());
		}

		return null;
	}

	public void setDestination(Position destination) {
		de.nec.nle.siafu.model.Position siafuPosition = new de.nec.nle.siafu.model.Position(destination.getY(), destination.getX());
		Place destinationPlace = new Place("Unknown", siafuPosition, siafuWorld);
		agent.setDestination(destinationPlace);
	}

	public void moveTowardsDestination() {
		agent.moveTowardsDestination();
	}
}

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
	
	public void setDestination(Position destination, Position start) {
		de.nec.nle.siafu.model.Position siafuDestination = new de.nec.nle.siafu.model.Position(destination.getY(), destination.getX());
		de.nec.nle.siafu.model.Position siafuStart = new de.nec.nle.siafu.model.Position(start.getY(), start.getX());
		Place destinationPlace = new Place("Unknown", siafuDestination, siafuWorld);

		
		
		new Place("Unknown", siafuDestination, siafuWorld, siafuStart);
		agent.setDestination(destinationPlace);
	}

	public void moveTowardsDestination() {
		agent.moveTowardsDestination();
	}

	public boolean isAtDestination() {
		return agent.isAtDestination();
	}
}

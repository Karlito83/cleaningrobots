package de.nec.nle.siafu.testland;

import java.util.ArrayList;
import java.util.Collection;

import de.nec.nle.siafu.model.Agent;
import de.nec.nle.siafu.model.Position;
import de.nec.nle.siafu.model.World;
import de.tud.swt.cleaningrobots.Field;
import de.tud.swt.cleaningrobots.ISensor;
import de.tud.swt.cleaningrobots.State;


/***
 * A Sensor which only supports scanning for blocked and non-blocked fields.
 * Supported states are "Blocked" and "Free".
 * @author ronny
 *
 */
public class BlockedOnlySensor implements ISensor {
	
	private Agent agent;
	private World siafuWorld;
	
	private final int CONST_VISIONRADIUS = 2;
	private ArrayList<State> supportedStates;
	
	private final State STATE_BLOCKED = State.createState("Blocked");
	private final State STATE_FREE = State.createState("Free");

	public BlockedOnlySensor(World siafuWorld, Agent agent) {
		super();
		
		this.supportedStates = new ArrayList<State>();
		/*supportedStates.add(STATE_CLEAN);
		supportedStates.add(STATE_DIRTY);*/
		supportedStates.add(STATE_BLOCKED);
		supportedStates.add(STATE_FREE);
		
		this.siafuWorld = siafuWorld;
		this.agent = agent;
	}
	
	public Collection<Field> getData() {
		
		Collection<Field> data = new ArrayList<Field>();
		
		for (int xOffset=-CONST_VISIONRADIUS; xOffset<=CONST_VISIONRADIUS; xOffset++){
			for (int yOffset = -CONST_VISIONRADIUS; yOffset<=CONST_VISIONRADIUS; yOffset++ )
			{
				data.add(getField(xOffset, yOffset));
			}
		}
		
		
		return data;
	}	
	
	private Field getField(int xOffset, int yOffset)
	{
		Field result = null;
		
		int row =  agent.getPos().getRow() + yOffset;
		int column =  agent.getPos().getCol() + xOffset;
		
		result = new Field(column, row, !siafuWorld.isAWall(new Position(row, column)));
		if(siafuWorld.isAWall(new Position(row, column)))
		{
			result.addState(STATE_BLOCKED);
		}
		else
		{
			result.addState(STATE_FREE);
		}
		
		
		return result;
	}

	/***
	 * Returns a copy of the SupportedStates Collection. 
	 */
	@SuppressWarnings("unchecked")
	public Collection<State> getSupportedStates() {
		return (Collection<State>) supportedStates.clone();
	}
}


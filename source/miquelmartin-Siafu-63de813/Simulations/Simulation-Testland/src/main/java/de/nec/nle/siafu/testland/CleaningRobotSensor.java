package de.nec.nle.siafu.testland;

import java.util.ArrayList;

import org.eclipse.emf.common.util.BasicEList;
import org.eclipse.emf.common.util.EList;

import cleaningrobots.CleaningrobotsFactory;
import cleaningrobots.Field;
import cleaningrobots.State;
import cleaningrobots.impl.SensorImpl;
import de.nec.nle.siafu.model.Position;
import de.nec.nle.siafu.model.World;

public class CleaningRobotSensor extends SensorImpl {
	
	private CleaningRobotAgent agent;
	private World siafuWorld;
	
	private final int CONST_VISIONRADIUS = 2;

	public CleaningRobotSensor(World siafuWorld, CleaningRobotAgent agent) {
		super();
		
		this.siafuWorld = siafuWorld;
		this.agent = agent;
	}
	
	@Override
	public EList<Field> getData() {
		
		EList<Field> data = new BasicEList<Field>();
		
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
		Field result = CleaningrobotsFactory.eINSTANCE.createField();
		
		int row =  agent.getPos().getRow();
		int column =  agent.getPos().getCol();
		
		result.setXpos(column);
		result.setYpos(row);
		if(siafuWorld.isAWall(new Position(row, column)))
		{
			State state = CleaningrobotsFactory.eINSTANCE.createState();
			state.setName("Blocked");
		}
		else
		{
			State state = CleaningrobotsFactory.eINSTANCE.createState();
			state.setName("Free");
		}
		
		
		return result;
	}
}


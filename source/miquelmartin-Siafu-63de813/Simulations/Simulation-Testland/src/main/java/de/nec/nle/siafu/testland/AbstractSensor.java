package de.nec.nle.siafu.testland;

import java.util.ArrayList;
import java.util.Collection;

import de.tud.swt.cleaningrobots.ISensor;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.State;

public abstract class AbstractSensor implements ISensor {

	private static final int CONST_DEFAULT_VISIONRADIUS = 2; 
	private ArrayList<State> supportedStates = new ArrayList<State>();
	
	@Override
	public Collection<Field> getData() {
		Collection<Field> data = new ArrayList<Field>();
		
		for (int xOffset=-getVisionRadius(); xOffset<=getVisionRadius(); xOffset++){
			for (int yOffset = -getVisionRadius(); yOffset<=getVisionRadius(); yOffset++ )
			{
				data.add(getField(xOffset, yOffset));
			}
		}
		
		
		return data;
	}

	protected abstract Field getField(int x, int y) ;

	private int getVisionRadius() {
		return CONST_DEFAULT_VISIONRADIUS;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<State> getSupportedStates() {
		return (Collection<State>) supportedStates.clone();
	}

}

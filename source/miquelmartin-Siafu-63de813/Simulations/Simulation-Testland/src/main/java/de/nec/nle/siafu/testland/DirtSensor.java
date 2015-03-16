package de.nec.nle.siafu.testland;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import de.tud.swt.cleaningrobots.ISensor;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.State;

public class DirtSensor implements ISensor {

	private final int CONST_VISIONRADIUS = 2;
	private final State STATE_DIRTY = State.createState("Dirty");
	private final State STATE_CLEAN = State.createState("Clean");
	
	private final ArrayList<State> supportedStates;
	
	public DirtSensor() {
		this.supportedStates = new ArrayList<State>();
		this.supportedStates.add(STATE_DIRTY);
		this.supportedStates.add(STATE_CLEAN);
	}
	
	@Override
	public Collection<Field> getData() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<State> getSupportedStates() {
		return (Collection<State>) supportedStates.clone();
	}

}

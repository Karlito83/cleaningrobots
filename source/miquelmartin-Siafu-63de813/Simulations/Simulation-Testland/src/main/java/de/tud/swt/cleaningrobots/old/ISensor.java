package de.tud.swt.cleaningrobots.old;

import java.util.Collection;

import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.State;

public interface ISensor {
	public Collection<Field> getData();

	public Collection<State> getSupportedStates();
}

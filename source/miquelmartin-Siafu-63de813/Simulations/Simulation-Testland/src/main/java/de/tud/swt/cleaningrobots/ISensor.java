package de.tud.swt.cleaningrobots;

import java.util.Collection;

public interface ISensor {
	public Collection<Field> getData();

	public Collection<State> getSupportedStates();
}

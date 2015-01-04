package de.tud.swt.cleaningrobots;

import java.util.Collection;

public interface ISensor {
	public void getData();

	public Collection<State> getSupportedStates();
}

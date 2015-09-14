package de.tud.swt.cleaningrobots.util;

import java.util.LinkedList;
import java.util.List;

import de.tud.swt.cleaningrobots.model.State;

public class ImportExportConfiguration {
	
	public List<State> knownStates;
	public int iteration;
	public boolean knowledge;
	public boolean knownstates;
	public boolean world;
	
	public ImportExportConfiguration () {
		this.iteration = -1;
		this.knownStates = new LinkedList<State>();
	}
	
	@Override
	public String toString() {
		return "I: " + iteration + " KStates: " + knownStates;
	}

}

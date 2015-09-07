package de.tud.swt.cleaningrobots.util;

import java.util.LinkedList;
import java.util.List;

import de.tud.swt.cleaningrobots.model.State;

public class ImportExportConfiguration {
	
	public List<State> knownStates;
	public boolean knowledge;
	public boolean knownstates;
	public boolean world;
	
	public ImportExportConfiguration () {
		knownStates = new LinkedList<State>();
	}
	

}

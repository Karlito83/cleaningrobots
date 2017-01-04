package de.tud.swt.cleaningrobots.util;

import java.util.LinkedList;
import java.util.List;

import de.tud.swt.cleaningrobots.model.State;

/**
 * Describe the configuration parameters to export and import information between robots.
 * 
 * @author Christopher Werner
 *
 */
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
		return "Iteration: " + iteration + " KnownStates: " + knownStates;
	}
	
	public ImportExportConfiguration getStateConfiguration () {
		ImportExportConfiguration iec = new ImportExportConfiguration();
		iec.knowledge = true;
		iec.knownstates = true;
		iec.world = true;
		iec.knownStates.addAll(knownStates);
		return iec;
	}

}

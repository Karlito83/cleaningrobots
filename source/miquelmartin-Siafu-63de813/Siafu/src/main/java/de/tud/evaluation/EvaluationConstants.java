package de.tud.evaluation;

import com.google.gson.Gson;

/**
 * Static Constants for the test case and the output of the evaluation documents.
 * 
 * @author Christopher Werner
 *
 */
public class EvaluationConstants {
	
	public static final boolean GLOBAL_USE_GUI = true;
	public static final Gson gson = new Gson();
	
	public static final int maxThreadCount = 1;
	public static final String simulationPathpfad = "..\\Simulations\\Simulation-Testland\\target\\classes";
		
	public static boolean local_gui_use = true;
	public static final boolean MULTISIMULATION_XML = true;
}

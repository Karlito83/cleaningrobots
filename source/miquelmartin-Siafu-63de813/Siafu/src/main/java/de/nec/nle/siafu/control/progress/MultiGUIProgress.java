package de.nec.nle.siafu.control.progress;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Font;
import org.eclipse.swt.graphics.FontData;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.ProgressBar;

public class MultiGUIProgress implements Progress {
	
	/** The system font, in bold. */
	private Font bold;

	/** Whether the agents are being created. */
	private boolean creatingAgents;

	/** Whether the simulation has already started. */
	private boolean simulationStarted;

	/** The name of the world. */
	private String worldName;

	/** Whether we hve started creating the world. */
	private boolean worldCreationStarted;

	/** The progress bar with for the cache loads. */
	private ProgressBar cacheBar;
	
	private boolean configFilesCreated;
	
	private boolean wallFilesCreated;

	/**
	 * Create an instance of this progress.
	 * 
	 */
	public MultiGUIProgress() {
		reset();
	}

	/**
	 * Do nothing. The GUI is not available anymore at the time the
	 * backgrounds are created.
	 */
	public synchronized void reportBackgroundCreated() {
		this.configFilesCreated = true;
	}

	/**
	 * Do nothing. The GUI is not available anymore at the time the
	 * backgrounds are created.
	 */
	public synchronized void reportBackgroundCreationEnd() {
		this.configFilesCreated = false;
	}

	/** Do nothing. */
	public synchronized void reportBackgroundCreationStart(final int amount) {
	}

	/** Do nothing. */
	public synchronized void reportCacheElementLoaded() {
	}

	/** Do nothing. */
	public synchronized void reportCachePrefill(final int amountOfElements) {
	}

	/** Do nothing. */
	public synchronized void reportCachePrefillEnded() {
	}

	/** Do nothing. */
	public synchronized void reportCreatingAgents() {
	}

	/** Do nothing. */
	public synchronized void reportPlacesFound(final String type,
			final int amount) {
	}

	/**
	 * Report that a place of the given type has been creted, and store the
	 * type so that the update mechanism can reflect it on the gui.
	 * 
	 * @param type the type of the place that has just been created
	 */
	public void reportPlaceCreated(final String type) {
		this.wallFilesCreated = true;
	}

	/** Store the change so the GUI can redraw it appropriately. */
	public synchronized void reportSimulationEnded() {
		// Do Nothing
	}

	/** Store the change so the GUI can redraw it appropriately. */
	public synchronized void reportSimulationStarted() {
		this.simulationStarted = true;
	}

	/**
	 * Store the change so the GUI can redraw it appropriately.
	 * 
	 * @param newWorldName the name of the world
	 */
	public synchronized void reportWorldCreation(final String newWorldName) {
		this.worldCreationStarted = true;
		this.worldName = newWorldName;
	}

	/**
	 * Update the composite with the information gathered since the last
	 * update.
	 * 
	 * @param parent the composite where the reports should be drawn
	 */
	public synchronized void update(final Composite parent) {
		if (bold == null) {
			FontData fd = Display.getCurrent().getSystemFont().getFontData()[0];
			bold = new Font(Display.getCurrent(), fd.getName(), fd.getHeight(),
							fd.getStyle() | SWT.BOLD);
		}
		
		if (configFilesCreated) {
			Label start = addLabel("Load Configuration files!", parent);
			start.setFont(bold);
			configFilesCreated = false;
		}
		
		if (wallFilesCreated) {
			Label start = addLabel("Load Wall Files!", parent);
			start.setFont(bold);
			wallFilesCreated = false;
		}

		if (worldCreationStarted) {
			Label start = addLabel("Creating world: " + worldName, parent);
			start.setFont(bold);
			worldCreationStarted = false;
		}
		
		if (simulationStarted) {
			addLabel("Simulation started", parent);
			simulationStarted = false;
			
			GridData gdLabel = new GridData(SWT.CENTER, SWT.CENTER, true, false);
			iterationLabel = new Label(parent, SWT.WRAP);
			iterationLabel.setLayoutData(gdLabel);
			iterationLabel.setAlignment(SWT.CENTER);
			iterationLabel.setText("Actual Iteration: 0");
		}	

		parent.layout();
	}
	
	private Label iterationLabel;
	
	private int lastIterationOut = 0;
	
	public synchronized void iterationUpdata(final Composite parent, int iteration) {
		if (iteration / 100 != lastIterationOut)
		{
			lastIterationOut = iteration / 100;
			iterationLabel.setText("Actual Iteration: " + lastIterationOut * 100);
		}
		parent.layout();
	}

	/**
	 * Add a standard label to report on the event given by message.
	 * 
	 * @param message the message to show
	 * @param comp the composite where the label should be added
	 * @return the created label
	 */
	private Label addLabel(final String message, final Composite comp) {
		GridData gdLabel = new GridData(SWT.CENTER, SWT.CENTER, true, false);
		Label l = new Label(comp, SWT.WRAP);
		l.setLayoutData(gdLabel);
		l.setAlignment(SWT.CENTER);
		l.setText(message);

		comp.layout();
		return l;
	}

	/**
	 * Initialize the progress to zero, that is, the state when no simulation
	 * is loaded at all.
	 * 
	 */
	public synchronized void reset() {
		// All elements have been disposed when
		// the parent composite was disposed.
		creatingAgents = false;
		configFilesCreated = false;
		wallFilesCreated = false;
		simulationStarted = false;
		worldName = null;
		worldCreationStarted = false;
		if (cacheBar != null) {
			cacheBar.dispose();
			cacheBar = null;
		}
	}

}

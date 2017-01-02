package de.nec.nle.siafu.graphics;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Scale;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

public class StandbyComposite extends Composite {

	private static Image siafuIcon = null;
	private static Image noteIcon = null;
	
	private Shell shell;

	private Display display = Display.getCurrent();

	private Composite splashComposite;
	private Composite hintComposite;

	public StandbyComposite(Composite parent) {
		super(parent, SWT.NONE);
		
		this.shell = (Shell) parent;
		
		GridLayout glStandByComposite = new GridLayout();
		GridData gdStandByComposite = new GridData(SWT.CENTER, SWT.CENTER,
				true, true);
		this.setLayout(glStandByComposite);
		this.setLayoutData(gdStandByComposite);

		createSplashComposite();
		createHintComposite();

	}

	private void createSplashComposite() {
		GridData gdSplashComposite = new GridData(SWT.CENTER, SWT.CENTER, true,
				true);
		GridLayout glSplashComposite = new GridLayout(1, false);
		splashComposite = new Composite(this, SWT.NONE);
		splashComposite.setLayout(glSplashComposite);
		splashComposite.setLayoutData(gdSplashComposite);
		
		if (siafuIcon == null) {
			siafuIcon = new Image(display, getClass().getResourceAsStream(
					"/misc/icon.png"));
		}
		
		GridData gdIconLabel = new GridData(SWT.CENTER, SWT.CENTER, false, false);
		Label iconLabel = new Label(splashComposite, SWT.NONE);
		iconLabel.setImage(siafuIcon);
		iconLabel.setLayoutData(gdIconLabel);
		
		Label splashLabel = new Label(splashComposite, SWT.NONE);
		splashLabel.setText("Load a simulation to start");
	}
	
	private void createMapSelection () {
		//Configuration of the used simulation config		
		GridData gdOutputGroup = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
		GridLayout glOutputGroup = new GridLayout(1, false);
		Group outputGroup = new Group(hintComposite, SWT.NORMAL);
		outputGroup.setText("Map Selection:");
		outputGroup.setLayout(glOutputGroup);
		outputGroup.setLayoutData(gdOutputGroup);

		GridLayout glOutputTypeComposite = new GridLayout(1, false);
		glOutputTypeComposite.marginWidth = 0;
		Composite outputTypeComposite =	new Composite(outputGroup, SWT.NONE);
		outputTypeComposite.setLayout(glOutputTypeComposite);

		Button quadraticRadio = new Button(outputTypeComposite, SWT.RADIO);
		Button fakultyRadio = new Button(outputTypeComposite, SWT.RADIO);
		Button labyrinthRadio = new Button(outputTypeComposite, SWT.RADIO);
				
		quadraticRadio.setText("Quadratic Map");
		quadraticRadio.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				quadraticRadio.setSelection(true);
				labyrinthRadio.setSelection(false);
				fakultyRadio.setSelection(false);
			}
		});
		quadraticRadio.setSelection(true);
		//nullOutputRadio.setSelection(conf.getString("output.type").equalsIgnoreCase("null"));
				
		fakultyRadio.setText("Computerscience TU Dresden");
		//csvOutputRadio.setSelection(conf.getString("output.type").equalsIgnoreCase("csv"));		
		fakultyRadio.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				fakultyRadio.setSelection(true);
				labyrinthRadio.setSelection(false);
				quadraticRadio.setSelection(false);
			}
		});
				
		labyrinthRadio.setText("Labyrinth");
		//labyrinthRadio.setSelection(conf.getString("output.type").equalsIgnoreCase("csv"));		
		labyrinthRadio.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				labyrinthRadio.setSelection(true);
				fakultyRadio.setSelection(false);
				quadraticRadio.setSelection(false);
			}
		});
	}
	
	private void createStrategySelection () {
		//Configuration of the used simulation config		
		GridData gdStrategyGroup = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
		GridLayout glStrategyGroup = new GridLayout(1, false);
		Group strategyGroup = new Group(hintComposite, SWT.NORMAL);
		strategyGroup.setText("Using Strategy:");
		strategyGroup.setLayout(glStrategyGroup);
		strategyGroup.setLayoutData(gdStrategyGroup);

		GridLayout glStrategyTypeComposite = new GridLayout(1, false);
		glStrategyTypeComposite.marginWidth = 0;
		Composite strategyTypeComposite =	new Composite(strategyGroup, SWT.NONE);
		strategyTypeComposite.setLayout(glStrategyTypeComposite);

		Button withoutMasterRadio = new Button(strategyTypeComposite, SWT.RADIO);
		Button controllingMasterRadio = new Button(strategyTypeComposite, SWT.RADIO);
		Button mergeMasterRadio = new Button(strategyTypeComposite, SWT.RADIO);
		Button mergeDevideMasterRadio = new Button(strategyTypeComposite, SWT.RADIO);
						
		withoutMasterRadio.setText("Without Master");
		withoutMasterRadio.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				withoutMasterRadio.setSelection(true);
				controllingMasterRadio.setSelection(false);
				mergeMasterRadio.setSelection(false);
				mergeDevideMasterRadio.setSelection(false);
			}
		});
		withoutMasterRadio.setSelection(true);
						
		controllingMasterRadio.setText("Complete controlling Master");				
		controllingMasterRadio.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				controllingMasterRadio.setSelection(true);
				withoutMasterRadio.setSelection(false);
				mergeMasterRadio.setSelection(false);
				mergeDevideMasterRadio.setSelection(false);
			}
		});
				
		mergeMasterRadio.setText("Master Merges");				
		mergeMasterRadio.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				mergeMasterRadio.setSelection(true);
				withoutMasterRadio.setSelection(false);
				controllingMasterRadio.setSelection(false);
				mergeDevideMasterRadio.setSelection(false);
			}
		});
				
		mergeDevideMasterRadio.setText("Master Merge and Devide");				
		mergeDevideMasterRadio.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				mergeDevideMasterRadio.setSelection(true);
				withoutMasterRadio.setSelection(false);
				controllingMasterRadio.setSelection(false);
				mergeMasterRadio.setSelection(false);
			}
		});
	}
	
	private void createAgentNumberSelection () {
		//Configuration Number of Agents
		GridData gdUiGroup = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
		GridLayout glUiGroup = new GridLayout(1, false);
		Group uiGroup = new Group(hintComposite, SWT.NORMAL);
		uiGroup.setText("Number of Agents:");
		uiGroup.setLayout(glUiGroup);
		uiGroup.setLayoutData(gdUiGroup);

		//Number of explore Robots
		GridData gdExploreComposite =	new GridData(SWT.FILL, SWT.BEGINNING, true, false);
		GridLayout glExploreComposite = new GridLayout(2, false);
		glExploreComposite.marginWidth = 0;
		Composite exploreComposite = new Composite(uiGroup, SWT.NONE);
		exploreComposite.setLayout(glExploreComposite);
		exploreComposite.setLayoutData(gdExploreComposite);
				
		//Number of hoove Robots
		GridData gdHooveComposite =	new GridData(SWT.FILL, SWT.BEGINNING, true, false);
		GridLayout glHooveComposite = new GridLayout(2, false);
		glHooveComposite.marginWidth = 0;
		Composite hooveComposite = new Composite(uiGroup, SWT.NONE);
		hooveComposite.setLayout(glHooveComposite);
		hooveComposite.setLayoutData(gdHooveComposite);
				
		//Number of wipe Robots
		GridData gdWipeComposite =	new GridData(SWT.FILL, SWT.BEGINNING, true, false);
		GridLayout glWipeComposite = new GridLayout(2, false);
		glWipeComposite.marginWidth = 0;
		Composite wipeComposite = new Composite(uiGroup, SWT.NONE);
		wipeComposite.setLayout(glWipeComposite);
		wipeComposite.setLayoutData(gdWipeComposite);
				
		Scale wipeScale = new Scale(wipeComposite, SWT.HORIZONTAL), exploreScale = new Scale(exploreComposite, SWT.HORIZONTAL), hooveScale = new Scale(hooveComposite, SWT.HORIZONTAL);
		Label wipeLabel = new Label(wipeComposite, SWT.NONE), exploreLabel = new Label(exploreComposite, SWT.NONE), hooveLabel = new Label(hooveComposite, SWT.NONE);
				
		//real explore gui
		GridData gdExploreScale = new GridData(130, SWT.DEFAULT);
		GridData gdExploreLabel =	new GridData(SWT.FILL, SWT.CENTER, true, false);
		exploreScale.setLayoutData(gdExploreScale);
		exploreScale.setMinimum(1);
		exploreScale.setMaximum(10);
		exploreScale.setSelection(1);
		//speedScale.setSelection(conf.getInt("ui.speed"));
		exploreLabel.setLayoutData(gdExploreLabel);
		exploreLabel.setText("Number Explore Agents: 1  ");
		exploreScale.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				exploreLabel.setText("Number Explore Agents: " + ((Scale) e.widget).getSelection());				
			}
		});	

		//real hoove gui
		GridData gdHooveScale = new GridData(130, SWT.DEFAULT);
		GridData gdHooveLabel =	new GridData(SWT.FILL, SWT.CENTER, true, false);
		hooveScale.setLayoutData(gdHooveScale);
		hooveScale.setMinimum(0);
		hooveScale.setMaximum(10);
		hooveScale.setSelection(1);
		//speedScale.setSelection(conf.getInt("ui.speed"));
		hooveLabel.setLayoutData(gdHooveLabel);
		hooveLabel.setText("Number Hoove Agents: 1  ");
		hooveScale.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				hooveLabel.setText("Number Hoove Agents: " + ((Scale) e.widget).getSelection());
				
				if (((Scale) e.widget).getSelection() == 0)
				{	
					wipeLabel.setVisible(false);
					wipeScale.setVisible(false);
				}
				else
				{
					wipeLabel.setVisible(true);
					wipeScale.setVisible(true);	
				}
			}
		});
				
		//real wipe gui
		GridData gdWipeScale = new GridData(130, SWT.DEFAULT);
		GridData gdWipeLabel =	new GridData(SWT.FILL, SWT.CENTER, true, false);
		wipeScale.setLayoutData(gdWipeScale);
		wipeScale.setMinimum(0);
		wipeScale.setMaximum(10);
		wipeScale.setSelection(1);
		//speedScale.setSelection(conf.getInt("ui.speed"));
		wipeLabel.setLayoutData(gdWipeLabel);
		wipeLabel.setText("Number Wipe Agents: 1  ");
		wipeScale.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				wipeLabel.setText("Number Wipe Agents: " + ((Scale) e.widget).getSelection());
			}
		});
	}
	
	private void createStartSimulationPart () {
		//Configuration Number of Agents
		GridData gdUiGroup = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
		GridLayout glUiGroup = new GridLayout(1, false);
		Group uiGroup = new Group(hintComposite, SWT.NORMAL);
		uiGroup.setText("Start Simulation:");
		uiGroup.setLayout(glUiGroup);
		uiGroup.setLayoutData(gdUiGroup);
		
		//Use GUI Button
		Button useUI = new Button(uiGroup, SWT.CHECK);
		useUI.setText("Use the Graphical user interface");
		useUI.setSelection(true);
		//useUI.setToolTipText("Note that you will need to edit\nthe config file to reverse this setting!");
				
		//read Config.xml
		GridData gdCSVOutputComposite =	new GridData(SWT.FILL, SWT.BEGINNING, true, false);
		GridLayout glCSVOutputComposite = new GridLayout(1, false);
		glCSVOutputComposite.marginWidth = 0;
		Composite csvOutputComposite = new Composite(uiGroup, SWT.NONE);
		csvOutputComposite.setLayout(glCSVOutputComposite);
		csvOutputComposite.setLayoutData(gdCSVOutputComposite);

		GridData gdCSVPathComposite = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
		GridLayout glCSVPathComposite = new GridLayout(2, false);
		glCSVPathComposite.marginWidth = 0;
		Composite csvPathComposite = new Composite(csvOutputComposite, SWT.NONE);
		csvPathComposite.setLayoutData(gdCSVPathComposite);
		csvPathComposite.setLayout(glCSVPathComposite);

		GridData gdCSVPath = new GridData(SWT.FILL, SWT.BEGINNING, true, true);
		Text csvPath = new Text(csvPathComposite, SWT.BORDER);
		csvPath.setLayoutData(gdCSVPath);
		csvPath.setText("");
		Button csvChooseButton = new Button(csvPathComposite, SWT.PUSH);
		csvChooseButton.setText("Select");
		csvChooseButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {				
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
				fd.setText("Open a simulation");
				fd.setFilterPath(csvPath.getText());
				String value = fd.open();
				if (value != null)
				{
					csvPath.setText(value);
				}
			}
		});
				
		//start simulation
		Button startSimulationButton = new Button(uiGroup, SWT.PUSH);
		startSimulationButton.setText("Start Simulation");
		startSimulationButton.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {				
				FileDialog fd = new FileDialog(shell, SWT.OPEN);
				fd.setText("Open a simulation");
				fd.setFilterPath(csvPath.getText());
				String value = fd.open();
				if (value != null)
				{
					csvPath.setText(value);
				}
			}
		});
	}
	
	private void saveRobotData () {
		//Configuration of the saving png, xml, csv		
		GridData gdOutputGroup = new GridData(SWT.FILL, SWT.BEGINNING, true, false);
		GridLayout glOutputGroup = new GridLayout(1, false);
		Group outputGroup = new Group(hintComposite, SWT.NORMAL);
		outputGroup.setText("Map Selection:");
		outputGroup.setLayout(glOutputGroup);
		outputGroup.setLayoutData(gdOutputGroup);

		GridLayout glOutputTypeComposite = new GridLayout(1, false);
		glOutputTypeComposite.marginWidth = 0;
		Composite outputTypeComposite =	new Composite(outputGroup, SWT.NONE);
		outputTypeComposite.setLayout(glOutputTypeComposite);

		Button quadraticRadio = new Button(outputTypeComposite, SWT.CHECK);
		Button fakultyRadio = new Button(outputTypeComposite, SWT.CHECK);
		Button labyrinthRadio = new Button(outputTypeComposite, SWT.CHECK);
						
		quadraticRadio.setText("PNG Save");
		quadraticRadio.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				//quadraticRadio.setSelection(true);
			}
		});
		quadraticRadio.setSelection(true);
		//nullOutputRadio.setSelection(conf.getString("output.type").equalsIgnoreCase("null"));
						
		fakultyRadio.setText("XML Save");
		//csvOutputRadio.setSelection(conf.getString("output.type").equalsIgnoreCase("csv"));		
		fakultyRadio.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				//fakultyRadio.setSelection(true);
			}
		});
				
		labyrinthRadio.setText("CSV Save");
		//labyrinthRadio.setSelection(conf.getString("output.type").equalsIgnoreCase("csv"));		
		labyrinthRadio.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				//labyrinthRadio.setSelection(true);
			}
		});
	}	
	
	private void createHintComposite() {
		GridData gdhintComposite = new GridData(SWT.FILL, SWT.CENTER, true,
				true);
		//gdhintComposite.verticalIndent=40;
		
		GridLayout glhintComposite = new GridLayout(3, false);
		hintComposite = new Composite(this, SWT.NONE);
		hintComposite.setLayout(glhintComposite);
		hintComposite.setLayoutData(gdhintComposite);
		if (noteIcon == null) {
			noteIcon = new Image(display, getClass().getResourceAsStream(
					"/misc/note.png"));
		}
		
		GridData gdNoteLabel = new GridData(SWT.CENTER, SWT.TOP, false,true);
		Label noteLabel = new Label(hintComposite, SWT.NONE);
		noteLabel.setImage(noteIcon);
		noteLabel.setLayoutData(gdNoteLabel);
		
		Label splashLabel = new Label(hintComposite, SWT.TOP);
		splashLabel.setText("By the way:\n- Select agents with your mouse or right-click for a context menu\n- Move agents by selecting them and double-clicking on the destination");
		
		createMapSelection();
		createStrategySelection();
		saveRobotData();
		createAgentNumberSelection();
		createStartSimulationPart();		
	}

	public void dispose(){
		super.dispose();
		siafuIcon.dispose();
		siafuIcon=null;
		noteIcon.dispose();
		noteIcon=null;
	}
}

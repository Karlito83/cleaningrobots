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

import de.tud.evaluation.EvaluationConstants;

public class StandbyComposite extends Composite {

	private static Image siafuIcon = null;
	private static Image noteIcon = null;
	
	private Shell shell;

	private Display display = Display.getCurrent();

	private Composite splashComposite;
	private Composite hintComposite;
	
	private GUI gui;

	public StandbyComposite(Composite parent, GUI gui) {
		super(parent, SWT.NONE);
		
		this.gui = gui;
		this.shell = (Shell) parent;
		
		gui.getWorkingConfiguration().setConfig();
		EvaluationConstants.local_gui_use = true;
		
		GridLayout glStandByComposite = new GridLayout();
		GridData gdStandByComposite = new GridData(SWT.CENTER, SWT.CENTER,
				true, true);
		this.setLayout(glStandByComposite);
		this.setLayoutData(gdStandByComposite);

		//createSplashComposite();
		createLogoAndText();
		createStartSimulationPart();
		createStrategySelection();
		createAgentNumberSelection();
		createMapSelection();
		saveRobotData();	
		//createHintComposite();

	}

	private void createSplashComposite() {
		GridData gdSplashComposite = new GridData(SWT.CENTER, SWT.CENTER, true,	true);
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
	
	private void createLogoAndText() {
		
		GridData gdhintComposite = new GridData(SWT.CENTER, SWT.CENTER, true, true);
		//gdhintComposite.verticalIndent=40;
		//gdhintComposite.verticalAlignment = 40;
		GridLayout glhintComposite = new GridLayout(3, false);
		hintComposite = new Composite(this, SWT.NONE);
		hintComposite.setLayout(glhintComposite);
		hintComposite.setLayoutData(gdhintComposite);
		if (noteIcon == null) {
			noteIcon = new Image(display, getClass().getResourceAsStream(
					"/misc/logo.png"));				
		}
		
		GridData gdNoteLabel = new GridData(SWT.CENTER, SWT.CENTER, false,true);
		Label noteLabel = new Label(hintComposite, SWT.NONE);
		noteLabel.setImage(noteIcon);
		noteLabel.setLayoutData(gdNoteLabel);

		GridData gdLabelComposite = new GridData(SWT.BEGINNING, SWT.BEGINNING, true, true);
		gdLabelComposite.horizontalSpan = 2;
		Label splashLabel = new Label(hintComposite, SWT.TOP);
		splashLabel.setLayoutData(gdLabelComposite);
		splashLabel.setText("Simulation Software for different Strategies on a Map \n\n" +
					"Siafu Simulator by Miquel Martin \n" +
					"Simulation Environement and Implementation by Christopher Werner \n\n" +
					"Configure your specific simulation to start and open the \nsimulation files to run your configured Simulation.");
		//splashLabel.setText("By the way:\n- Select agents with your mouse or right-click for a context menu\n- Move agents by selecting them and double-clicking on the destination");
				
	}
	
	private void createMapSelection () {
		//Configuration of the used simulation config		
		GridData gdOutputGroup = new GridData(SWT.FILL, SWT.FILL, true, false);
		GridLayout glOutputGroup = new GridLayout(1, false);
		Group outputGroup = new Group(hintComposite, SWT.NORMAL);
		outputGroup.setText("Map Selection:");
		outputGroup.setLayout(glOutputGroup);
		outputGroup.setLayoutData(gdOutputGroup);

		GridLayout glOutputTypeComposite = new GridLayout(1, false);
		glOutputTypeComposite.marginWidth = 0;
		Composite outputTypeComposite =	new Composite(outputGroup, SWT.NONE);
		outputTypeComposite.setLayout(glOutputTypeComposite);
		
		GridData gdLabelComposite = new GridData(SWT.BEGINNING, SWT.BEGINNING, true, true);
		Label splashLabel = new Label(outputTypeComposite, SWT.TOP);
		splashLabel.setLayoutData(gdLabelComposite);
		splashLabel.setText("Choose a map to run your simulation \nconfiguration on it.\n");

		Button quadraticRadio = new Button(outputTypeComposite, SWT.RADIO);
		Button fakultyRadio = new Button(outputTypeComposite, SWT.RADIO);
		Button fakultyCompleteRadio = new Button(outputTypeComposite, SWT.RADIO);
		Button labyrinthRadio = new Button(outputTypeComposite, SWT.RADIO);
				
		quadraticRadio.setText("Quadratic Map");
		quadraticRadio.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				gui.getWorkingConfiguration().map = 0;
				quadraticRadio.setSelection(true);
				labyrinthRadio.setSelection(false);
				fakultyRadio.setSelection(false);
				fakultyCompleteRadio.setSelection(false);
			}
		});
		quadraticRadio.setSelection(true);
		//nullOutputRadio.setSelection(conf.getString("output.type").equalsIgnoreCase("null"));
				
		fakultyCompleteRadio.setText("Computerscience TU Dresden Full Fakulty");
		//csvOutputRadio.setSelection(conf.getString("output.type").equalsIgnoreCase("csv"));		
		fakultyCompleteRadio.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				gui.getWorkingConfiguration().map = 2;
				fakultyCompleteRadio.setSelection(true);
				labyrinthRadio.setSelection(false);
				quadraticRadio.setSelection(false);
				fakultyRadio.setSelection(false);
			}
		});
		
		fakultyRadio.setText("Computerscience TU Dresden Half Fakulty");
		//csvOutputRadio.setSelection(conf.getString("output.type").equalsIgnoreCase("csv"));		
		fakultyRadio.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				gui.getWorkingConfiguration().map = 3;
				fakultyRadio.setSelection(true);
				labyrinthRadio.setSelection(false);
				quadraticRadio.setSelection(false);
				fakultyCompleteRadio.setSelection(false);
			}
		});
				
		labyrinthRadio.setText("Labyrinth");
		//labyrinthRadio.setSelection(conf.getString("output.type").equalsIgnoreCase("csv"));		
		labyrinthRadio.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				gui.getWorkingConfiguration().map = 1;
				labyrinthRadio.setSelection(true);
				fakultyRadio.setSelection(false);
				quadraticRadio.setSelection(false);
				fakultyCompleteRadio.setSelection(false);
			}
		});
	}
	
	private void createStrategySelection () {
		//Configuration of the used simulation config		
		GridData gdStrategyGroup = new GridData(SWT.FILL, SWT.FILL, true, false);
		gdStrategyGroup.verticalIndent=40;
		gdStrategyGroup.verticalSpan = 2;
		GridLayout glStrategyGroup = new GridLayout(1, false);
		Group strategyGroup = new Group(hintComposite, SWT.NORMAL);
		strategyGroup.setText("Using Strategy:");
		strategyGroup.setLayout(glStrategyGroup);
		strategyGroup.setLayoutData(gdStrategyGroup);

		GridLayout glStrategyTypeComposite = new GridLayout(1, false);
		glStrategyTypeComposite.marginWidth = 0;
		Composite strategyTypeComposite =	new Composite(strategyGroup, SWT.NONE);
		strategyTypeComposite.setLayout(glStrategyTypeComposite);
		
		GridData gdLabelComposite = new GridData(SWT.BEGINNING, SWT.BEGINNING, true, true);
		Label splashLabel = new Label(strategyTypeComposite, SWT.TOP);
		splashLabel.setLayoutData(gdLabelComposite);
		splashLabel.setText("Choose one of the default strategies to \nrun the simulation.\n");

		Button withoutMasterRadio = new Button(strategyTypeComposite, SWT.RADIO);
		
		GridData gdLabelComposite2 = new GridData(SWT.BEGINNING, SWT.BEGINNING, true, true);
		Label splashLabel2 = new Label(strategyTypeComposite, SWT.TOP);
		splashLabel2.setLayoutData(gdLabelComposite2);
		splashLabel2.setText("All agents communicate with each other if \nthey meet on the map.\n");
		
		Button controllingMasterRadio = new Button(strategyTypeComposite, SWT.RADIO);
		
		GridData gdLabelComposite3 = new GridData(SWT.BEGINNING, SWT.BEGINNING, true, true);
		Label splashLabel3 = new Label(strategyTypeComposite, SWT.TOP);
		splashLabel3.setLayoutData(gdLabelComposite3);
		splashLabel3.setText("A master agent communicate every time with \neach agent and say what they should do. \nThe master is the only agent with knowledge \ninformation about the map.\n");
		
		Button mergeMasterRadio = new Button(strategyTypeComposite, SWT.RADIO);
		
		GridData gdLabelComposite4 = new GridData(SWT.BEGINNING, SWT.BEGINNING, true, true);
		Label splashLabel4 = new Label(strategyTypeComposite, SWT.TOP);
		splashLabel4.setLayoutData(gdLabelComposite4);
		splashLabel4.setText("A master ist the global communication point \nand the other agents only do there work on \nthere local model. Creates a hierarchical \ncommuncation tree under the agents.\n");
		
		Button mergeDevideMasterRadio = new Button(strategyTypeComposite, SWT.RADIO);
		
		GridData gdLabelComposite5 = new GridData(SWT.BEGINNING, SWT.BEGINNING, true, true);
		Label splashLabel5 = new Label(strategyTypeComposite, SWT.TOP);
		splashLabel5.setLayoutData(gdLabelComposite5);
		splashLabel5.setText("Same as the \"Master Merge\" strategy, \nbut the master although devide the agents \nafter they meet each other that they all \ndistributed over the area.\n");
						
		withoutMasterRadio.setText("Without Master");
		withoutMasterRadio.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				gui.getWorkingConfiguration().config = 1;
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
				gui.getWorkingConfiguration().config = 0;
				controllingMasterRadio.setSelection(true);
				withoutMasterRadio.setSelection(false);
				mergeMasterRadio.setSelection(false);
				mergeDevideMasterRadio.setSelection(false);
			}
		});
				
		mergeMasterRadio.setText("Master Merge");				
		mergeMasterRadio.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				gui.getWorkingConfiguration().config = 2;
				mergeMasterRadio.setSelection(true);
				withoutMasterRadio.setSelection(false);
				controllingMasterRadio.setSelection(false);
				mergeDevideMasterRadio.setSelection(false);
			}
		});
				
		mergeDevideMasterRadio.setText("Master Merge and Devide");				
		mergeDevideMasterRadio.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				gui.getWorkingConfiguration().config = 3;
				mergeDevideMasterRadio.setSelection(true);
				withoutMasterRadio.setSelection(false);
				controllingMasterRadio.setSelection(false);
				mergeMasterRadio.setSelection(false);
			}
		});
	}
	
	private void createAgentNumberSelection () {
		//Configuration Number of Agents
		GridData gdUiGroup = new GridData(SWT.FILL, SWT.FILL, true, false);
		gdUiGroup.verticalIndent=40;
		//gdUiGroup.verticalSpan = 2;
		GridLayout glUiGroup = new GridLayout(1, false);
		Group uiGroup = new Group(hintComposite, SWT.NORMAL);
		uiGroup.setText("Number of Agents:");
		uiGroup.setLayout(glUiGroup);
		uiGroup.setLayoutData(gdUiGroup);
		
		GridData gdLabelComposite = new GridData(SWT.BEGINNING, SWT.BEGINNING, true, true);
		Label splashLabel = new Label(uiGroup, SWT.TOP);
		splashLabel.setLayoutData(gdLabelComposite);
		splashLabel.setText("Choose the number of agents from each category. \nYou need minimal one hoove agent to create \nalthough one wipe agent.\n");

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
				int number = ((Scale) e.widget).getSelection();
				exploreLabel.setText("Number Explore Agents: " + number);
				gui.getWorkingConfiguration().number_explore_agents = number;
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
				int number = ((Scale) e.widget).getSelection();
				hooveLabel.setText("Number Hoove Agents: " + number);
				gui.getWorkingConfiguration().number_hoove_agents = number;
				
				if (number == 0)
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
				int number = ((Scale) e.widget).getSelection();
				wipeLabel.setText("Number Wipe Agents: " + number);
				gui.getWorkingConfiguration().number_wipe_agents = number;
			}
		});
	}
	
	private void createStartSimulationPart () {
		//Configuration Number of Agents
		GridData gdUiGroup = new GridData(SWT.FILL, SWT.FILL, true, false);
		gdUiGroup.verticalIndent=40;
		GridLayout glUiGroup = new GridLayout(1, false);
		Group uiGroup = new Group(hintComposite, SWT.NORMAL);
		uiGroup.setText("Global Configuration:");
		uiGroup.setLayout(glUiGroup);
		uiGroup.setLayoutData(gdUiGroup);
		
		GridData gdLabelComposite = new GridData(SWT.BEGINNING, SWT.BEGINNING, true, true);
		Label splashLabel = new Label(uiGroup, SWT.TOP);
		splashLabel.setLayoutData(gdLabelComposite);
		splashLabel.setText("Run the simulation on the userinterface \nor in the background.\n");
		
		//Use GUI Button
		Button useUI = new Button(uiGroup, SWT.CHECK);
		useUI.setText("Use the Graphical user interface");
		useUI.setSelection(true);
		useUI.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				boolean boolValue = ((Button) e.widget).getSelection();
				EvaluationConstants.local_gui_use = boolValue;
				//quadraticRadio.setSelection(true);
			}
		});
		
		GridData gdLabelComposite2 = new GridData(SWT.BEGINNING, SWT.BEGINNING, true, true);
		Label splashLabel2 = new Label(uiGroup, SWT.TOP);
		splashLabel2.setLayoutData(gdLabelComposite2);
		splashLabel2.setText("To distinguisch between output files of same \nconfigurations shows different run values \nfor each configuration.\n");
		
		//Number of wipe Robots
		GridData gdRunComposite =	new GridData(SWT.FILL, SWT.BEGINNING, true, false);
		GridLayout glRunComposite = new GridLayout(2, false);
		glRunComposite.marginWidth = 0;
		Composite runComposite = new Composite(uiGroup, SWT.NONE);
		runComposite.setLayout(glRunComposite);
		runComposite.setLayoutData(gdRunComposite);
		
		Scale runScale = new Scale(runComposite, SWT.HORIZONTAL);
		Label runLabel = new Label(runComposite, SWT.NONE);
		
		GridData gdRunScale = new GridData(130, SWT.DEFAULT);
		GridData gdRunLabel =	new GridData(SWT.FILL, SWT.CENTER, true, false);
		runScale.setLayoutData(gdRunScale);
		runScale.setMinimum(0);
		runScale.setMaximum(10);
		runScale.setSelection(1);
		//speedScale.setSelection(conf.getInt("ui.speed"));
		runLabel.setLayoutData(gdRunLabel);
		runLabel.setText("Run Number: 1  ");
		runScale.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				int number = ((Scale) e.widget).getSelection();
				runLabel.setText("Run Number: " + number);
				gui.getWorkingConfiguration().run = number;
			}
		});
		
		//useUI.setToolTipText("Note that you will need to edit\nthe config file to reverse this setting!");
				
		//read Config.xml
		/*GridData gdCSVOutputComposite =	new GridData(SWT.FILL, SWT.BEGINNING, true, false);
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
		});*/
	}
	
	private void saveRobotData () {
		//Configuration of the saving png, xml, csv		
		GridData gdOutputGroup = new GridData(SWT.FILL, SWT.FILL, true, false);
		GridLayout glOutputGroup = new GridLayout(1, false);
		Group outputGroup = new Group(hintComposite, SWT.NORMAL);
		outputGroup.setText("Model Save:");
		outputGroup.setLayout(glOutputGroup);
		outputGroup.setLayoutData(gdOutputGroup);

		GridLayout glOutputTypeComposite = new GridLayout(1, false);
		glOutputTypeComposite.marginWidth = 0;
		Composite outputTypeComposite =	new Composite(outputGroup, SWT.NONE);
		outputTypeComposite.setLayout(glOutputTypeComposite);
		
		GridData gdLabelComposite2 = new GridData(SWT.BEGINNING, SWT.BEGINNING, true, true);
		Label splashLabel2 = new Label(outputTypeComposite, SWT.TOP);
		splashLabel2.setLayoutData(gdLabelComposite2);
		splashLabel2.setText("Use an output format to save the local \nagent models stepwise in a logging folder.\n");

		Button pngCheck = new Button(outputTypeComposite, SWT.CHECK);
		Button xmlCheck = new Button(outputTypeComposite, SWT.CHECK);
		Button csvCheck = new Button(outputTypeComposite, SWT.CHECK);
						
		pngCheck.setText("PNG Save");
		pngCheck.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				boolean boolValue = ((Button) e.widget).getSelection();
				gui.getWorkingConfiguration().pngSave = boolValue;
			}
		});
		//pngCheck.setSelection(true);
						
		xmlCheck.setText("XML Save");	
		xmlCheck.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				boolean boolValue = ((Button) e.widget).getSelection();
				gui.getWorkingConfiguration().xmlSave = boolValue;
			}
		});
				
		csvCheck.setText("CSV Save");		
		csvCheck.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(final SelectionEvent e) {
				boolean boolValue = ((Button) e.widget).getSelection();
				gui.getWorkingConfiguration().csvSave = boolValue;
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
		if (siafuIcon != null)
		{
			siafuIcon.dispose();
			siafuIcon=null;
		}
		if (noteIcon != null)
		{
			noteIcon.dispose();
			noteIcon=null;
		}
	}
}

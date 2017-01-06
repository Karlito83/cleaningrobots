package de.tud.swt.cleaningrobots.behaviours;

import java.io.File;
import java.util.Collection;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.measure.ExportFiles;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.model.World;

public class DumpCsvModelBehaviour extends Behaviour {
	
	private final int CONST_FILENAME_NUMBERPREFIX = 1000000000;
	private final int CONST_CSV_DUMP_INTERVAL = 1000;
	private final String CONST_PATH_DUMP_CSV = "dump/csv";
	private int counter;
	
	private State STATE_BLOCKED;	
	private State STATE_HOOVE;	
	private State STATE_WIPE;
	
	private int[][] mapValues; 

	public DumpCsvModelBehaviour(AgentRole role) {
		super(role);

		this.mapValues = new int[640][480];
		this.counter = 0;
	}

	@Override
	protected void addSupportedStates() {
		this.STATE_BLOCKED = agentCore.getConfiguration().createState("Blocked");		
		this.STATE_HOOVE = agentCore.getConfiguration().createState("Hoove");
		this.STATE_WIPE = agentCore.getConfiguration().createState("Wipe");		
	}

	@Override
	protected void addHardwareComponents() {
		//no hardware components needed...
	}

	@Override
	public boolean action() throws Exception {
		
		counter++;
		if (counter % CONST_CSV_DUMP_INTERVAL == 0 && counter > 0) {
			exportCSV();
		}		

		// Always returns false, so that the following behaviours will be
		// executed
		return false;
	}
	
	private void exportCSV() {
		if (createDirectory(CONST_PATH_DUMP_CSV)){
			String fileName = generateFilename("csv");
			
			try {
				
				World world = this.agentCore.getWorld();
				Collection<Field> fields = world.getFields();
								
				//iterates over all fields if has State make color
				for (Field field : fields){
					if (field.containsState(STATE_WIPE)){
						this.mapValues[field.getPos().getX()][field.getPos().getY()] = 4;
					} else if (field.containsState(STATE_HOOVE)){
						this.mapValues[field.getPos().getX()][field.getPos().getY()] = 3;
					} else if (field.containsState(STATE_BLOCKED)){
						this.mapValues[field.getPos().getX()][field.getPos().getY()] = 1;
					} else {
						this.mapValues[field.getPos().getX()][field.getPos().getY()] = 2;
					}
				}
				//write the output image in a file
				ExportFiles ef = new ExportFiles();
				String path = CONST_PATH_DUMP_CSV + File.separator + fileName;
				ef.addMatrixToFile(this.mapValues, path);
								
				//logger.info("created png " + outputFile.getAbsolutePath());
			} catch (Exception e) {
				//logger.error("Something went wrong while exporting to PNG");
			}			
		}
	}	
	
	/***
	 * 
	 * @param extension The extension for the file name to be generated. The "." character can be omitted.
	 * @return
	 */
	private String generateFilename(String extension) {
		String fileName = "";
		
		if (!extension.startsWith(".")){
			extension = "." + extension;
		}
		
		fileName += (CONST_FILENAME_NUMBERPREFIX + counter);
		fileName =  agentCore.getName() + "_" + fileName.substring(1) + extension;
		
		return fileName;
	}

	private boolean createDirectory(String path) {
		boolean result = false;
		File dir = new File(path);

		if (!dir.exists()) {
			try {
				result = dir.mkdirs();
			} catch (Exception e) {
				//logger.error(e);
			}
		} else {
			result = true;
		}
		return result;
	}

	@Override
	public void initialiseBehaviour() {
		//do not need to initialize something		
	}

}

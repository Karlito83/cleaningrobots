package de.tud.swt.cleaningrobots.behaviours;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collection;

import javax.imageio.ImageIO;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.model.World;

public class DumpPngModelBehaviour extends Behaviour {
	
	private final int CONST_FILENAME_NUMBERPREFIX = 1000000000;
	private final int CONST_PNG_DUMP_INTERVAL = 100;
	private final String CONST_PATH_DUMP_PNG = "dump/png";
	private int counter;
	
	private State STATE_BLOCKED;	
	private State STATE_HOOVE;	
	private State STATE_WIPE;

	public DumpPngModelBehaviour(AgentRole role) {
		super(role);
		
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
		if (counter % CONST_PNG_DUMP_INTERVAL == 0 && counter > 0) {
			exportPNG();
		}		

		// Always returns false, so that the following behaviours will be
		// executed
		return false;
	}
	
	private void exportPNG() {
		if (createDirectory(CONST_PATH_DUMP_PNG)){
			String fileName = generateFilename("png");
			
			try {
				
				World world = this.agentCore.getWorld();
				Collection<Field> fields = world.getFields();
								
				//Creates a gray scale image
				BufferedImage image = new BufferedImage(640, 480, BufferedImage.TYPE_3BYTE_BGR);//.TYPE_BYTE_GRAY);
				Graphics2D    graphics = image.createGraphics();

				//Creates the gray background of the hole image
				graphics.setPaint ( Color.GRAY );
				graphics.fillRect ( 0, 0, image.getWidth(), image.getHeight() );
								
				//iterates over all fields if has State make color
				for (Field field : fields){
					if (field.containsState(STATE_WIPE)){
						image.setRGB(field.getPos().getX(), field.getPos().getY(), Color.RED.getRGB());
					} else if (field.containsState(STATE_HOOVE)){
						image.setRGB(field.getPos().getX(), field.getPos().getY(), Color.BLUE.getRGB());
					} else if (field.containsState(STATE_BLOCKED)){
						image.setRGB(field.getPos().getX(), field.getPos().getY(), Color.BLACK.getRGB());
					} else {
						image.setRGB(field.getPos().getX(), field.getPos().getY(), Color.WHITE.getRGB());
					}
				}
				//write the output image in a file
				File outputFile = new File(CONST_PATH_DUMP_PNG + File.separator + fileName);
				ImageIO.write(image, "png", outputFile);
				
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

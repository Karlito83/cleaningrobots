package de.tud.swt.cleaningrobots.behaviours;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;

import cleaningrobots.CleaningrobotsFactory;
import cleaningrobots.WorldPart;
import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.util.EMFUtils;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;

/**
 * Behavior that creates a png and xml output of the actual world of the robot and use the EMF Model.
 * 
 * @author Christopher Werner
 *
 */
public class DumpModelBehaviour extends Behaviour {

	private final int CONST_FILENAME_NUMBERPREFIX = 1000000000;
	private final int CONST_PNG_DUMP_INTERVAL = 100;
	private final String CONST_PATH_DUMP_PNG = "dump/png";
	private final int CONST_XML_DUMP_INTERVAL = 10000;
	private final String CONST_PATH_DUMP_XML = "dump/xml";
	private int counter;

	public DumpModelBehaviour(AgentRole role) {
		super(role);
		
		this.counter = 0;
	}
	
	@Override
	protected void addSupportedStates() {
		//no states needed...		
	}

	@Override
	protected void addHardwareComponents() {
		//no hardware components needed...
	}

	@Override
	public boolean action() throws Exception {
		
		counter++;
		ImportExportConfiguration config = new ImportExportConfiguration();
		config.world = true;
		config.knownstates = true;
		EObject model = null;
		if (counter % CONST_PNG_DUMP_INTERVAL == 0 && counter > 0) {			
			model = agentCore.exportModel(config);
			exportPNG(model);
		}
		if (counter % CONST_XML_DUMP_INTERVAL == 0 && counter > 0){
			System.out.println("ExportXML");
			if (model == null){
				model = agentCore.exportModel(config);
			}
			exportXML(model);
			System.out.println("ExportXMLFinish");
		}
		

		// Always returns false, so that the following behaviours will be
		// executed
		return false;
	}
	
	private List<cleaningrobots.Field> getFieldsFromWorldModel(cleaningrobots.WorldPart worldPart) {
		
		List<cleaningrobots.Field> result = new LinkedList<cleaningrobots.Field>();
		
		if (worldPart instanceof cleaningrobots.Map) {
			result.addAll(((cleaningrobots.Map) worldPart).getFields());
		}
		if (worldPart instanceof cleaningrobots.World) {
			for (WorldPart innerWorldPart : ((cleaningrobots.World) worldPart)
					.getChildren()) {
				result.addAll(getFieldsFromWorldModel(innerWorldPart));
			}
		}
		
		return result;
	}

	private void exportPNG(EObject model) {
		if (createDirectory(CONST_PATH_DUMP_PNG)){
			String fileName = generateFilename("png");
			
			try {
				
				cleaningrobots.Robot robot = (cleaningrobots.Robot)model;
				WorldPart world = robot.getWorld();
				List<cleaningrobots.Field> fields = getFieldsFromWorldModel(world);
				
				//Creates a gray scale image
				//BufferedImage image = new BufferedImage(640, 480, BufferedImage.TYPE_3BYTE_BGR);//.TYPE_BYTE_GRAY);
				BufferedImage image = new BufferedImage(310, 274, BufferedImage.TYPE_3BYTE_BGR);//.TYPE_BYTE_GRAY);
				Graphics2D    graphics = image.createGraphics();

				//Creates the gray background of the hole image
				graphics.setPaint ( Color.GRAY );
				graphics.fillRect ( 0, 0, image.getWidth(), image.getHeight() );
				cleaningrobots.State blockedState = CleaningrobotsFactory.eINSTANCE.createState();
				blockedState.setName("Blocked");
				
				cleaningrobots.State hooveState = CleaningrobotsFactory.eINSTANCE.createState();
				hooveState.setName("Hoove");
				
				cleaningrobots.State wipeState = CleaningrobotsFactory.eINSTANCE.createState();
				wipeState.setName("Wipe");
				
				//iterates over all fields if has State make color
				for (cleaningrobots.Field field : fields){
					if (EMFUtils.listContains(field.getStates(), wipeState)){
						image.setRGB(field.getPos().getXpos(), field.getPos().getYpos(), Color.RED.getRGB());
					} else if (EMFUtils.listContains(field.getStates(), hooveState)){
						image.setRGB(field.getPos().getXpos(), field.getPos().getYpos(), Color.BLUE.getRGB());
					} else if (EMFUtils.listContains(field.getStates(), blockedState)){
						image.setRGB(field.getPos().getXpos(), field.getPos().getYpos(), Color.BLACK.getRGB());
					} else {
						image.setRGB(field.getPos().getXpos(), field.getPos().getYpos(), Color.WHITE.getRGB());
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

	private void exportXML(EObject model) {
		if (createDirectory(CONST_PATH_DUMP_XML)){
			String fileName = generateFilename("xml"); 
			
			try{
				ResourceSet rs = new ResourceSetImpl();
				Resource res = createAndAddResource(CONST_PATH_DUMP_XML + File.separator + fileName, "xml", rs);
				res.getContents().add(model);
				java.util.Map<Object,Object> saveOptions = ((XMLResource)res).getDefaultSaveOptions();
			    saveOptions.put(XMLResource.OPTION_CONFIGURATION_CACHE, Boolean.TRUE);
			    saveOptions.put(XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE, new ArrayList<>());
			    try {
			    	res.save(saveOptions);
			    } catch (IOException e) {
			    	throw new RuntimeException(e);
			    }
			} catch (Exception ex) {
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

	private Resource createAndAddResource(String outputFile, String ext,
			ResourceSet rs) {
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(ext, new XMLResourceFactoryImpl());
		URI uri = URI.createFileURI(outputFile);
		//logger.debug(uri);
		Resource resource = rs.createResource(uri);
		((ResourceImpl) resource).setIntrinsicIDToEObjectMap(new HashMap<String, EObject>());
		return resource;

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
		// TODO Auto-generated method stub
		
	}
}


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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
import de.tud.swt.cleaningrobots.Field;
import de.tud.swt.cleaningrobots.Robot;
import de.tud.swt.cleaningrobots.State;

public class DumpModelBehaviour extends Behaviour {

	private static final int CONST_FILENAME_NUMBERPREFIX = 1000000000;
	private static final int CONST_DUMP_INTERVAL = 100;
	private static final String CONST_PATH_DUMP_XML = "dump/xml";
	private static final String CONST_PATH_DUMP_PNG = "dump/png";

	private int counter;
	private final Logger logger = LogManager.getRootLogger();

	public DumpModelBehaviour(Robot robot) {
		super(robot);
		counter = 0;
	}

	@Override
	public boolean action() throws Exception {

		logger.trace("Enter DumpModelBehaviour.action() " + (counter % CONST_DUMP_INTERVAL + 1)  + "/" + CONST_DUMP_INTERVAL);
		counter++;
		if (counter % CONST_DUMP_INTERVAL == 0 && counter > 0) {
			EObject model = getRobot().exportModel();

			exportXML(model);
			exportPNG(model);
		}

		// Always returns false, so that the following behaviours will be
		// executed
		return false;
	}
	
	private List<Field> getFieldsFromWorldModel(cleaningrobots.WorldPart worldPart) {
		
		List<Field> result = new LinkedList<Field>();
		
		// Maybe an arrayList is better here?
		if (worldPart instanceof cleaningrobots.Map) {
			cleaningrobots.State blockedState = CleaningrobotsFactory.eINSTANCE
					.createState();
			blockedState.setName("Blocked");
			for (cleaningrobots.Field modelField : ((cleaningrobots.Map) worldPart)
					.getFields()) {
				Field f = new Field(modelField.getXpos(), modelField.getYpos(), !modelField
						.getStates().contains(blockedState));
				for (cleaningrobots.State modelState : modelField.getStates()) {
					State state = State.createState(modelState.getName());
					f.addState(state);
				}
				result.add(f);
			}
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
				
				if (model instanceof cleaningrobots.Robot) {
					WorldPart world = ((cleaningrobots.Robot) model).getWorld();
					/*
					world.getXdim();
					world.getYdim();
					*/
				} else {
					throw new Exception("The model " + model + " is unknown!");
				}
				cleaningrobots.Robot robot = (cleaningrobots.Robot)model;
				WorldPart world = robot.getWorld();
				List<Field> fields = getFieldsFromWorldModel(world);
				
				
				BufferedImage image = new BufferedImage(640, 480, BufferedImage.TYPE_BYTE_GRAY);
				Graphics2D    graphics = image.createGraphics();

				graphics.setPaint ( Color.GRAY );
				graphics.fillRect ( 0, 0, image.getWidth(), image.getHeight() );
				cleaningrobots.State blockedState = CleaningrobotsFactory.eINSTANCE.createState();
				blockedState.setName("Blocked");
				for (Field field : fields){
					if (field.getStates().contains(blockedState)){
						image.setRGB(field.getX(), field.getY(), Color.BLACK.getRGB());
					} else {
						image.setRGB(field.getX(), field.getY(), Color.WHITE.getRGB());
					}
				}
				File outputFile = new File(CONST_PATH_DUMP_PNG + File.separator + fileName);
				ImageIO.write(image, "png", outputFile);
				
				logger.info("created png " + outputFile.getAbsolutePath());
			} catch (Exception e) {
				logger.error("Something went wrong while exporting to PNG");
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
			     logger.info("created xml " + fileName);
			} catch (Exception ex) {
				logger.error("Something went wrong while exporting to XML", ex);
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
		fileName =  getRobot().getName() + "_" + fileName.substring(1) + extension;
		
		return fileName;
	}

	private Resource createAndAddResource(String outputFile, String ext,
			ResourceSet rs) {
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(ext, new XMLResourceFactoryImpl());
		URI uri = URI.createFileURI(outputFile);
		logger.debug(uri);
		Resource resource = rs.createResource(uri);
		((ResourceImpl) resource).setIntrinsicIDToEObjectMap(new HashMap());
		return resource;

	}

	private boolean createDirectory(String path) {
		boolean result = false;
		File dir = new File(path);

		if (!dir.exists()) {
			logger.info("creating directory: " + path);

			try {
				result = dir.mkdirs();
			} catch (Exception e) {
				logger.error(e);
			}
			if (result) {
				logger.info("created directory " + dir.getAbsolutePath());
			} else {
				logger.error("error while creating directory " + dir.getAbsolutePath());
			}
		} else {
			result = true;
		}
		return result;
	}
}


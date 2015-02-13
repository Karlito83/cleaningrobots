package de.tud.swt.cleaningrobots.behaviours;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

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

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.Robot;

public class DumpModelBehaviour extends Behaviour {

	private static final int CONST_FILENAME_NUMBERPREFIX = 1000000000;
	private static final int CONST_DUMP_INTERVAL = 1000;
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

		logger.trace("Enter DumpModelBehaviour.action()");
		
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

	private void exportPNG(EObject model) {
		if (createDirectory(CONST_PATH_DUMP_PNG)){
			String fileName = "";
			
			
			
			logger.info("created png " + fileName);
		}
	}

	private void exportXML(EObject model) {
		if (createDirectory(CONST_PATH_DUMP_PNG)){
			String fileName = generateFilename("png"); 
			
			ResourceSet rs = new ResourceSetImpl();
			Resource res = createAndAddResource("output/" + fileName, "cleaningrobots", rs);
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
				dir.mkdir();
				result = true;
			} catch (Exception e) {
				logger.error(e);
			}
			if (result) {
				logger.info("created directory " + dir.getAbsolutePath());
			}
		} else {
			result = true;
		}
		return result;
	}
}

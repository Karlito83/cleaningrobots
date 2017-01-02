package de.tud.swt.cleaningrobots.behaviours;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.RobotCore;
import de.tud.swt.cleaningrobots.util.ImportExportConfiguration;

public class DumpXMLModelBehaviour extends Behaviour {
	
	private final int CONST_FILENAME_NUMBERPREFIX = 1000000000;
	private final int CONST_XML_DUMP_INTERVAL = 10000;
	private final String CONST_PATH_DUMP_XML = "dump/xml";
	private int counter;

	public DumpXMLModelBehaviour(RobotCore robot) {
		super(robot);
		
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
		EObject model = null;
		if (counter % CONST_XML_DUMP_INTERVAL == 0 && counter > 0){
			System.out.println("ExportXML");
			if (model == null){
				ImportExportConfiguration config = new ImportExportConfiguration();
				config.world = true;
				config.knownstates = true;
				model = robot.exportModel(config);
			}
			exportXML(model);
			System.out.println("ExportXMLFinish");
		}
		

		// Always returns false, so that the following behaviours will be
		// executed
		return false;
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
		fileName =  robot.getName() + "_" + fileName.substring(1) + extension;
		
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
		//nothing to do here		
	}
}

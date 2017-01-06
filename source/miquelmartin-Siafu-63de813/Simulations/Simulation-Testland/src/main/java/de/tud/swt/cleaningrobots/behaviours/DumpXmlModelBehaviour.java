package de.tud.swt.cleaningrobots.behaviours;

import java.io.File;
import java.io.FileWriter;
import java.util.Collection;
import org.jdom2.Attribute;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.output.Format;
import org.jdom2.output.XMLOutputter;

import de.tud.swt.cleaningrobots.Behaviour;
import de.tud.swt.cleaningrobots.AgentRole;
import de.tud.swt.cleaningrobots.model.Field;
import de.tud.swt.cleaningrobots.model.State;
import de.tud.swt.cleaningrobots.model.World;

public class DumpXmlModelBehaviour extends Behaviour {
	
	private final int CONST_FILENAME_NUMBERPREFIX = 1000000000;
	private final int CONST_XML_DUMP_INTERVAL = 1000;
	private final String CONST_PATH_DUMP_XML = "dump/xml";
	private int counter;
	
	private State STATE_BLOCKED;	
	private State STATE_HOOVE;	
	private State STATE_WIPE;
	private State STATE_FREE;

	public DumpXmlModelBehaviour(AgentRole role) {
		super(role);
		
		this.counter = 0;
	}

	@Override
	protected void addSupportedStates() {
		this.STATE_BLOCKED = agentCore.getConfiguration().createState("Blocked");		
		this.STATE_HOOVE = agentCore.getConfiguration().createState("Hoove");
		this.STATE_WIPE = agentCore.getConfiguration().createState("Wipe");	
		this.STATE_FREE = agentCore.getConfiguration().createState("Free");
	}

	@Override
	protected void addHardwareComponents() {
		//no hardware components needed...
	}

	@Override
	public boolean action() throws Exception {
		
		counter++;
		if (counter % CONST_XML_DUMP_INTERVAL == 0 && counter > 0){
			//System.out.println("ExportXML");
			exportXML();
			//System.out.println("ExportXMLFinish");
		}		

		// Always returns false, so that the following behaviours will be
		// executed
		return false;
	}	

	private void exportXML() {
		if (createDirectory(CONST_PATH_DUMP_XML)){
			String fileName = generateFilename("xml");
			
			try {
				
				World world = this.agentCore.getWorld();
				Collection<Field> fields = world.getFields();
				
				// returns an xml element object
				// school is passed to make it root element in document
				Element model = new Element("model");

				// created an document object, all elements will be added to it
				// passes school as parameter to make it root element of document
				Document document = new Document(model);

				// created other element to add to document
				Element robot = new Element("Agent").setText(this.agentCore.getName());
				Element iteration = new Element("Iteration").setText(this.agentCore.getConfiguration().getWc().iteration + "");
				
				// adding child attribute to student element
				Element fieldsElement = new Element("Fields");
				
				//iterates over all fields if has State make color
				for (Field field : fields){
					
					Element fieldElement = new Element("Field");
					fieldElement.setAttribute(new Attribute("x", field.getPos().getX() + ""));
					fieldElement.setAttribute(new Attribute("y", field.getPos().getY() + ""));
					//fieldElement.addContent("state");
					
					if (field.containsState(STATE_WIPE)){
						fieldElement.addContent(STATE_WIPE.getName());
					} else if (field.containsState(STATE_HOOVE)){
						fieldElement.addContent(STATE_HOOVE.getName());
					} else if (field.containsState(STATE_BLOCKED)){
						fieldElement.addContent(STATE_BLOCKED.getName());
					} else {
						fieldElement.addContent(STATE_FREE.getName());
					}					

					fieldsElement.addContent(fieldElement);
				}				
				
				// get root element and added student element as a child of it
				document.getRootElement().addContent(robot);
				document.getRootElement().addContent(iteration);
				document.getRootElement().addContent(fieldsElement);

				// get object to see output of prepared document
				XMLOutputter xmlOutput = new XMLOutputter();

				// passsed System.out to see document content on console
				//xmlOutput.output(document, System.out);

				// passed fileWriter to write content in specified file
				xmlOutput.setFormat(Format.getPrettyFormat());
				xmlOutput.output(document, new FileWriter(CONST_PATH_DUMP_XML + File.separator + fileName));
				
				//logger.info("created xml " + outputFile.getAbsolutePath());
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
		//nothing to do here		
	}
}

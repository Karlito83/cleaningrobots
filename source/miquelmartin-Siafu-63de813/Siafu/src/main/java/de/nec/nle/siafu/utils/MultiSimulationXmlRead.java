package de.nec.nle.siafu.utils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;

import de.tud.evaluation.WorkingConfiguration;

public class MultiSimulationXmlRead {
	
	private String simulationPath;
	private int threadCount;

	public Queue<WorkingConfiguration> readWorkingConfigurations (String xmlPath)
	{
		Document doc = null;
		//"../Simulations/Simulation-Testland/Configuration/SimulationInformation.xml"
        File f = new File(xmlPath);
        
        Queue<WorkingConfiguration> workings = new LinkedList<WorkingConfiguration>();
                
        try {
            //create the document
            SAXBuilder builder = new SAXBuilder();
            doc = builder.build(f);

            // Element HardwareComponents
            Element element = doc.getRootElement();
            
            //get the overall Configuration Element
            Element configs = element.getChild("Configurations");
            
            //list of all working configurations
            List<Element> xmlAllComponents = configs.getChildren();
            
            //Path element content and threadCount element content
            this.simulationPath = element.getChildText("Path");
            this.threadCount = Integer.parseInt(element.getChildText("ThreadCount"));

            for (Element e : xmlAllComponents)
            {
            	int numberExplore = Integer.parseInt(e.getChildText("NumberExploreAgents"));
            	int numberHoove = Integer.parseInt(e.getChildText("NumberHooveAgents"));
            	int numberWipe = Integer.parseInt(e.getChildText("NumberWipeAgents"));
            	int map = Integer.parseInt(e.getChildText("Map"));
            	int strategy = Integer.parseInt(e.getChildText("Strategy"));
            	int runs = Integer.parseInt(e.getChildText("Runs"));
            	
            	for (int i = 0; i < runs; i++)
            	{
            		WorkingConfiguration wc = new WorkingConfiguration(numberExplore, numberHoove, numberWipe, i, strategy, 0, map);
            		workings.add(wc);
            	}           	
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return workings;
	}
	
	public int getThreadCount () {
		return this.threadCount;
	}
	
	public String getSimulationPath () {
		return this.simulationPath;
	}

	/*public static void main(String[] args) {
		MultiSimulationXmlRead r = new MultiSimulationXmlRead();
		List<WorkingConfiguration> list = r.readWorkingConfigurations();
		
		for (WorkingConfiguration wc : list)
		{
			System.out.println("WC: " + wc.toString());
		}
	}*/
}

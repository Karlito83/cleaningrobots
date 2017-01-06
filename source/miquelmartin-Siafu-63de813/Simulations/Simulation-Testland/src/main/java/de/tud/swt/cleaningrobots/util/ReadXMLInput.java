package de.tud.swt.cleaningrobots.util;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;
import org.jdom2.input.SAXBuilder;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;
import de.tud.swt.cleaningrobots.hardware.SpecialHardware;

public class ReadXMLInput {
	
	public Map<String, SpecialHardware> readHardwareComponents ()
	{
		Document doc = null;
        File f = new File("../Simulations/Simulation-Testland/Configuration/hardware.xml");
        
        Map<String, SpecialHardware> componentMap = new HashMap<String, SpecialHardware>();

        try {
            // Das Dokument erstellen
            SAXBuilder builder = new SAXBuilder();
            doc = builder.build(f);

            // Element HardwareComponents
            Element element = doc.getRootElement();

            // Eine Liste aller direkten Kindelemente eines Elementes erstellen
            List<Element> xmlAllComponents = element.getChildren();

            for (Element e : xmlAllComponents)
            {
            	SpecialHardware sh;
            	
            	Element energy = e.getChild("Energyvalues");
            	
            	String on = energy.getChildText("OnEnergy");
            	String off = energy.getChildText("OffEnergy");
            	String out = energy.getChildText("OutEnergy");
            	String work = energy.getChildText("WorkEnergy");
            	
            	double dOn = Double.parseDouble(on);
            	double dOff = Double.parseDouble(off);
            	double dOut = Double.parseDouble(out);
            	double dWork = Double.parseDouble(work);
            	
            	dOn = HardwareComponent.caluculateEnergie(dOn);
            	dOff = HardwareComponent.caluculateEnergie(dOff);
            	dOut = HardwareComponent.caluculateEnergie(dOut);
            	dWork = HardwareComponent.caluculateEnergie(dWork);
            	
            	System.out.println(on + off + out+ work);
            	
            	String name = e.getChildText("Name");
            	String comp = e.getChildText("ComponentType");
            	
            	Element val = e.getChild("Extravalues");
            	
            	Map<String, Object> valuesMap = new HashMap<String,Object>();
            	
            	List<Element> values = val.getChildren();
            	
            	
            	for (Element extra : values)
            	{
            		String eName = extra.getChildText("Name");
            		String eType = extra.getChildText("Type");
            		String eValue = extra.getChildText("Value");
            		String eUnity = extra.getChildText("Unity");
            		
            		if (eType.toLowerCase().equals("int"))
            		{
            			valuesMap.put(eName, Integer.parseInt(eValue));
            		}
            		else if (eType.toLowerCase().equals("double"))
            		{
            			if (eUnity.equals("W/h"))
            			{
            				double dValue = Double.parseDouble(eValue);
            				dValue = HardwareComponent.caluculateEnergie(dValue);
            				valuesMap.put(eName, dValue);
            			}
            			else
            			{
            				valuesMap.put(eName, Double.parseDouble(eValue));
            			}
            		}
            		else if (eType.toLowerCase().equals("float"))
            		{
            			valuesMap.put(eName, Float.parseFloat(eValue));
            		}
            		else
            		{
            			valuesMap.put(eName, eValue);
            		}
            		System.out.println(eName + eType + eValue + eUnity);
            	}          
            	sh = new SpecialHardware(name, dOn, dOff, dOut, dWork, comp, valuesMap);
            	
            	componentMap.put(name, sh);
            }
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return componentMap;
	}

	/*public static void main(String[] args) {
		ReadXMLInput r = new ReadXMLInput();
		r.readHardwareComponents();
	}*/
}

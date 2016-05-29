package de.tud.swt.cleaningrobots;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;

/**
 * Contains the hardware components a behavior need to run.
 * 
 * @author Christopher Werner
 *
 */
public class Demand {

	private Map<Components, Integer> hardware;
	private RobotCore robot;
	private List<HardwareComponent> hcList;
	
	public Demand (RobotCore robotCore)
	{
		this.robot = robotCore;	
		this.hardware = new EnumMap<Components, Integer> (Components.class);
		this.hcList = new ArrayList<HardwareComponent>();
	}
	
	public void addDemandPair (Components comp, int value)
	{
		hardware.put(comp, value);
		for (HardwareComponent hc : robot.getHardwarecomponents()) {
			//add if it isn't in
			if (comp == hc.getComponents() && !hcList.contains(hc))
			{
				hcList.add(hc);
			}
		}
	}
	
	public HardwareComponent getHardwareComponent (Components comp)
	{
		for (HardwareComponent hc : this.hcList) {
			if (hc.getComponents() == comp)
			{
				return hc;
			}
		}
		return null;
	}
	
	public void switchAllOn ()
	{
		for (HardwareComponent hc : this.hcList)
		{
			hc.switchOn();
		}
	}
	
	public void switchAllOff ()
	{
		for (HardwareComponent hc : this.hcList)
		{
			hc.switchOff();
		}
	}

	public boolean isCorrect() {
		//TODO: Anzahl der Hardwarecomponenten prüfen
		/*List<HardwareComponent> hcList = new ArrayList<HardwareComponent>();
		for (HardwareComponent hc : robot.getHardwarecomponents()) {
			if (hardware.containsKey(hc.getComponents()))
			{
				hcList.add(hc);
			}
		}
		Map<Components, Integer> hardwareCopy = new EnumMap<Components, Integer> (Components.class);
		hardwareCopy.putAll(hardware);*/
		if (hcList.size() == hardware.size())
			return true;
		else
			return false;
	}
	
	
}

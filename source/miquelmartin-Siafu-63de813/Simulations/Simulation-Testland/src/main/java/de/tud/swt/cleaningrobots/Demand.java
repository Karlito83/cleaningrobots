package de.tud.swt.cleaningrobots;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import de.tud.swt.cleaningrobots.hardware.Components;
import de.tud.swt.cleaningrobots.hardware.HardwareComponent;

public class Demand {

	private List<HardwareComponent> hcs;
	private Map<Components, Integer> hardware;
	private boolean correct;
	
	public Demand (Map<Components, Integer> enumMap, RobotCore r) 
	{
		//TODO: Anzahl der Hardwarecomponenten prüfen
		
		correct = false;
		
		hardware = enumMap;
		hcs = new ArrayList<HardwareComponent>();
		
		for (HardwareComponent robothc : r.getHardwarecomponents()) {
			if (hardware.containsKey(robothc.getComponents()))
			{
				hcs.add(robothc);
			}
		}
		
		if (hcs.size() == hardware.size())
			correct = true;
		else
			correct = false;		
	}

	public Map<Components, Integer> getHardware() {
		return hardware;
	}

	public List<HardwareComponent> getHcs() {
		return hcs;
	}

	public boolean isCorrect() {
		return correct;
	}
	
	
}

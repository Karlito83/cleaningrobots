package de.tud.swt.cleaningrobots.hardware;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class SpecialHardware extends HardwareComponent {
	
	private Map<String, Object> extraValues;
	private String compType;
		
	public SpecialHardware (String name, double on, double off, double out, double work, String compType, Map<String,Object> values)
	{
		super (name);
		this.compType = compType;
		this.onEnergie = on;
		this.offEnergie = off;
		this.outEnergie = out;
		this.workEnergie = work;
		this.extraValues = values;
	}
	
	public Object getValue (String key)
	{
		return extraValues.get(key);
	}

	@Override
	public ComponentTypes getComponentType() {		
		return ComponentTypes.valueOf(compType);
	}

	public SpecialHardware copy ()
	{
		Map<String, Object> values = new HashMap<String,Object>();
		
		for (Entry<String,Object> e : this.extraValues.entrySet())
		{
			values.put(e.getKey(), e.getValue());
		}
		return new SpecialHardware(name, onEnergie, offEnergie, outEnergie, workEnergie, compType, values);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof SpecialHardware)
		{
			SpecialHardware s = (SpecialHardware) obj;
			if (s.getName().equals(this.getName()))
				return true;
		}
		return false;
	}
}

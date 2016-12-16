package de.tud.swt.cleaningrobots.exceptions;

public class HardwareComponentExistenzException extends Exception {
	
	public HardwareComponentExistenzException ()
	{
		super("The Hardware Component must be defined in the XML!");
	}

}

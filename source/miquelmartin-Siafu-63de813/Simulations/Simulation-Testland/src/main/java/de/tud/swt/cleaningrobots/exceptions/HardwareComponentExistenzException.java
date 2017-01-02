package de.tud.swt.cleaningrobots.exceptions;

public class HardwareComponentExistenzException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1031935982967382991L;

	public HardwareComponentExistenzException ()
	{
		super("The Hardware Component must be defined in the XML!");
	}

}

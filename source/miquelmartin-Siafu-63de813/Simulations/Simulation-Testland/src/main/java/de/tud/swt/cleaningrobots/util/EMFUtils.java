package de.tud.swt.cleaningrobots.util;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

public class EMFUtils {

	/**
	 * Proofs if a list contains an object.
	 * @param list
	 * @param compareToElement
	 * @return
	 */
	public static boolean listContains(EList<?> list, EObject compareToElement) {
		boolean result = false;
		if (list != null) {
			for (Object element : list) {
				if (element.equals(compareToElement)) {
					result = true;
					break;
				}
			}
		}
		return result;
	}

}

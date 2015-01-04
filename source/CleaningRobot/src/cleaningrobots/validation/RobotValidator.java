/**
 *
 * $Id$
 */
package cleaningrobots.validation;

import cleaningrobots.Map;
import cleaningrobots.State;

import org.eclipse.emf.common.util.EList;

/**
 * A sample validator interface for {@link cleaningrobots.Robot}.
 * This doesn't really do anything, and it's not a real EMF artifact.
 * It was generated by the org.eclipse.emf.examples.generator.validator plug-in to illustrate how EMF's code generator can be extended.
 * This can be disabled with -vmargs -Dorg.eclipse.emf.examples.generator.validator=false.
 */
public interface RobotValidator {
	boolean validate();

	boolean validateName(String value);
	boolean validateMap(Map value);
	boolean validateKnownStates(EList<State> value);
}

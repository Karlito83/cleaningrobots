/**
 */
package cleaningrobots;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>World</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cleaningrobots.World#getChildren <em>Children</em>}</li>
 * </ul>
 * </p>
 *
 * @see cleaningrobots.CleaningrobotsPackage#getWorld()
 * @model
 * @generated
 */
public interface World extends WorldPart {
	/**
	 * Returns the value of the '<em><b>Children</b></em>' containment reference list.
	 * The list contents are of type {@link cleaningrobots.WorldPart}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Children</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Children</em>' containment reference list.
	 * @see cleaningrobots.CleaningrobotsPackage#getWorld_Children()
	 * @model containment="true"
	 * @generated
	 */
	EList<WorldPart> getChildren();

} // World

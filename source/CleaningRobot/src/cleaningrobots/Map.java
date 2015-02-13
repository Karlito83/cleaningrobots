/**
 */
package cleaningrobots;

import org.eclipse.emf.common.util.EList;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Map</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cleaningrobots.Map#getFields <em>Fields</em>}</li>
 * </ul>
 * </p>
 *
 * @see cleaningrobots.CleaningrobotsPackage#getMap()
 * @model
 * @generated
 */
public interface Map extends WorldPart {

	/**
	 * Returns the value of the '<em><b>Fields</b></em>' containment reference list.
	 * The list contents are of type {@link cleaningrobots.Field}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Fields</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Fields</em>' containment reference list.
	 * @see cleaningrobots.CleaningrobotsPackage#getMap_Fields()
	 * @model containment="true"
	 * @generated
	 */
	EList<Field> getFields();

} // Map

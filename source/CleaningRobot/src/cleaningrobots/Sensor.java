/**
 */
package cleaningrobots;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Sensor</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cleaningrobots.Sensor#getKnownStates <em>Known States</em>}</li>
 * </ul>
 * </p>
 *
 * @see cleaningrobots.CleaningrobotsPackage#getSensor()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Sensor extends EObject {
	/**
	 * Returns the value of the '<em><b>Known States</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Known States</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Known States</em>' containment reference.
	 * @see #setKnownStates(State)
	 * @see cleaningrobots.CleaningrobotsPackage#getSensor_KnownStates()
	 * @model containment="true"
	 * @generated
	 */
	State getKnownStates();

	/**
	 * Sets the value of the '{@link cleaningrobots.Sensor#getKnownStates <em>Known States</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Known States</em>' containment reference.
	 * @see #getKnownStates()
	 * @generated
	 */
	void setKnownStates(State value);

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<Field> getData();

} // Sensor

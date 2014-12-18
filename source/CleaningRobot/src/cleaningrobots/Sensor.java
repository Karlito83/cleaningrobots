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
 *
 * @see cleaningrobots.CleaningrobotsPackage#getSensor()
 * @model interface="true" abstract="true"
 * @generated
 */
public interface Sensor extends EObject {
	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @model kind="operation"
	 * @generated
	 */
	EList<Field> getData();

} // Sensor

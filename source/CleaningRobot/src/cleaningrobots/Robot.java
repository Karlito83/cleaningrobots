/**
 */
package cleaningrobots;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Robot</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cleaningrobots.Robot#getName <em>Name</em>}</li>
 *   <li>{@link cleaningrobots.Robot#getKnownStates <em>Known States</em>}</li>
 *   <li>{@link cleaningrobots.Robot#getWorld <em>World</em>}</li>
 * </ul>
 * </p>
 *
 * @see cleaningrobots.CleaningrobotsPackage#getRobot()
 * @model
 * @generated
 */
public interface Robot extends EObject {
	/**
	 * Returns the value of the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Name</em>' attribute.
	 * @see #setName(String)
	 * @see cleaningrobots.CleaningrobotsPackage#getRobot_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link cleaningrobots.Robot#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

	/**
	 * Returns the value of the '<em><b>Known States</b></em>' containment reference list.
	 * The list contents are of type {@link cleaningrobots.State}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Known States</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Known States</em>' containment reference list.
	 * @see cleaningrobots.CleaningrobotsPackage#getRobot_KnownStates()
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<State> getKnownStates();

	/**
	 * Returns the value of the '<em><b>World</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>World</em>' containment reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>World</em>' containment reference.
	 * @see #setWorld(WorldPart)
	 * @see cleaningrobots.CleaningrobotsPackage#getRobot_World()
	 * @model containment="true"
	 * @generated
	 */
	WorldPart getWorld();

	/**
	 * Sets the value of the '{@link cleaningrobots.Robot#getWorld <em>World</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>World</em>' containment reference.
	 * @see #getWorld()
	 * @generated
	 */
	void setWorld(WorldPart value);

} // Robot

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
 *   <li>{@link cleaningrobots.Robot#getWorld <em>World</em>}</li>
 *   <li>{@link cleaningrobots.Robot#getDestination <em>Destination</em>}</li>
 *   <li>{@link cleaningrobots.Robot#getComponents <em>Components</em>}</li>
 *   <li>{@link cleaningrobots.Robot#getRoles <em>Roles</em>}</li>
 *   <li>{@link cleaningrobots.Robot#getKnownStates <em>Known States</em>}</li>
 *   <li>{@link cleaningrobots.Robot#getRobotKnowledge <em>Robot Knowledge</em>}</li>
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
	 * @model containment="true"
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

	/**
	 * Returns the value of the '<em><b>Destination</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Destination</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Destination</em>' reference.
	 * @see #setDestination(Position)
	 * @see cleaningrobots.CleaningrobotsPackage#getRobot_Destination()
	 * @model
	 * @generated
	 */
	Position getDestination();

	/**
	 * Sets the value of the '{@link cleaningrobots.Robot#getDestination <em>Destination</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Destination</em>' reference.
	 * @see #getDestination()
	 * @generated
	 */
	void setDestination(Position value);

	/**
	 * Returns the value of the '<em><b>Components</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Components</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Components</em>' attribute list.
	 * @see cleaningrobots.CleaningrobotsPackage#getRobot_Components()
	 * @model
	 * @generated
	 */
	EList<String> getComponents();

	/**
	 * Returns the value of the '<em><b>Roles</b></em>' reference list.
	 * The list contents are of type {@link cleaningrobots.Role}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Roles</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Roles</em>' reference list.
	 * @see cleaningrobots.CleaningrobotsPackage#getRobot_Roles()
	 * @model
	 * @generated
	 */
	EList<Role> getRoles();

	/**
	 * Returns the value of the '<em><b>Robot Knowledge</b></em>' reference list.
	 * The list contents are of type {@link cleaningrobots.RobotKnowledge}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Robot Knowledge</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Robot Knowledge</em>' reference list.
	 * @see cleaningrobots.CleaningrobotsPackage#getRobot_RobotKnowledge()
	 * @model
	 * @generated
	 */
	EList<RobotKnowledge> getRobotKnowledge();

} // Robot

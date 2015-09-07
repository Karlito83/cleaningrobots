/**
 */
package cleaningrobots;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Robot Knowledge</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cleaningrobots.RobotKnowledge#getName <em>Name</em>}</li>
 *   <li>{@link cleaningrobots.RobotKnowledge#getLastArrange <em>Last Arrange</em>}</li>
 *   <li>{@link cleaningrobots.RobotKnowledge#getDestination <em>Destination</em>}</li>
 *   <li>{@link cleaningrobots.RobotKnowledge#getComponents <em>Components</em>}</li>
 *   <li>{@link cleaningrobots.RobotKnowledge#getRoles <em>Roles</em>}</li>
 *   <li>{@link cleaningrobots.RobotKnowledge#getKnowStates <em>Know States</em>}</li>
 * </ul>
 * </p>
 *
 * @see cleaningrobots.CleaningrobotsPackage#getRobotKnowledge()
 * @model
 * @generated
 */
public interface RobotKnowledge extends EObject {
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
	 * @see cleaningrobots.CleaningrobotsPackage#getRobotKnowledge_Name()
	 * @model
	 * @generated
	 */
	String getName();

	/**
	 * Sets the value of the '{@link cleaningrobots.RobotKnowledge#getName <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Name</em>' attribute.
	 * @see #getName()
	 * @generated
	 */
	void setName(String value);

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
	 * @see cleaningrobots.CleaningrobotsPackage#getRobotKnowledge_Destination()
	 * @model
	 * @generated
	 */
	Position getDestination();

	/**
	 * Sets the value of the '{@link cleaningrobots.RobotKnowledge#getDestination <em>Destination</em>}' reference.
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
	 * @see cleaningrobots.CleaningrobotsPackage#getRobotKnowledge_Components()
	 * @model
	 * @generated
	 */
	EList<String> getComponents();

	/**
	 * Returns the value of the '<em><b>Last Arrange</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Last Arrange</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Last Arrange</em>' attribute.
	 * @see #setLastArrange(int)
	 * @see cleaningrobots.CleaningrobotsPackage#getRobotKnowledge_LastArrange()
	 * @model
	 * @generated
	 */
	int getLastArrange();

	/**
	 * Sets the value of the '{@link cleaningrobots.RobotKnowledge#getLastArrange <em>Last Arrange</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Last Arrange</em>' attribute.
	 * @see #getLastArrange()
	 * @generated
	 */
	void setLastArrange(int value);

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
	 * @see cleaningrobots.CleaningrobotsPackage#getRobotKnowledge_Roles()
	 * @model
	 * @generated
	 */
	EList<Role> getRoles();

	/**
	 * Returns the value of the '<em><b>Know States</b></em>' reference list.
	 * The list contents are of type {@link cleaningrobots.State}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Know States</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Know States</em>' reference list.
	 * @see cleaningrobots.CleaningrobotsPackage#getRobotKnowledge_KnowStates()
	 * @model
	 * @generated
	 */
	EList<State> getKnowStates();

} // RobotKnowledge

/**
 */
package cleaningrobots;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Position</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cleaningrobots.Position#getXpos <em>Xpos</em>}</li>
 *   <li>{@link cleaningrobots.Position#getYpos <em>Ypos</em>}</li>
 * </ul>
 * </p>
 *
 * @see cleaningrobots.CleaningrobotsPackage#getPosition()
 * @model
 * @generated
 */
public interface Position extends EObject {
	/**
	 * Returns the value of the '<em><b>Xpos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Xpos</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Xpos</em>' attribute.
	 * @see #setXpos(int)
	 * @see cleaningrobots.CleaningrobotsPackage#getPosition_Xpos()
	 * @model
	 * @generated
	 */
	int getXpos();

	/**
	 * Sets the value of the '{@link cleaningrobots.Position#getXpos <em>Xpos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xpos</em>' attribute.
	 * @see #getXpos()
	 * @generated
	 */
	void setXpos(int value);

	/**
	 * Returns the value of the '<em><b>Ypos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ypos</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ypos</em>' attribute.
	 * @see #setYpos(int)
	 * @see cleaningrobots.CleaningrobotsPackage#getPosition_Ypos()
	 * @model
	 * @generated
	 */
	int getYpos();

	/**
	 * Sets the value of the '{@link cleaningrobots.Position#getYpos <em>Ypos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ypos</em>' attribute.
	 * @see #getYpos()
	 * @generated
	 */
	void setYpos(int value);

} // Position

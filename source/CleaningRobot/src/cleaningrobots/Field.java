/**
 */
package cleaningrobots;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cleaningrobots.Field#getXpos <em>Xpos</em>}</li>
 *   <li>{@link cleaningrobots.Field#getYpos <em>Ypos</em>}</li>
 *   <li>{@link cleaningrobots.Field#getState <em>State</em>}</li>
 * </ul>
 * </p>
 *
 * @see cleaningrobots.CleaningrobotsPackage#getField()
 * @model
 * @generated
 */
public interface Field extends EObject {
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
	 * @see cleaningrobots.CleaningrobotsPackage#getField_Xpos()
	 * @model
	 * @generated
	 */
	int getXpos();

	/**
	 * Sets the value of the '{@link cleaningrobots.Field#getXpos <em>Xpos</em>}' attribute.
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
	 * @see cleaningrobots.CleaningrobotsPackage#getField_Ypos()
	 * @model
	 * @generated
	 */
	int getYpos();

	/**
	 * Sets the value of the '{@link cleaningrobots.Field#getYpos <em>Ypos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ypos</em>' attribute.
	 * @see #getYpos()
	 * @generated
	 */
	void setYpos(int value);

	/**
	 * Returns the value of the '<em><b>State</b></em>' reference list.
	 * The list contents are of type {@link cleaningrobots.State}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>State</em>' reference isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>State</em>' reference list.
	 * @see cleaningrobots.CleaningrobotsPackage#getField_State()
	 * @model required="true"
	 * @generated
	 */
	EList<State> getState();

} // Field

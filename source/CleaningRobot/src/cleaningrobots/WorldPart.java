/**
 */
package cleaningrobots;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>World Part</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cleaningrobots.WorldPart#getXDim <em>XDim</em>}</li>
 *   <li>{@link cleaningrobots.WorldPart#getYDim <em>YDim</em>}</li>
 * </ul>
 * </p>
 *
 * @see cleaningrobots.CleaningrobotsPackage#getWorldPart()
 * @model
 * @generated
 */
public interface WorldPart extends EObject {
	/**
	 * Returns the value of the '<em><b>XDim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>XDim</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>XDim</em>' attribute.
	 * @see #setXDim(int)
	 * @see cleaningrobots.CleaningrobotsPackage#getWorldPart_XDim()
	 * @model
	 * @generated
	 */
	int getXDim();

	/**
	 * Sets the value of the '{@link cleaningrobots.WorldPart#getXDim <em>XDim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>XDim</em>' attribute.
	 * @see #getXDim()
	 * @generated
	 */
	void setXDim(int value);

	/**
	 * Returns the value of the '<em><b>YDim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>YDim</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>YDim</em>' attribute.
	 * @see #setYDim(int)
	 * @see cleaningrobots.CleaningrobotsPackage#getWorldPart_YDim()
	 * @model
	 * @generated
	 */
	int getYDim();

	/**
	 * Sets the value of the '{@link cleaningrobots.WorldPart#getYDim <em>YDim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>YDim</em>' attribute.
	 * @see #getYDim()
	 * @generated
	 */
	void setYDim(int value);

} // WorldPart

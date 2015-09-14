/**
 */
package cleaningrobots;

import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>World Part</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cleaningrobots.WorldPart#getXdim <em>Xdim</em>}</li>
 *   <li>{@link cleaningrobots.WorldPart#getYdim <em>Ydim</em>}</li>
 *   <li>{@link cleaningrobots.WorldPart#getWorldStates <em>World States</em>}</li>
 * </ul>
 * </p>
 *
 * @see cleaningrobots.CleaningrobotsPackage#getWorldPart()
 * @model
 * @generated
 */
public interface WorldPart extends EObject {
	/**
	 * Returns the value of the '<em><b>Xdim</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Xdim</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Xdim</em>' attribute.
	 * @see #setXdim(int)
	 * @see cleaningrobots.CleaningrobotsPackage#getWorldPart_Xdim()
	 * @model default="0"
	 * @generated
	 */
	int getXdim();

	/**
	 * Sets the value of the '{@link cleaningrobots.WorldPart#getXdim <em>Xdim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xdim</em>' attribute.
	 * @see #getXdim()
	 * @generated
	 */
	void setXdim(int value);

	/**
	 * Returns the value of the '<em><b>Ydim</b></em>' attribute.
	 * The default value is <code>"0"</code>.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ydim</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ydim</em>' attribute.
	 * @see #setYdim(int)
	 * @see cleaningrobots.CleaningrobotsPackage#getWorldPart_Ydim()
	 * @model default="0"
	 * @generated
	 */
	int getYdim();

	/**
	 * Sets the value of the '{@link cleaningrobots.WorldPart#getYdim <em>Ydim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ydim</em>' attribute.
	 * @see #getYdim()
	 * @generated
	 */
	void setYdim(int value);

	/**
	 * Returns the value of the '<em><b>World States</b></em>' reference list.
	 * The list contents are of type {@link cleaningrobots.State}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>World States</em>' reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>World States</em>' reference list.
	 * @see cleaningrobots.CleaningrobotsPackage#getWorldPart_WorldStates()
	 * @model
	 * @generated
	 */
	EList<State> getWorldStates();

} // WorldPart

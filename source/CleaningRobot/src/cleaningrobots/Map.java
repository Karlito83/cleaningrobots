/**
 */
package cleaningrobots;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Map</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cleaningrobots.Map#getXdim <em>Xdim</em>}</li>
 *   <li>{@link cleaningrobots.Map#getYdim <em>Ydim</em>}</li>
 *   <li>{@link cleaningrobots.Map#getFields <em>Fields</em>}</li>
 *   <li>{@link cleaningrobots.Map#getSubMaps <em>Sub Maps</em>}</li>
 * </ul>
 * </p>
 *
 * @see cleaningrobots.CleaningrobotsPackage#getMap()
 * @model
 * @generated
 */
public interface Map extends EObject {
	/**
	 * Returns the value of the '<em><b>Xdim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Xdim</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Xdim</em>' attribute.
	 * @see #setXdim(int)
	 * @see cleaningrobots.CleaningrobotsPackage#getMap_Xdim()
	 * @model
	 * @generated
	 */
	int getXdim();

	/**
	 * Sets the value of the '{@link cleaningrobots.Map#getXdim <em>Xdim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Xdim</em>' attribute.
	 * @see #getXdim()
	 * @generated
	 */
	void setXdim(int value);

	/**
	 * Returns the value of the '<em><b>Ydim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Ydim</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Ydim</em>' attribute.
	 * @see #setYdim(int)
	 * @see cleaningrobots.CleaningrobotsPackage#getMap_Ydim()
	 * @model
	 * @generated
	 */
	int getYdim();

	/**
	 * Sets the value of the '{@link cleaningrobots.Map#getYdim <em>Ydim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Ydim</em>' attribute.
	 * @see #getYdim()
	 * @generated
	 */
	void setYdim(int value);

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
	 * @model containment="true" required="true"
	 * @generated
	 */
	EList<Field> getFields();

	/**
	 * Returns the value of the '<em><b>Sub Maps</b></em>' containment reference list.
	 * The list contents are of type {@link cleaningrobots.Map}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Sub Maps</em>' containment reference list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Sub Maps</em>' containment reference list.
	 * @see cleaningrobots.CleaningrobotsPackage#getMap_SubMaps()
	 * @model containment="true"
	 * @generated
	 */
	EList<Map> getSubMaps();

} // Map

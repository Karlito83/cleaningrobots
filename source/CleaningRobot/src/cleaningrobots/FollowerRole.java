/**
 */
package cleaningrobots;


/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Follower Role</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cleaningrobots.FollowerRole#getMasterName <em>Master Name</em>}</li>
 * </ul>
 * </p>
 *
 * @see cleaningrobots.CleaningrobotsPackage#getFollowerRole()
 * @model
 * @generated
 */
public interface FollowerRole extends Role {
	/**
	 * Returns the value of the '<em><b>Master Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Master Name</em>' attribute isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Master Name</em>' attribute.
	 * @see #setMasterName(String)
	 * @see cleaningrobots.CleaningrobotsPackage#getFollowerRole_MasterName()
	 * @model
	 * @generated
	 */
	String getMasterName();

	/**
	 * Sets the value of the '{@link cleaningrobots.FollowerRole#getMasterName <em>Master Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @param value the new value of the '<em>Master Name</em>' attribute.
	 * @see #getMasterName()
	 * @generated
	 */
	void setMasterName(String value);

} // FollowerRole

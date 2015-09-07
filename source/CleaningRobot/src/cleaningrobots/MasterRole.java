/**
 */
package cleaningrobots;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Master Role</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link cleaningrobots.MasterRole#getFollowerNames <em>Follower Names</em>}</li>
 * </ul>
 * </p>
 *
 * @see cleaningrobots.CleaningrobotsPackage#getMasterRole()
 * @model
 * @generated
 */
public interface MasterRole extends Role {
	/**
	 * Returns the value of the '<em><b>Follower Names</b></em>' attribute list.
	 * The list contents are of type {@link java.lang.String}.
	 * <!-- begin-user-doc -->
	 * <p>
	 * If the meaning of the '<em>Follower Names</em>' attribute list isn't clear,
	 * there really should be more of a description here...
	 * </p>
	 * <!-- end-user-doc -->
	 * @return the value of the '<em>Follower Names</em>' attribute list.
	 * @see cleaningrobots.CleaningrobotsPackage#getMasterRole_FollowerNames()
	 * @model
	 * @generated
	 */
	EList<String> getFollowerNames();

} // MasterRole

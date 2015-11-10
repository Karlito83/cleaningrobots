/**
 */
package cleaningrobots.impl;

import cleaningrobots.CleaningrobotsPackage;
import cleaningrobots.Position;
import cleaningrobots.RobotKnowledge;
import cleaningrobots.Role;

import cleaningrobots.State;
import java.util.Collection;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Robot Knowledge</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link cleaningrobots.impl.RobotKnowledgeImpl#getName <em>Name</em>}</li>
 *   <li>{@link cleaningrobots.impl.RobotKnowledgeImpl#getLastArrange <em>Last Arrange</em>}</li>
 *   <li>{@link cleaningrobots.impl.RobotKnowledgeImpl#getDestination <em>Destination</em>}</li>
 *   <li>{@link cleaningrobots.impl.RobotKnowledgeImpl#getComponents <em>Components</em>}</li>
 *   <li>{@link cleaningrobots.impl.RobotKnowledgeImpl#getRoles <em>Roles</em>}</li>
 *   <li>{@link cleaningrobots.impl.RobotKnowledgeImpl#getKnowStates <em>Know States</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RobotKnowledgeImpl extends MinimalEObjectImpl.Container implements RobotKnowledge {
	/**
	 * The default value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	//protected String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = null;

	/**
	 * The default value of the '{@link #getLastArrange() <em>Last Arrange</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastArrange()
	 * @generated
	 * @ordered
	 */
	//protected int LAST_ARRANGE_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getLastArrange() <em>Last Arrange</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getLastArrange()
	 * @generated
	 * @ordered
	 */
	protected int lastArrange = 0;

	/**
	 * The cached value of the '{@link #getDestination() <em>Destination</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getDestination()
	 * @generated
	 * @ordered
	 */
	protected Position destination;

	/**
	 * The cached value of the '{@link #getComponents() <em>Components</em>}' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getComponents()
	 * @generated
	 * @ordered
	 */
	protected EList<String> components;

	/**
	 * The cached value of the '{@link #getRoles() <em>Roles</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRoles()
	 * @generated
	 * @ordered
	 */
	protected EList<Role> roles;

	/**
	 * The cached value of the '{@link #getKnowStates() <em>Know States</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKnowStates()
	 * @generated
	 * @ordered
	 */
	protected EList<State> knowStates;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RobotKnowledgeImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CleaningrobotsPackage.Literals.ROBOT_KNOWLEDGE;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public String getName() {
		return name;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setName(String newName) {
		String oldName = name;
		name = newName;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CleaningrobotsPackage.ROBOT_KNOWLEDGE__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Position getDestination() {
		if (destination != null && destination.eIsProxy()) {
			InternalEObject oldDestination = (InternalEObject)destination;
			destination = (Position)eResolveProxy(oldDestination);
			if (destination != oldDestination) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CleaningrobotsPackage.ROBOT_KNOWLEDGE__DESTINATION, oldDestination, destination));
			}
		}
		return destination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Position basicGetDestination() {
		return destination;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setDestination(Position newDestination) {
		Position oldDestination = destination;
		destination = newDestination;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CleaningrobotsPackage.ROBOT_KNOWLEDGE__DESTINATION, oldDestination, destination));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getComponents() {
		if (components == null) {
			components = new EDataTypeUniqueEList<String>(String.class, this, CleaningrobotsPackage.ROBOT_KNOWLEDGE__COMPONENTS);
		}
		return components;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getLastArrange() {
		return lastArrange;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setLastArrange(int newLastArrange) {
		int oldLastArrange = lastArrange;
		lastArrange = newLastArrange;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CleaningrobotsPackage.ROBOT_KNOWLEDGE__LAST_ARRANGE, oldLastArrange, lastArrange));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Role> getRoles() {
		if (roles == null) {
			roles = new EObjectResolvingEList<Role>(Role.class, this, CleaningrobotsPackage.ROBOT_KNOWLEDGE__ROLES);
		}
		return roles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<State> getKnowStates() {
		if (knowStates == null) {
			knowStates = new EObjectResolvingEList<State>(State.class, this, CleaningrobotsPackage.ROBOT_KNOWLEDGE__KNOW_STATES);
		}
		return knowStates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__NAME:
				return getName();
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__LAST_ARRANGE:
				return getLastArrange();
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__DESTINATION:
				if (resolve) return getDestination();
				return basicGetDestination();
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__COMPONENTS:
				return getComponents();
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__ROLES:
				return getRoles();
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__KNOW_STATES:
				return getKnowStates();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__NAME:
				setName((String)newValue);
				return;
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__LAST_ARRANGE:
				setLastArrange((Integer)newValue);
				return;
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__DESTINATION:
				setDestination((Position)newValue);
				return;
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__COMPONENTS:
				getComponents().clear();
				getComponents().addAll((Collection<? extends String>)newValue);
				return;
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__ROLES:
				getRoles().clear();
				getRoles().addAll((Collection<? extends Role>)newValue);
				return;
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__KNOW_STATES:
				getKnowStates().clear();
				getKnowStates().addAll((Collection<? extends State>)newValue);
				return;
		}
		super.eSet(featureID, newValue);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eUnset(int featureID) {
		switch (featureID) {
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__NAME:
				setName(null);
				return;
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__LAST_ARRANGE:
				setLastArrange(0);
				return;
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__DESTINATION:
				setDestination((Position)null);
				return;
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__COMPONENTS:
				getComponents().clear();
				return;
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__ROLES:
				getRoles().clear();
				return;
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__KNOW_STATES:
				getKnowStates().clear();
				return;
		}
		super.eUnset(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public boolean eIsSet(int featureID) {
		switch (featureID) {
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__NAME:
				return name != null;
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__LAST_ARRANGE:
				return lastArrange != 0;
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__DESTINATION:
				return destination != null;
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__COMPONENTS:
				return components != null && !components.isEmpty();
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__ROLES:
				return roles != null && !roles.isEmpty();
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE__KNOW_STATES:
				return knowStates != null && !knowStates.isEmpty();
		}
		return super.eIsSet(featureID);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public String toString() {
		if (eIsProxy()) return super.toString();

		StringBuffer result = new StringBuffer(super.toString());
		result.append(" (name: ");
		result.append(name);
		result.append(", lastArrange: ");
		result.append(lastArrange);
		result.append(", components: ");
		result.append(components);
		result.append(')');
		return result.toString();
	}

} //RobotKnowledgeImpl

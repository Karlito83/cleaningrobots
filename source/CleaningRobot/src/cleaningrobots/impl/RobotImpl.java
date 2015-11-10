/**
 */
package cleaningrobots.impl;

import cleaningrobots.CleaningrobotsPackage;
import cleaningrobots.Position;
import cleaningrobots.Robot;
import cleaningrobots.RobotKnowledge;
import cleaningrobots.Role;
import cleaningrobots.State;
import cleaningrobots.WorldPart;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EDataTypeUniqueEList;
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Robot</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link cleaningrobots.impl.RobotImpl#getName <em>Name</em>}</li>
 *   <li>{@link cleaningrobots.impl.RobotImpl#getWorld <em>World</em>}</li>
 *   <li>{@link cleaningrobots.impl.RobotImpl#getDestination <em>Destination</em>}</li>
 *   <li>{@link cleaningrobots.impl.RobotImpl#getComponents <em>Components</em>}</li>
 *   <li>{@link cleaningrobots.impl.RobotImpl#getRoles <em>Roles</em>}</li>
 *   <li>{@link cleaningrobots.impl.RobotImpl#getKnownStates <em>Known States</em>}</li>
 *   <li>{@link cleaningrobots.impl.RobotImpl#getRobotKnowledge <em>Robot Knowledge</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RobotImpl extends MinimalEObjectImpl.Container implements Robot {
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
	 * The cached value of the '{@link #getWorld() <em>World</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorld()
	 * @generated
	 * @ordered
	 */
	protected WorldPart world;

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
	 * The cached value of the '{@link #getKnownStates() <em>Known States</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getKnownStates()
	 * @generated
	 * @ordered
	 */
	protected EList<State> knownStates;

	/**
	 * The cached value of the '{@link #getRobotKnowledge() <em>Robot Knowledge</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getRobotKnowledge()
	 * @generated
	 * @ordered
	 */
	protected EList<RobotKnowledge> robotKnowledge;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected RobotImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CleaningrobotsPackage.Literals.ROBOT;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CleaningrobotsPackage.ROBOT__NAME, oldName, name));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<State> getKnownStates() {
		if (knownStates == null) {
			knownStates = new EObjectContainmentEList<State>(State.class, this, CleaningrobotsPackage.ROBOT__KNOWN_STATES);
		}
		return knownStates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorldPart getWorld() {
		return world;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetWorld(WorldPart newWorld, NotificationChain msgs) {
		WorldPart oldWorld = world;
		world = newWorld;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CleaningrobotsPackage.ROBOT__WORLD, oldWorld, newWorld);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorld(WorldPart newWorld) {
		if (newWorld != world) {
			NotificationChain msgs = null;
			if (world != null)
				msgs = ((InternalEObject)world).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CleaningrobotsPackage.ROBOT__WORLD, null, msgs);
			if (newWorld != null)
				msgs = ((InternalEObject)newWorld).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CleaningrobotsPackage.ROBOT__WORLD, null, msgs);
			msgs = basicSetWorld(newWorld, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CleaningrobotsPackage.ROBOT__WORLD, newWorld, newWorld));
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
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CleaningrobotsPackage.ROBOT__DESTINATION, oldDestination, destination));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CleaningrobotsPackage.ROBOT__DESTINATION, oldDestination, destination));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<String> getComponents() {
		if (components == null) {
			components = new EDataTypeUniqueEList<String>(String.class, this, CleaningrobotsPackage.ROBOT__COMPONENTS);
		}
		return components;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Role> getRoles() {
		if (roles == null) {
			roles = new EObjectResolvingEList<Role>(Role.class, this, CleaningrobotsPackage.ROBOT__ROLES);
		}
		return roles;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<RobotKnowledge> getRobotKnowledge() {
		if (robotKnowledge == null) {
			robotKnowledge = new EObjectResolvingEList<RobotKnowledge>(RobotKnowledge.class, this, CleaningrobotsPackage.ROBOT__ROBOT_KNOWLEDGE);
		}
		return robotKnowledge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CleaningrobotsPackage.ROBOT__WORLD:
				return basicSetWorld(null, msgs);
			case CleaningrobotsPackage.ROBOT__KNOWN_STATES:
				return ((InternalEList<?>)getKnownStates()).basicRemove(otherEnd, msgs);
		}
		return super.eInverseRemove(otherEnd, featureID, msgs);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CleaningrobotsPackage.ROBOT__NAME:
				return getName();
			case CleaningrobotsPackage.ROBOT__WORLD:
				return getWorld();
			case CleaningrobotsPackage.ROBOT__DESTINATION:
				if (resolve) return getDestination();
				return basicGetDestination();
			case CleaningrobotsPackage.ROBOT__COMPONENTS:
				return getComponents();
			case CleaningrobotsPackage.ROBOT__ROLES:
				return getRoles();
			case CleaningrobotsPackage.ROBOT__KNOWN_STATES:
				return getKnownStates();
			case CleaningrobotsPackage.ROBOT__ROBOT_KNOWLEDGE:
				return getRobotKnowledge();
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
			case CleaningrobotsPackage.ROBOT__NAME:
				setName((String)newValue);
				return;
			case CleaningrobotsPackage.ROBOT__WORLD:
				setWorld((WorldPart)newValue);
				return;
			case CleaningrobotsPackage.ROBOT__DESTINATION:
				setDestination((Position)newValue);
				return;
			case CleaningrobotsPackage.ROBOT__COMPONENTS:
				getComponents().clear();
				getComponents().addAll((Collection<? extends String>)newValue);
				return;
			case CleaningrobotsPackage.ROBOT__ROLES:
				getRoles().clear();
				getRoles().addAll((Collection<? extends Role>)newValue);
				return;
			case CleaningrobotsPackage.ROBOT__KNOWN_STATES:
				getKnownStates().clear();
				getKnownStates().addAll((Collection<? extends State>)newValue);
				return;
			case CleaningrobotsPackage.ROBOT__ROBOT_KNOWLEDGE:
				getRobotKnowledge().clear();
				getRobotKnowledge().addAll((Collection<? extends RobotKnowledge>)newValue);
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
			case CleaningrobotsPackage.ROBOT__NAME:
				setName(null);
				return;
			case CleaningrobotsPackage.ROBOT__WORLD:
				setWorld((WorldPart)null);
				return;
			case CleaningrobotsPackage.ROBOT__DESTINATION:
				setDestination((Position)null);
				return;
			case CleaningrobotsPackage.ROBOT__COMPONENTS:
				getComponents().clear();
				return;
			case CleaningrobotsPackage.ROBOT__ROLES:
				getRoles().clear();
				return;
			case CleaningrobotsPackage.ROBOT__KNOWN_STATES:
				getKnownStates().clear();
				return;
			case CleaningrobotsPackage.ROBOT__ROBOT_KNOWLEDGE:
				getRobotKnowledge().clear();
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
			case CleaningrobotsPackage.ROBOT__NAME:
				return name != null;
			case CleaningrobotsPackage.ROBOT__WORLD:
				return world != null;
			case CleaningrobotsPackage.ROBOT__DESTINATION:
				return destination != null;
			case CleaningrobotsPackage.ROBOT__COMPONENTS:
				return components != null && !components.isEmpty();
			case CleaningrobotsPackage.ROBOT__ROLES:
				return roles != null && !roles.isEmpty();
			case CleaningrobotsPackage.ROBOT__KNOWN_STATES:
				return knownStates != null && !knownStates.isEmpty();
			case CleaningrobotsPackage.ROBOT__ROBOT_KNOWLEDGE:
				return robotKnowledge != null && !robotKnowledge.isEmpty();
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
		result.append(", components: ");
		result.append(components);
		result.append(')');
		return result.toString();
	}

} //RobotImpl

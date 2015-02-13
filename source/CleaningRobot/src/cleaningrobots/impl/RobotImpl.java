/**
 */
package cleaningrobots.impl;

import cleaningrobots.CleaningrobotsPackage;
import cleaningrobots.Robot;
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
import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Robot</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link cleaningrobots.impl.RobotImpl#getName <em>Name</em>}</li>
 *   <li>{@link cleaningrobots.impl.RobotImpl#getKnownStates <em>Known States</em>}</li>
 *   <li>{@link cleaningrobots.impl.RobotImpl#getWorld <em>World</em>}</li>
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
	protected static final String NAME_EDEFAULT = null;

	/**
	 * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getName()
	 * @generated
	 * @ordered
	 */
	protected String name = NAME_EDEFAULT;

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
	 * The cached value of the '{@link #getWorld() <em>World</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorld()
	 * @generated
	 * @ordered
	 */
	protected WorldPart world;

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
		if (world != null && world.eIsProxy()) {
			InternalEObject oldWorld = (InternalEObject)world;
			world = (WorldPart)eResolveProxy(oldWorld);
			if (world != oldWorld) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CleaningrobotsPackage.ROBOT__WORLD, oldWorld, world));
			}
		}
		return world;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorldPart basicGetWorld() {
		return world;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setWorld(WorldPart newWorld) {
		WorldPart oldWorld = world;
		world = newWorld;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CleaningrobotsPackage.ROBOT__WORLD, oldWorld, world));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
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
			case CleaningrobotsPackage.ROBOT__KNOWN_STATES:
				return getKnownStates();
			case CleaningrobotsPackage.ROBOT__WORLD:
				if (resolve) return getWorld();
				return basicGetWorld();
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
			case CleaningrobotsPackage.ROBOT__KNOWN_STATES:
				getKnownStates().clear();
				getKnownStates().addAll((Collection<? extends State>)newValue);
				return;
			case CleaningrobotsPackage.ROBOT__WORLD:
				setWorld((WorldPart)newValue);
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
				setName(NAME_EDEFAULT);
				return;
			case CleaningrobotsPackage.ROBOT__KNOWN_STATES:
				getKnownStates().clear();
				return;
			case CleaningrobotsPackage.ROBOT__WORLD:
				setWorld((WorldPart)null);
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
				return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
			case CleaningrobotsPackage.ROBOT__KNOWN_STATES:
				return knownStates != null && !knownStates.isEmpty();
			case CleaningrobotsPackage.ROBOT__WORLD:
				return world != null;
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
		result.append(')');
		return result.toString();
	}

} //RobotImpl

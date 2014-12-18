/**
 */
package cleaningrobots.impl;

import cleaningrobots.CleaningrobotsPackage;
import cleaningrobots.Map;
import cleaningrobots.Motor;
import cleaningrobots.Robot;
import cleaningrobots.Sensor;
import cleaningrobots.State;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
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
 *   <li>{@link cleaningrobots.impl.RobotImpl#getMap <em>Map</em>}</li>
 *   <li>{@link cleaningrobots.impl.RobotImpl#getKnownStates <em>Known States</em>}</li>
 *   <li>{@link cleaningrobots.impl.RobotImpl#getSensor <em>Sensor</em>}</li>
 *   <li>{@link cleaningrobots.impl.RobotImpl#getActuator <em>Actuator</em>}</li>
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
	 * The cached value of the '{@link #getMap() <em>Map</em>}' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getMap()
	 * @generated
	 * @ordered
	 */
	protected Map map;

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
	 * The cached value of the '{@link #getSensor() <em>Sensor</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSensor()
	 * @generated
	 * @ordered
	 */
	protected EList<Sensor> sensor;

	/**
	 * The cached value of the '{@link #getActuator() <em>Actuator</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getActuator()
	 * @generated
	 * @ordered
	 */
	protected Motor actuator;

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
	public Map getMap() {
		return map;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public NotificationChain basicSetMap(Map newMap, NotificationChain msgs) {
		Map oldMap = map;
		map = newMap;
		if (eNotificationRequired()) {
			ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, CleaningrobotsPackage.ROBOT__MAP, oldMap, newMap);
			if (msgs == null) msgs = notification; else msgs.add(notification);
		}
		return msgs;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setMap(Map newMap) {
		if (newMap != map) {
			NotificationChain msgs = null;
			if (map != null)
				msgs = ((InternalEObject)map).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - CleaningrobotsPackage.ROBOT__MAP, null, msgs);
			if (newMap != null)
				msgs = ((InternalEObject)newMap).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - CleaningrobotsPackage.ROBOT__MAP, null, msgs);
			msgs = basicSetMap(newMap, msgs);
			if (msgs != null) msgs.dispatch();
		}
		else if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CleaningrobotsPackage.ROBOT__MAP, newMap, newMap));
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
	public EList<Sensor> getSensor() {
		if (sensor == null) {
			sensor = new EObjectResolvingEList<Sensor>(Sensor.class, this, CleaningrobotsPackage.ROBOT__SENSOR);
		}
		return sensor;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Motor getActuator() {
		if (actuator != null && actuator.eIsProxy()) {
			InternalEObject oldActuator = (InternalEObject)actuator;
			actuator = (Motor)eResolveProxy(oldActuator);
			if (actuator != oldActuator) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CleaningrobotsPackage.ROBOT__ACTUATOR, oldActuator, actuator));
			}
		}
		return actuator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Motor basicGetActuator() {
		return actuator;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setActuator(Motor newActuator) {
		Motor oldActuator = actuator;
		actuator = newActuator;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CleaningrobotsPackage.ROBOT__ACTUATOR, oldActuator, actuator));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CleaningrobotsPackage.ROBOT__MAP:
				return basicSetMap(null, msgs);
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
			case CleaningrobotsPackage.ROBOT__MAP:
				return getMap();
			case CleaningrobotsPackage.ROBOT__KNOWN_STATES:
				return getKnownStates();
			case CleaningrobotsPackage.ROBOT__SENSOR:
				return getSensor();
			case CleaningrobotsPackage.ROBOT__ACTUATOR:
				if (resolve) return getActuator();
				return basicGetActuator();
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
			case CleaningrobotsPackage.ROBOT__MAP:
				setMap((Map)newValue);
				return;
			case CleaningrobotsPackage.ROBOT__KNOWN_STATES:
				getKnownStates().clear();
				getKnownStates().addAll((Collection<? extends State>)newValue);
				return;
			case CleaningrobotsPackage.ROBOT__SENSOR:
				getSensor().clear();
				getSensor().addAll((Collection<? extends Sensor>)newValue);
				return;
			case CleaningrobotsPackage.ROBOT__ACTUATOR:
				setActuator((Motor)newValue);
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
			case CleaningrobotsPackage.ROBOT__MAP:
				setMap((Map)null);
				return;
			case CleaningrobotsPackage.ROBOT__KNOWN_STATES:
				getKnownStates().clear();
				return;
			case CleaningrobotsPackage.ROBOT__SENSOR:
				getSensor().clear();
				return;
			case CleaningrobotsPackage.ROBOT__ACTUATOR:
				setActuator((Motor)null);
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
			case CleaningrobotsPackage.ROBOT__MAP:
				return map != null;
			case CleaningrobotsPackage.ROBOT__KNOWN_STATES:
				return knownStates != null && !knownStates.isEmpty();
			case CleaningrobotsPackage.ROBOT__SENSOR:
				return sensor != null && !sensor.isEmpty();
			case CleaningrobotsPackage.ROBOT__ACTUATOR:
				return actuator != null;
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

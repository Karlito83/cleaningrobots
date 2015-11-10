/**
 */
package cleaningrobots.impl;

import cleaningrobots.CleaningrobotsPackage;
import cleaningrobots.State;
import cleaningrobots.WorldPart;
import java.util.Collection;
import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.util.EList;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;
import org.eclipse.emf.ecore.util.EObjectResolvingEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>World Part</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link cleaningrobots.impl.WorldPartImpl#getXdim <em>Xdim</em>}</li>
 *   <li>{@link cleaningrobots.impl.WorldPartImpl#getYdim <em>Ydim</em>}</li>
 *   <li>{@link cleaningrobots.impl.WorldPartImpl#getWorldStates <em>World States</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WorldPartImpl extends MinimalEObjectImpl.Container implements WorldPart {
	/**
	 * The default value of the '{@link #getXdim() <em>Xdim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXdim()
	 * @generated
	 * @ordered
	 */
	//protected int XDIM_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getXdim() <em>Xdim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXdim()
	 * @generated
	 * @ordered
	 */
	protected int xdim = 0;

	/**
	 * The default value of the '{@link #getYdim() <em>Ydim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYdim()
	 * @generated
	 * @ordered
	 */
	//protected int YDIM_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getYdim() <em>Ydim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYdim()
	 * @generated
	 * @ordered
	 */
	protected int ydim = 0;

	/**
	 * The cached value of the '{@link #getWorldStates() <em>World States</em>}' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getWorldStates()
	 * @generated
	 * @ordered
	 */
	protected EList<State> worldStates;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected WorldPartImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CleaningrobotsPackage.Literals.WORLD_PART;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getXdim() {
		return xdim;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXdim(int newXdim) {
		int oldXdim = xdim;
		xdim = newXdim;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CleaningrobotsPackage.WORLD_PART__XDIM, oldXdim, xdim));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getYdim() {
		return ydim;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setYdim(int newYdim) {
		int oldYdim = ydim;
		ydim = newYdim;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CleaningrobotsPackage.WORLD_PART__YDIM, oldYdim, ydim));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<State> getWorldStates() {
		if (worldStates == null) {
			worldStates = new EObjectResolvingEList<State>(State.class, this, CleaningrobotsPackage.WORLD_PART__WORLD_STATES);
		}
		return worldStates;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CleaningrobotsPackage.WORLD_PART__XDIM:
				return getXdim();
			case CleaningrobotsPackage.WORLD_PART__YDIM:
				return getYdim();
			case CleaningrobotsPackage.WORLD_PART__WORLD_STATES:
				return getWorldStates();
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
			case CleaningrobotsPackage.WORLD_PART__XDIM:
				setXdim((Integer)newValue);
				return;
			case CleaningrobotsPackage.WORLD_PART__YDIM:
				setYdim((Integer)newValue);
				return;
			case CleaningrobotsPackage.WORLD_PART__WORLD_STATES:
				getWorldStates().clear();
				getWorldStates().addAll((Collection<? extends State>)newValue);
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
			case CleaningrobotsPackage.WORLD_PART__XDIM:
				setXdim(0);
				return;
			case CleaningrobotsPackage.WORLD_PART__YDIM:
				setYdim(0);
				return;
			case CleaningrobotsPackage.WORLD_PART__WORLD_STATES:
				getWorldStates().clear();
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
			case CleaningrobotsPackage.WORLD_PART__XDIM:
				return xdim != 0;
			case CleaningrobotsPackage.WORLD_PART__YDIM:
				return ydim != 0;
			case CleaningrobotsPackage.WORLD_PART__WORLD_STATES:
				return worldStates != null && !worldStates.isEmpty();
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
		result.append(" (xdim: ");
		result.append(xdim);
		result.append(", ydim: ");
		result.append(ydim);
		result.append(')');
		return result.toString();
	}

} //WorldPartImpl

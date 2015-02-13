/**
 */
package cleaningrobots.impl;

import cleaningrobots.CleaningrobotsPackage;
import cleaningrobots.WorldPart;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>World Part</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link cleaningrobots.impl.WorldPartImpl#getXdim <em>Xdim</em>}</li>
 *   <li>{@link cleaningrobots.impl.WorldPartImpl#getYdim <em>Ydim</em>}</li>
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
	protected static final int XDIM_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getXdim() <em>Xdim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXdim()
	 * @generated
	 * @ordered
	 */
	protected int xdim = XDIM_EDEFAULT;

	/**
	 * The default value of the '{@link #getYdim() <em>Ydim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYdim()
	 * @generated
	 * @ordered
	 */
	protected static final int YDIM_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getYdim() <em>Ydim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYdim()
	 * @generated
	 * @ordered
	 */
	protected int ydim = YDIM_EDEFAULT;

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
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CleaningrobotsPackage.WORLD_PART__XDIM:
				return getXdim();
			case CleaningrobotsPackage.WORLD_PART__YDIM:
				return getYdim();
		}
		return super.eGet(featureID, resolve, coreType);
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public void eSet(int featureID, Object newValue) {
		switch (featureID) {
			case CleaningrobotsPackage.WORLD_PART__XDIM:
				setXdim((Integer)newValue);
				return;
			case CleaningrobotsPackage.WORLD_PART__YDIM:
				setYdim((Integer)newValue);
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
				setXdim(XDIM_EDEFAULT);
				return;
			case CleaningrobotsPackage.WORLD_PART__YDIM:
				setYdim(YDIM_EDEFAULT);
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
				return xdim != XDIM_EDEFAULT;
			case CleaningrobotsPackage.WORLD_PART__YDIM:
				return ydim != YDIM_EDEFAULT;
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

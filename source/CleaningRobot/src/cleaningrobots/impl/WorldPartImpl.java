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
 *   <li>{@link cleaningrobots.impl.WorldPartImpl#getXDim <em>XDim</em>}</li>
 *   <li>{@link cleaningrobots.impl.WorldPartImpl#getYDim <em>YDim</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class WorldPartImpl extends MinimalEObjectImpl.Container implements WorldPart {
	/**
	 * The default value of the '{@link #getXDim() <em>XDim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXDim()
	 * @generated
	 * @ordered
	 */
	protected static final int XDIM_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getXDim() <em>XDim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXDim()
	 * @generated
	 * @ordered
	 */
	protected int xDim = XDIM_EDEFAULT;

	/**
	 * The default value of the '{@link #getYDim() <em>YDim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYDim()
	 * @generated
	 * @ordered
	 */
	protected static final int YDIM_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getYDim() <em>YDim</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYDim()
	 * @generated
	 * @ordered
	 */
	protected int yDim = YDIM_EDEFAULT;

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
	public int getXDim() {
		return xDim;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXDim(int newXDim) {
		int oldXDim = xDim;
		xDim = newXDim;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CleaningrobotsPackage.WORLD_PART__XDIM, oldXDim, xDim));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getYDim() {
		return yDim;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setYDim(int newYDim) {
		int oldYDim = yDim;
		yDim = newYDim;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CleaningrobotsPackage.WORLD_PART__YDIM, oldYDim, yDim));
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
				return getXDim();
			case CleaningrobotsPackage.WORLD_PART__YDIM:
				return getYDim();
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
				setXDim((Integer)newValue);
				return;
			case CleaningrobotsPackage.WORLD_PART__YDIM:
				setYDim((Integer)newValue);
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
				setXDim(XDIM_EDEFAULT);
				return;
			case CleaningrobotsPackage.WORLD_PART__YDIM:
				setYDim(YDIM_EDEFAULT);
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
				return xDim != XDIM_EDEFAULT;
			case CleaningrobotsPackage.WORLD_PART__YDIM:
				return yDim != YDIM_EDEFAULT;
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
		result.append(" (xDim: ");
		result.append(xDim);
		result.append(", yDim: ");
		result.append(yDim);
		result.append(')');
		return result.toString();
	}

} //WorldPartImpl

/**
 */
package cleaningrobots.impl;

import cleaningrobots.CleaningrobotsPackage;
import cleaningrobots.Position;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Position</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link cleaningrobots.impl.PositionImpl#getXpos <em>Xpos</em>}</li>
 *   <li>{@link cleaningrobots.impl.PositionImpl#getYpos <em>Ypos</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class PositionImpl extends MinimalEObjectImpl.Container implements Position {
	/**
	 * The default value of the '{@link #getXpos() <em>Xpos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXpos()
	 * @generated
	 * @ordered
	 */
	//protected int XPOS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getXpos() <em>Xpos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXpos()
	 * @generated
	 * @ordered
	 */
	protected int xpos = 0;

	/**
	 * The default value of the '{@link #getYpos() <em>Ypos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYpos()
	 * @generated
	 * @ordered
	 */
	//protected int YPOS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getYpos() <em>Ypos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYpos()
	 * @generated
	 * @ordered
	 */
	protected int ypos = 0;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected PositionImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CleaningrobotsPackage.Literals.POSITION;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getXpos() {
		return xpos;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setXpos(int newXpos) {
		int oldXpos = xpos;
		xpos = newXpos;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CleaningrobotsPackage.POSITION__XPOS, oldXpos, xpos));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public int getYpos() {
		return ypos;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setYpos(int newYpos) {
		int oldYpos = ypos;
		ypos = newYpos;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CleaningrobotsPackage.POSITION__YPOS, oldYpos, ypos));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CleaningrobotsPackage.POSITION__XPOS:
				return getXpos();
			case CleaningrobotsPackage.POSITION__YPOS:
				return getYpos();
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
			case CleaningrobotsPackage.POSITION__XPOS:
				setXpos((Integer)newValue);
				return;
			case CleaningrobotsPackage.POSITION__YPOS:
				setYpos((Integer)newValue);
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
			case CleaningrobotsPackage.POSITION__XPOS:
				setXpos(0);
				return;
			case CleaningrobotsPackage.POSITION__YPOS:
				setYpos(0);
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
			case CleaningrobotsPackage.POSITION__XPOS:
				return xpos != 0;
			case CleaningrobotsPackage.POSITION__YPOS:
				return ypos != 0;
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
		result.append(" (xpos: ");
		result.append(xpos);
		result.append(", ypos: ");
		result.append(ypos);
		result.append(')');
		return result.toString();
	}

} //PositionImpl

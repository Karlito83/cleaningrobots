/**
 */
package cleaningrobots.impl;

import cleaningrobots.CleaningrobotsPackage;
import cleaningrobots.Field;
import cleaningrobots.State;

import org.eclipse.emf.common.notify.Notification;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Field</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link cleaningrobots.impl.FieldImpl#getXpos <em>Xpos</em>}</li>
 *   <li>{@link cleaningrobots.impl.FieldImpl#getYpos <em>Ypos</em>}</li>
 *   <li>{@link cleaningrobots.impl.FieldImpl#getState <em>State</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FieldImpl extends MinimalEObjectImpl.Container implements Field {
	/**
	 * The default value of the '{@link #getXpos() <em>Xpos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXpos()
	 * @generated
	 * @ordered
	 */
	protected static final int XPOS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getXpos() <em>Xpos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getXpos()
	 * @generated
	 * @ordered
	 */
	protected int xpos = XPOS_EDEFAULT;

	/**
	 * The default value of the '{@link #getYpos() <em>Ypos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYpos()
	 * @generated
	 * @ordered
	 */
	protected static final int YPOS_EDEFAULT = 0;

	/**
	 * The cached value of the '{@link #getYpos() <em>Ypos</em>}' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getYpos()
	 * @generated
	 * @ordered
	 */
	protected int ypos = YPOS_EDEFAULT;

	/**
	 * The cached value of the '{@link #getState() <em>State</em>}' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getState()
	 * @generated
	 * @ordered
	 */
	protected State state;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected FieldImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CleaningrobotsPackage.Literals.FIELD;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CleaningrobotsPackage.FIELD__XPOS, oldXpos, xpos));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CleaningrobotsPackage.FIELD__YPOS, oldYpos, ypos));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State getState() {
		if (state != null && state.eIsProxy()) {
			InternalEObject oldState = (InternalEObject)state;
			state = (State)eResolveProxy(oldState);
			if (state != oldState) {
				if (eNotificationRequired())
					eNotify(new ENotificationImpl(this, Notification.RESOLVE, CleaningrobotsPackage.FIELD__STATE, oldState, state));
			}
		}
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State basicGetState() {
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public void setState(State newState) {
		State oldState = state;
		state = newState;
		if (eNotificationRequired())
			eNotify(new ENotificationImpl(this, Notification.SET, CleaningrobotsPackage.FIELD__STATE, oldState, state));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public Object eGet(int featureID, boolean resolve, boolean coreType) {
		switch (featureID) {
			case CleaningrobotsPackage.FIELD__XPOS:
				return getXpos();
			case CleaningrobotsPackage.FIELD__YPOS:
				return getYpos();
			case CleaningrobotsPackage.FIELD__STATE:
				if (resolve) return getState();
				return basicGetState();
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
			case CleaningrobotsPackage.FIELD__XPOS:
				setXpos((Integer)newValue);
				return;
			case CleaningrobotsPackage.FIELD__YPOS:
				setYpos((Integer)newValue);
				return;
			case CleaningrobotsPackage.FIELD__STATE:
				setState((State)newValue);
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
			case CleaningrobotsPackage.FIELD__XPOS:
				setXpos(XPOS_EDEFAULT);
				return;
			case CleaningrobotsPackage.FIELD__YPOS:
				setYpos(YPOS_EDEFAULT);
				return;
			case CleaningrobotsPackage.FIELD__STATE:
				setState((State)null);
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
			case CleaningrobotsPackage.FIELD__XPOS:
				return xpos != XPOS_EDEFAULT;
			case CleaningrobotsPackage.FIELD__YPOS:
				return ypos != YPOS_EDEFAULT;
			case CleaningrobotsPackage.FIELD__STATE:
				return state != null;
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

} //FieldImpl

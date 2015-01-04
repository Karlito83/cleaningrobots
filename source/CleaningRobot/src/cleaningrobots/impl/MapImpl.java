/**
 */
package cleaningrobots.impl;

import cleaningrobots.CleaningrobotsPackage;
import cleaningrobots.Field;
import cleaningrobots.Map;

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
 * An implementation of the model object '<em><b>Map</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link cleaningrobots.impl.MapImpl#getXdim <em>Xdim</em>}</li>
 *   <li>{@link cleaningrobots.impl.MapImpl#getYdim <em>Ydim</em>}</li>
 *   <li>{@link cleaningrobots.impl.MapImpl#getFields <em>Fields</em>}</li>
 *   <li>{@link cleaningrobots.impl.MapImpl#getSubMaps <em>Sub Maps</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class MapImpl extends MinimalEObjectImpl.Container implements Map {
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
	 * The cached value of the '{@link #getFields() <em>Fields</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getFields()
	 * @generated
	 * @ordered
	 */
	protected EList<Field> fields;

	/**
	 * The cached value of the '{@link #getSubMaps() <em>Sub Maps</em>}' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see #getSubMaps()
	 * @generated
	 * @ordered
	 */
	protected EList<Map> subMaps;

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	protected MapImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	protected EClass eStaticClass() {
		return CleaningrobotsPackage.Literals.MAP;
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
			eNotify(new ENotificationImpl(this, Notification.SET, CleaningrobotsPackage.MAP__XDIM, oldXdim, xdim));
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
			eNotify(new ENotificationImpl(this, Notification.SET, CleaningrobotsPackage.MAP__YDIM, oldYdim, ydim));
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Field> getFields() {
		if (fields == null) {
			fields = new EObjectContainmentEList<Field>(Field.class, this, CleaningrobotsPackage.MAP__FIELDS);
		}
		return fields;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public EList<Map> getSubMaps() {
		if (subMaps == null) {
			subMaps = new EObjectContainmentEList<Map>(Map.class, this, CleaningrobotsPackage.MAP__SUB_MAPS);
		}
		return subMaps;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs) {
		switch (featureID) {
			case CleaningrobotsPackage.MAP__FIELDS:
				return ((InternalEList<?>)getFields()).basicRemove(otherEnd, msgs);
			case CleaningrobotsPackage.MAP__SUB_MAPS:
				return ((InternalEList<?>)getSubMaps()).basicRemove(otherEnd, msgs);
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
			case CleaningrobotsPackage.MAP__XDIM:
				return getXdim();
			case CleaningrobotsPackage.MAP__YDIM:
				return getYdim();
			case CleaningrobotsPackage.MAP__FIELDS:
				return getFields();
			case CleaningrobotsPackage.MAP__SUB_MAPS:
				return getSubMaps();
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
			case CleaningrobotsPackage.MAP__XDIM:
				setXdim((Integer)newValue);
				return;
			case CleaningrobotsPackage.MAP__YDIM:
				setYdim((Integer)newValue);
				return;
			case CleaningrobotsPackage.MAP__FIELDS:
				getFields().clear();
				getFields().addAll((Collection<? extends Field>)newValue);
				return;
			case CleaningrobotsPackage.MAP__SUB_MAPS:
				getSubMaps().clear();
				getSubMaps().addAll((Collection<? extends Map>)newValue);
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
			case CleaningrobotsPackage.MAP__XDIM:
				setXdim(XDIM_EDEFAULT);
				return;
			case CleaningrobotsPackage.MAP__YDIM:
				setYdim(YDIM_EDEFAULT);
				return;
			case CleaningrobotsPackage.MAP__FIELDS:
				getFields().clear();
				return;
			case CleaningrobotsPackage.MAP__SUB_MAPS:
				getSubMaps().clear();
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
			case CleaningrobotsPackage.MAP__XDIM:
				return xdim != XDIM_EDEFAULT;
			case CleaningrobotsPackage.MAP__YDIM:
				return ydim != YDIM_EDEFAULT;
			case CleaningrobotsPackage.MAP__FIELDS:
				return fields != null && !fields.isEmpty();
			case CleaningrobotsPackage.MAP__SUB_MAPS:
				return subMaps != null && !subMaps.isEmpty();
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

} //MapImpl

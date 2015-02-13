/**
 */
package cleaningrobots;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each operation of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see cleaningrobots.CleaningrobotsFactory
 * @model kind="package"
 * @generated
 */
public interface CleaningrobotsPackage extends EPackage {
	/**
	 * The package name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNAME = "cleaningrobots";

	/**
	 * The package namespace URI.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_URI = "http://www.not.yet";

	/**
	 * The package namespace name.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	String eNS_PREFIX = "org.dagstuhl.cleaningrobots";

	/**
	 * The singleton instance of the package.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	CleaningrobotsPackage eINSTANCE = cleaningrobots.impl.CleaningrobotsPackageImpl.init();

	/**
	 * The meta object id for the '{@link cleaningrobots.impl.RobotImpl <em>Robot</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cleaningrobots.impl.RobotImpl
	 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getRobot()
	 * @generated
	 */
	int ROBOT = 0;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT__NAME = 0;

	/**
	 * The feature id for the '<em><b>Known States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT__KNOWN_STATES = 1;

	/**
	 * The feature id for the '<em><b>World</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT__WORLD = 2;

	/**
	 * The number of structural features of the '<em>Robot</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Robot</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link cleaningrobots.impl.MapImpl <em>Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cleaningrobots.impl.MapImpl
	 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getMap()
	 * @generated
	 */
	int MAP = 5;

	/**
	 * The meta object id for the '{@link cleaningrobots.impl.FieldImpl <em>Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cleaningrobots.impl.FieldImpl
	 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getField()
	 * @generated
	 */
	int FIELD = 1;

	/**
	 * The feature id for the '<em><b>Xpos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__XPOS = 0;

	/**
	 * The feature id for the '<em><b>Ypos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__YPOS = 1;

	/**
	 * The feature id for the '<em><b>States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__STATES = 2;

	/**
	 * The number of structural features of the '<em>Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link cleaningrobots.impl.StateImpl <em>State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cleaningrobots.impl.StateImpl
	 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getState()
	 * @generated
	 */
	int STATE = 2;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Transition</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE__TRANSITION = 1;

	/**
	 * The number of structural features of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>State</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int STATE_OPERATION_COUNT = 0;


	/**
	 * The meta object id for the '{@link cleaningrobots.impl.WorldPartImpl <em>World Part</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cleaningrobots.impl.WorldPartImpl
	 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getWorldPart()
	 * @generated
	 */
	int WORLD_PART = 3;

	/**
	 * The feature id for the '<em><b>Xdim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD_PART__XDIM = 0;

	/**
	 * The feature id for the '<em><b>Ydim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD_PART__YDIM = 1;

	/**
	 * The number of structural features of the '<em>World Part</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD_PART_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>World Part</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD_PART_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link cleaningrobots.impl.WorldImpl <em>World</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cleaningrobots.impl.WorldImpl
	 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getWorld()
	 * @generated
	 */
	int WORLD = 4;

	/**
	 * The feature id for the '<em><b>Xdim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD__XDIM = WORLD_PART__XDIM;

	/**
	 * The feature id for the '<em><b>Ydim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD__YDIM = WORLD_PART__YDIM;

	/**
	 * The feature id for the '<em><b>Children</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD__CHILDREN = WORLD_PART_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>World</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD_FEATURE_COUNT = WORLD_PART_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>World</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD_OPERATION_COUNT = WORLD_PART_OPERATION_COUNT + 0;

	/**
	 * The feature id for the '<em><b>Xdim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP__XDIM = WORLD_PART__XDIM;

	/**
	 * The feature id for the '<em><b>Ydim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP__YDIM = WORLD_PART__YDIM;

	/**
	 * The feature id for the '<em><b>Fields</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP__FIELDS = WORLD_PART_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_FEATURE_COUNT = WORLD_PART_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_OPERATION_COUNT = WORLD_PART_OPERATION_COUNT + 0;


	/**
	 * Returns the meta object for class '{@link cleaningrobots.Robot <em>Robot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Robot</em>'.
	 * @see cleaningrobots.Robot
	 * @generated
	 */
	EClass getRobot();

	/**
	 * Returns the meta object for the attribute '{@link cleaningrobots.Robot#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see cleaningrobots.Robot#getName()
	 * @see #getRobot()
	 * @generated
	 */
	EAttribute getRobot_Name();

	/**
	 * Returns the meta object for the containment reference list '{@link cleaningrobots.Robot#getKnownStates <em>Known States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Known States</em>'.
	 * @see cleaningrobots.Robot#getKnownStates()
	 * @see #getRobot()
	 * @generated
	 */
	EReference getRobot_KnownStates();

	/**
	 * Returns the meta object for the reference '{@link cleaningrobots.Robot#getWorld <em>World</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>World</em>'.
	 * @see cleaningrobots.Robot#getWorld()
	 * @see #getRobot()
	 * @generated
	 */
	EReference getRobot_World();

	/**
	 * Returns the meta object for class '{@link cleaningrobots.Map <em>Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Map</em>'.
	 * @see cleaningrobots.Map
	 * @generated
	 */
	EClass getMap();

	/**
	 * Returns the meta object for the containment reference list '{@link cleaningrobots.Map#getFields <em>Fields</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Fields</em>'.
	 * @see cleaningrobots.Map#getFields()
	 * @see #getMap()
	 * @generated
	 */
	EReference getMap_Fields();

	/**
	 * Returns the meta object for class '{@link cleaningrobots.Field <em>Field</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Field</em>'.
	 * @see cleaningrobots.Field
	 * @generated
	 */
	EClass getField();

	/**
	 * Returns the meta object for the attribute '{@link cleaningrobots.Field#getXpos <em>Xpos</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xpos</em>'.
	 * @see cleaningrobots.Field#getXpos()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_Xpos();

	/**
	 * Returns the meta object for the attribute '{@link cleaningrobots.Field#getYpos <em>Ypos</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ypos</em>'.
	 * @see cleaningrobots.Field#getYpos()
	 * @see #getField()
	 * @generated
	 */
	EAttribute getField_Ypos();

	/**
	 * Returns the meta object for the reference list '{@link cleaningrobots.Field#getStates <em>States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>States</em>'.
	 * @see cleaningrobots.Field#getStates()
	 * @see #getField()
	 * @generated
	 */
	EReference getField_States();

	/**
	 * Returns the meta object for class '{@link cleaningrobots.State <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>State</em>'.
	 * @see cleaningrobots.State
	 * @generated
	 */
	EClass getState();

	/**
	 * Returns the meta object for the attribute '{@link cleaningrobots.State#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see cleaningrobots.State#getName()
	 * @see #getState()
	 * @generated
	 */
	EAttribute getState_Name();

	/**
	 * Returns the meta object for the reference list '{@link cleaningrobots.State#getTransition <em>Transition</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Transition</em>'.
	 * @see cleaningrobots.State#getTransition()
	 * @see #getState()
	 * @generated
	 */
	EReference getState_Transition();

	/**
	 * Returns the meta object for class '{@link cleaningrobots.WorldPart <em>World Part</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>World Part</em>'.
	 * @see cleaningrobots.WorldPart
	 * @generated
	 */
	EClass getWorldPart();

	/**
	 * Returns the meta object for the attribute '{@link cleaningrobots.WorldPart#getXdim <em>Xdim</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xdim</em>'.
	 * @see cleaningrobots.WorldPart#getXdim()
	 * @see #getWorldPart()
	 * @generated
	 */
	EAttribute getWorldPart_Xdim();

	/**
	 * Returns the meta object for the attribute '{@link cleaningrobots.WorldPart#getYdim <em>Ydim</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ydim</em>'.
	 * @see cleaningrobots.WorldPart#getYdim()
	 * @see #getWorldPart()
	 * @generated
	 */
	EAttribute getWorldPart_Ydim();

	/**
	 * Returns the meta object for class '{@link cleaningrobots.World <em>World</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>World</em>'.
	 * @see cleaningrobots.World
	 * @generated
	 */
	EClass getWorld();

	/**
	 * Returns the meta object for the reference list '{@link cleaningrobots.World#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Children</em>'.
	 * @see cleaningrobots.World#getChildren()
	 * @see #getWorld()
	 * @generated
	 */
	EReference getWorld_Children();

	/**
	 * Returns the factory that creates the instances of the model.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the factory that creates the instances of the model.
	 * @generated
	 */
	CleaningrobotsFactory getCleaningrobotsFactory();

	/**
	 * <!-- begin-user-doc -->
	 * Defines literals for the meta objects that represent
	 * <ul>
	 *   <li>each class,</li>
	 *   <li>each feature of each class,</li>
	 *   <li>each operation of each class,</li>
	 *   <li>each enum,</li>
	 *   <li>and each data type</li>
	 * </ul>
	 * <!-- end-user-doc -->
	 * @generated
	 */
	interface Literals {
		/**
		 * The meta object literal for the '{@link cleaningrobots.impl.RobotImpl <em>Robot</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cleaningrobots.impl.RobotImpl
		 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getRobot()
		 * @generated
		 */
		EClass ROBOT = eINSTANCE.getRobot();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROBOT__NAME = eINSTANCE.getRobot_Name();

		/**
		 * The meta object literal for the '<em><b>Known States</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROBOT__KNOWN_STATES = eINSTANCE.getRobot_KnownStates();

		/**
		 * The meta object literal for the '<em><b>World</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROBOT__WORLD = eINSTANCE.getRobot_World();

		/**
		 * The meta object literal for the '{@link cleaningrobots.impl.MapImpl <em>Map</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cleaningrobots.impl.MapImpl
		 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getMap()
		 * @generated
		 */
		EClass MAP = eINSTANCE.getMap();

		/**
		 * The meta object literal for the '<em><b>Fields</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference MAP__FIELDS = eINSTANCE.getMap_Fields();

		/**
		 * The meta object literal for the '{@link cleaningrobots.impl.FieldImpl <em>Field</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cleaningrobots.impl.FieldImpl
		 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getField()
		 * @generated
		 */
		EClass FIELD = eINSTANCE.getField();

		/**
		 * The meta object literal for the '<em><b>Xpos</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD__XPOS = eINSTANCE.getField_Xpos();

		/**
		 * The meta object literal for the '<em><b>Ypos</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FIELD__YPOS = eINSTANCE.getField_Ypos();

		/**
		 * The meta object literal for the '<em><b>States</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIELD__STATES = eINSTANCE.getField_States();

		/**
		 * The meta object literal for the '{@link cleaningrobots.impl.StateImpl <em>State</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cleaningrobots.impl.StateImpl
		 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getState()
		 * @generated
		 */
		EClass STATE = eINSTANCE.getState();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute STATE__NAME = eINSTANCE.getState_Name();

		/**
		 * The meta object literal for the '<em><b>Transition</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference STATE__TRANSITION = eINSTANCE.getState_Transition();

		/**
		 * The meta object literal for the '{@link cleaningrobots.impl.WorldPartImpl <em>World Part</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cleaningrobots.impl.WorldPartImpl
		 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getWorldPart()
		 * @generated
		 */
		EClass WORLD_PART = eINSTANCE.getWorldPart();

		/**
		 * The meta object literal for the '<em><b>Xdim</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORLD_PART__XDIM = eINSTANCE.getWorldPart_Xdim();

		/**
		 * The meta object literal for the '<em><b>Ydim</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute WORLD_PART__YDIM = eINSTANCE.getWorldPart_Ydim();

		/**
		 * The meta object literal for the '{@link cleaningrobots.impl.WorldImpl <em>World</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cleaningrobots.impl.WorldImpl
		 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getWorld()
		 * @generated
		 */
		EClass WORLD = eINSTANCE.getWorld();

		/**
		 * The meta object literal for the '<em><b>Children</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORLD__CHILDREN = eINSTANCE.getWorld_Children();

	}

} //CleaningrobotsPackage

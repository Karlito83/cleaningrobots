/**
 */
package cleaningrobots;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EOperation;
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
	 * The feature id for the '<em><b>Map</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT__MAP = 1;

	/**
	 * The feature id for the '<em><b>Known States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT__KNOWN_STATES = 2;

	/**
	 * The feature id for the '<em><b>Sensor</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT__SENSOR = 3;

	/**
	 * The feature id for the '<em><b>Drive</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT__DRIVE = 4;

	/**
	 * The feature id for the '<em><b>Current Behaviour</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT__CURRENT_BEHAVIOUR = 5;

	/**
	 * The number of structural features of the '<em>Robot</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT_FEATURE_COUNT = 6;

	/**
	 * The operation id for the '<em>Action</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT___ACTION = 0;

	/**
	 * The number of operations of the '<em>Robot</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link cleaningrobots.impl.MapImpl <em>Map</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cleaningrobots.impl.MapImpl
	 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getMap()
	 * @generated
	 */
	int MAP = 1;

	/**
	 * The feature id for the '<em><b>Xdim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP__XDIM = 0;

	/**
	 * The feature id for the '<em><b>Ydim</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP__YDIM = 1;

	/**
	 * The feature id for the '<em><b>Fields</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP__FIELDS = 2;

	/**
	 * The number of structural features of the '<em>Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>Map</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link cleaningrobots.impl.FieldImpl <em>Field</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cleaningrobots.impl.FieldImpl
	 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getField()
	 * @generated
	 */
	int FIELD = 2;

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
	 * The feature id for the '<em><b>State</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__STATE = 2;

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
	int STATE = 3;

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
	 * The meta object id for the '{@link cleaningrobots.impl.SensorImpl <em>Sensor</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cleaningrobots.impl.SensorImpl
	 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getSensor()
	 * @generated
	 */
	int SENSOR = 4;

	/**
	 * The number of structural features of the '<em>Sensor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR_FEATURE_COUNT = 0;

	/**
	 * The operation id for the '<em>Get Data</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR___GET_DATA = 0;

	/**
	 * The number of operations of the '<em>Sensor</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int SENSOR_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link cleaningrobots.Drive <em>Drive</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cleaningrobots.Drive
	 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getDrive()
	 * @generated
	 */
	int DRIVE = 5;

	/**
	 * The feature id for the '<em><b>Direction</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRIVE__DIRECTION = 0;

	/**
	 * The number of structural features of the '<em>Drive</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRIVE_FEATURE_COUNT = 1;

	/**
	 * The operation id for the '<em>Move</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRIVE___MOVE = 0;

	/**
	 * The number of operations of the '<em>Drive</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DRIVE_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link cleaningrobots.Behaviour <em>Behaviour</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cleaningrobots.Behaviour
	 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getBehaviour()
	 * @generated
	 */
	int BEHAVIOUR = 6;

	/**
	 * The feature id for the '<em><b>Robot</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOUR__ROBOT = 0;

	/**
	 * The number of structural features of the '<em>Behaviour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOUR_FEATURE_COUNT = 1;

	/**
	 * The operation id for the '<em>Action</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOUR___ACTION = 0;

	/**
	 * The number of operations of the '<em>Behaviour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int BEHAVIOUR_OPERATION_COUNT = 1;

	/**
	 * The meta object id for the '{@link cleaningrobots.impl.CleaningBehaviourImpl <em>Cleaning Behaviour</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cleaningrobots.impl.CleaningBehaviourImpl
	 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getCleaningBehaviour()
	 * @generated
	 */
	int CLEANING_BEHAVIOUR = 7;

	/**
	 * The feature id for the '<em><b>Robot</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLEANING_BEHAVIOUR__ROBOT = BEHAVIOUR__ROBOT;

	/**
	 * The number of structural features of the '<em>Cleaning Behaviour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLEANING_BEHAVIOUR_FEATURE_COUNT = BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Action</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLEANING_BEHAVIOUR___ACTION = BEHAVIOUR___ACTION;

	/**
	 * The number of operations of the '<em>Cleaning Behaviour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int CLEANING_BEHAVIOUR_OPERATION_COUNT = BEHAVIOUR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link cleaningrobots.impl.DiscoverBehaviourImpl <em>Discover Behaviour</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cleaningrobots.impl.DiscoverBehaviourImpl
	 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getDiscoverBehaviour()
	 * @generated
	 */
	int DISCOVER_BEHAVIOUR = 8;

	/**
	 * The feature id for the '<em><b>Robot</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCOVER_BEHAVIOUR__ROBOT = BEHAVIOUR__ROBOT;

	/**
	 * The number of structural features of the '<em>Discover Behaviour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCOVER_BEHAVIOUR_FEATURE_COUNT = BEHAVIOUR_FEATURE_COUNT + 0;

	/**
	 * The operation id for the '<em>Action</em>' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCOVER_BEHAVIOUR___ACTION = BEHAVIOUR___ACTION;

	/**
	 * The number of operations of the '<em>Discover Behaviour</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int DISCOVER_BEHAVIOUR_OPERATION_COUNT = BEHAVIOUR_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link cleaningrobots.Direction <em>Direction</em>}' enum.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cleaningrobots.Direction
	 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getDirection()
	 * @generated
	 */
	int DIRECTION = 9;


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
	 * Returns the meta object for the containment reference '{@link cleaningrobots.Robot#getMap <em>Map</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Map</em>'.
	 * @see cleaningrobots.Robot#getMap()
	 * @see #getRobot()
	 * @generated
	 */
	EReference getRobot_Map();

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
	 * Returns the meta object for the containment reference list '{@link cleaningrobots.Robot#getSensor <em>Sensor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Sensor</em>'.
	 * @see cleaningrobots.Robot#getSensor()
	 * @see #getRobot()
	 * @generated
	 */
	EReference getRobot_Sensor();

	/**
	 * Returns the meta object for the containment reference '{@link cleaningrobots.Robot#getDrive <em>Drive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Drive</em>'.
	 * @see cleaningrobots.Robot#getDrive()
	 * @see #getRobot()
	 * @generated
	 */
	EReference getRobot_Drive();

	/**
	 * Returns the meta object for the containment reference '{@link cleaningrobots.Robot#getCurrentBehaviour <em>Current Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>Current Behaviour</em>'.
	 * @see cleaningrobots.Robot#getCurrentBehaviour()
	 * @see #getRobot()
	 * @generated
	 */
	EReference getRobot_CurrentBehaviour();

	/**
	 * Returns the meta object for the '{@link cleaningrobots.Robot#action() <em>Action</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Action</em>' operation.
	 * @see cleaningrobots.Robot#action()
	 * @generated
	 */
	EOperation getRobot__Action();

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
	 * Returns the meta object for the attribute '{@link cleaningrobots.Map#getXdim <em>Xdim</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xdim</em>'.
	 * @see cleaningrobots.Map#getXdim()
	 * @see #getMap()
	 * @generated
	 */
	EAttribute getMap_Xdim();

	/**
	 * Returns the meta object for the attribute '{@link cleaningrobots.Map#getYdim <em>Ydim</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ydim</em>'.
	 * @see cleaningrobots.Map#getYdim()
	 * @see #getMap()
	 * @generated
	 */
	EAttribute getMap_Ydim();

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
	 * Returns the meta object for the reference list '{@link cleaningrobots.Field#getState <em>State</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>State</em>'.
	 * @see cleaningrobots.Field#getState()
	 * @see #getField()
	 * @generated
	 */
	EReference getField_State();

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
	 * Returns the meta object for class '{@link cleaningrobots.Sensor <em>Sensor</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Sensor</em>'.
	 * @see cleaningrobots.Sensor
	 * @generated
	 */
	EClass getSensor();

	/**
	 * Returns the meta object for the '{@link cleaningrobots.Sensor#getData() <em>Get Data</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Get Data</em>' operation.
	 * @see cleaningrobots.Sensor#getData()
	 * @generated
	 */
	EOperation getSensor__GetData();

	/**
	 * Returns the meta object for class '{@link cleaningrobots.Drive <em>Drive</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Drive</em>'.
	 * @see cleaningrobots.Drive
	 * @generated
	 */
	EClass getDrive();

	/**
	 * Returns the meta object for the attribute '{@link cleaningrobots.Drive#getDirection <em>Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Direction</em>'.
	 * @see cleaningrobots.Drive#getDirection()
	 * @see #getDrive()
	 * @generated
	 */
	EAttribute getDrive_Direction();

	/**
	 * Returns the meta object for the '{@link cleaningrobots.Drive#move() <em>Move</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Move</em>' operation.
	 * @see cleaningrobots.Drive#move()
	 * @generated
	 */
	EOperation getDrive__Move();

	/**
	 * Returns the meta object for class '{@link cleaningrobots.Behaviour <em>Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Behaviour</em>'.
	 * @see cleaningrobots.Behaviour
	 * @generated
	 */
	EClass getBehaviour();

	/**
	 * Returns the meta object for the reference '{@link cleaningrobots.Behaviour#getRobot <em>Robot</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Robot</em>'.
	 * @see cleaningrobots.Behaviour#getRobot()
	 * @see #getBehaviour()
	 * @generated
	 */
	EReference getBehaviour_Robot();

	/**
	 * Returns the meta object for the '{@link cleaningrobots.Behaviour#action() <em>Action</em>}' operation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the '<em>Action</em>' operation.
	 * @see cleaningrobots.Behaviour#action()
	 * @generated
	 */
	EOperation getBehaviour__Action();

	/**
	 * Returns the meta object for class '{@link cleaningrobots.CleaningBehaviour <em>Cleaning Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Cleaning Behaviour</em>'.
	 * @see cleaningrobots.CleaningBehaviour
	 * @generated
	 */
	EClass getCleaningBehaviour();

	/**
	 * Returns the meta object for class '{@link cleaningrobots.DiscoverBehaviour <em>Discover Behaviour</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Discover Behaviour</em>'.
	 * @see cleaningrobots.DiscoverBehaviour
	 * @generated
	 */
	EClass getDiscoverBehaviour();

	/**
	 * Returns the meta object for enum '{@link cleaningrobots.Direction <em>Direction</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for enum '<em>Direction</em>'.
	 * @see cleaningrobots.Direction
	 * @generated
	 */
	EEnum getDirection();

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
		 * The meta object literal for the '<em><b>Map</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROBOT__MAP = eINSTANCE.getRobot_Map();

		/**
		 * The meta object literal for the '<em><b>Known States</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROBOT__KNOWN_STATES = eINSTANCE.getRobot_KnownStates();

		/**
		 * The meta object literal for the '<em><b>Sensor</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROBOT__SENSOR = eINSTANCE.getRobot_Sensor();

		/**
		 * The meta object literal for the '<em><b>Drive</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROBOT__DRIVE = eINSTANCE.getRobot_Drive();

		/**
		 * The meta object literal for the '<em><b>Current Behaviour</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROBOT__CURRENT_BEHAVIOUR = eINSTANCE.getRobot_CurrentBehaviour();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation ROBOT___ACTION = eINSTANCE.getRobot__Action();

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
		 * The meta object literal for the '<em><b>Xdim</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAP__XDIM = eINSTANCE.getMap_Xdim();

		/**
		 * The meta object literal for the '<em><b>Ydim</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MAP__YDIM = eINSTANCE.getMap_Ydim();

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
		 * The meta object literal for the '<em><b>State</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIELD__STATE = eINSTANCE.getField_State();

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
		 * The meta object literal for the '{@link cleaningrobots.impl.SensorImpl <em>Sensor</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cleaningrobots.impl.SensorImpl
		 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getSensor()
		 * @generated
		 */
		EClass SENSOR = eINSTANCE.getSensor();

		/**
		 * The meta object literal for the '<em><b>Get Data</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation SENSOR___GET_DATA = eINSTANCE.getSensor__GetData();

		/**
		 * The meta object literal for the '{@link cleaningrobots.Drive <em>Drive</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cleaningrobots.Drive
		 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getDrive()
		 * @generated
		 */
		EClass DRIVE = eINSTANCE.getDrive();

		/**
		 * The meta object literal for the '<em><b>Direction</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute DRIVE__DIRECTION = eINSTANCE.getDrive_Direction();

		/**
		 * The meta object literal for the '<em><b>Move</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation DRIVE___MOVE = eINSTANCE.getDrive__Move();

		/**
		 * The meta object literal for the '{@link cleaningrobots.Behaviour <em>Behaviour</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cleaningrobots.Behaviour
		 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getBehaviour()
		 * @generated
		 */
		EClass BEHAVIOUR = eINSTANCE.getBehaviour();

		/**
		 * The meta object literal for the '<em><b>Robot</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference BEHAVIOUR__ROBOT = eINSTANCE.getBehaviour_Robot();

		/**
		 * The meta object literal for the '<em><b>Action</b></em>' operation.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EOperation BEHAVIOUR___ACTION = eINSTANCE.getBehaviour__Action();

		/**
		 * The meta object literal for the '{@link cleaningrobots.impl.CleaningBehaviourImpl <em>Cleaning Behaviour</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cleaningrobots.impl.CleaningBehaviourImpl
		 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getCleaningBehaviour()
		 * @generated
		 */
		EClass CLEANING_BEHAVIOUR = eINSTANCE.getCleaningBehaviour();

		/**
		 * The meta object literal for the '{@link cleaningrobots.impl.DiscoverBehaviourImpl <em>Discover Behaviour</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cleaningrobots.impl.DiscoverBehaviourImpl
		 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getDiscoverBehaviour()
		 * @generated
		 */
		EClass DISCOVER_BEHAVIOUR = eINSTANCE.getDiscoverBehaviour();

		/**
		 * The meta object literal for the '{@link cleaningrobots.Direction <em>Direction</em>}' enum.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cleaningrobots.Direction
		 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getDirection()
		 * @generated
		 */
		EEnum DIRECTION = eINSTANCE.getDirection();

	}

} //CleaningrobotsPackage

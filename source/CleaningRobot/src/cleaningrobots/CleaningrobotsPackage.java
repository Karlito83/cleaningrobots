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
	 * The feature id for the '<em><b>World</b></em>' containment reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT__WORLD = 1;

	/**
	 * The feature id for the '<em><b>Destination</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT__DESTINATION = 2;

	/**
	 * The feature id for the '<em><b>Components</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT__COMPONENTS = 3;

	/**
	 * The feature id for the '<em><b>Roles</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT__ROLES = 4;

	/**
	 * The feature id for the '<em><b>Known States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT__KNOWN_STATES = 5;

	/**
	 * The feature id for the '<em><b>Robot Knowledge</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT__ROBOT_KNOWLEDGE = 6;

	/**
	 * The number of structural features of the '<em>Robot</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT_FEATURE_COUNT = 7;

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
	int MAP = 7;

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
	 * The meta object id for the '{@link cleaningrobots.impl.StateImpl <em>State</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cleaningrobots.impl.StateImpl
	 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getState()
	 * @generated
	 */
	int STATE = 4;

	/**
	 * The meta object id for the '{@link cleaningrobots.impl.WorldPartImpl <em>World Part</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cleaningrobots.impl.WorldPartImpl
	 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getWorldPart()
	 * @generated
	 */
	int WORLD_PART = 5;

	/**
	 * The meta object id for the '{@link cleaningrobots.impl.WorldImpl <em>World</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cleaningrobots.impl.WorldImpl
	 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getWorld()
	 * @generated
	 */
	int WORLD = 6;

	/**
	 * The meta object id for the '{@link cleaningrobots.impl.RobotKnowledgeImpl <em>Robot Knowledge</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cleaningrobots.impl.RobotKnowledgeImpl
	 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getRobotKnowledge()
	 * @generated
	 */
	int ROBOT_KNOWLEDGE = 1;

	/**
	 * The feature id for the '<em><b>Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT_KNOWLEDGE__NAME = 0;

	/**
	 * The feature id for the '<em><b>Last Arrange</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT_KNOWLEDGE__LAST_ARRANGE = 1;

	/**
	 * The feature id for the '<em><b>Destination</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT_KNOWLEDGE__DESTINATION = 2;

	/**
	 * The feature id for the '<em><b>Components</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT_KNOWLEDGE__COMPONENTS = 3;

	/**
	 * The feature id for the '<em><b>Roles</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT_KNOWLEDGE__ROLES = 4;

	/**
	 * The feature id for the '<em><b>Know States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT_KNOWLEDGE__KNOW_STATES = 5;

	/**
	 * The number of structural features of the '<em>Robot Knowledge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT_KNOWLEDGE_FEATURE_COUNT = 6;

	/**
	 * The number of operations of the '<em>Robot Knowledge</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROBOT_KNOWLEDGE_OPERATION_COUNT = 0;

	/**
	 * The feature id for the '<em><b>States</b></em>' containment reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__STATES = 0;

	/**
	 * The feature id for the '<em><b>Pos</b></em>' reference.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD__POS = 1;

	/**
	 * The number of structural features of the '<em>Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Field</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FIELD_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link cleaningrobots.impl.PositionImpl <em>Position</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cleaningrobots.impl.PositionImpl
	 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getPosition()
	 * @generated
	 */
	int POSITION = 3;

	/**
	 * The feature id for the '<em><b>Xpos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION__XPOS = 0;

	/**
	 * The feature id for the '<em><b>Ypos</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION__YPOS = 1;

	/**
	 * The number of structural features of the '<em>Position</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION_FEATURE_COUNT = 2;

	/**
	 * The number of operations of the '<em>Position</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int POSITION_OPERATION_COUNT = 0;

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
	 * The feature id for the '<em><b>World States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD_PART__WORLD_STATES = 2;

	/**
	 * The number of structural features of the '<em>World Part</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD_PART_FEATURE_COUNT = 3;

	/**
	 * The number of operations of the '<em>World Part</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD_PART_OPERATION_COUNT = 0;

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
	 * The feature id for the '<em><b>World States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int WORLD__WORLD_STATES = WORLD_PART__WORLD_STATES;

	/**
	 * The feature id for the '<em><b>Children</b></em>' containment reference list.
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
	 * The feature id for the '<em><b>World States</b></em>' reference list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MAP__WORLD_STATES = WORLD_PART__WORLD_STATES;

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
	 * The meta object id for the '{@link cleaningrobots.impl.RoleImpl <em>Role</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cleaningrobots.impl.RoleImpl
	 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getRole()
	 * @generated
	 */
	int ROLE = 8;

	/**
	 * The number of structural features of the '<em>Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_FEATURE_COUNT = 0;

	/**
	 * The number of operations of the '<em>Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int ROLE_OPERATION_COUNT = 0;

	/**
	 * The meta object id for the '{@link cleaningrobots.impl.FollowerRoleImpl <em>Follower Role</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cleaningrobots.impl.FollowerRoleImpl
	 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getFollowerRole()
	 * @generated
	 */
	int FOLLOWER_ROLE = 9;

	/**
	 * The feature id for the '<em><b>Master Name</b></em>' attribute.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOLLOWER_ROLE__MASTER_NAME = ROLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Follower Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOLLOWER_ROLE_FEATURE_COUNT = ROLE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Follower Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int FOLLOWER_ROLE_OPERATION_COUNT = ROLE_OPERATION_COUNT + 0;

	/**
	 * The meta object id for the '{@link cleaningrobots.impl.MasterRoleImpl <em>Master Role</em>}' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @see cleaningrobots.impl.MasterRoleImpl
	 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getMasterRole()
	 * @generated
	 */
	int MASTER_ROLE = 10;

	/**
	 * The feature id for the '<em><b>Follower Names</b></em>' attribute list.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASTER_ROLE__FOLLOWER_NAMES = ROLE_FEATURE_COUNT + 0;

	/**
	 * The number of structural features of the '<em>Master Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASTER_ROLE_FEATURE_COUNT = ROLE_FEATURE_COUNT + 1;

	/**
	 * The number of operations of the '<em>Master Role</em>' class.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 * @ordered
	 */
	int MASTER_ROLE_OPERATION_COUNT = ROLE_OPERATION_COUNT + 0;


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
	 * Returns the meta object for the containment reference '{@link cleaningrobots.Robot#getWorld <em>World</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference '<em>World</em>'.
	 * @see cleaningrobots.Robot#getWorld()
	 * @see #getRobot()
	 * @generated
	 */
	EReference getRobot_World();

	/**
	 * Returns the meta object for the reference '{@link cleaningrobots.Robot#getDestination <em>Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Destination</em>'.
	 * @see cleaningrobots.Robot#getDestination()
	 * @see #getRobot()
	 * @generated
	 */
	EReference getRobot_Destination();

	/**
	 * Returns the meta object for the attribute list '{@link cleaningrobots.Robot#getComponents <em>Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Components</em>'.
	 * @see cleaningrobots.Robot#getComponents()
	 * @see #getRobot()
	 * @generated
	 */
	EAttribute getRobot_Components();

	/**
	 * Returns the meta object for the reference list '{@link cleaningrobots.Robot#getRoles <em>Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Roles</em>'.
	 * @see cleaningrobots.Robot#getRoles()
	 * @see #getRobot()
	 * @generated
	 */
	EReference getRobot_Roles();

	/**
	 * Returns the meta object for the reference list '{@link cleaningrobots.Robot#getRobotKnowledge <em>Robot Knowledge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Robot Knowledge</em>'.
	 * @see cleaningrobots.Robot#getRobotKnowledge()
	 * @see #getRobot()
	 * @generated
	 */
	EReference getRobot_RobotKnowledge();

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
	 * Returns the meta object for class '{@link cleaningrobots.RobotKnowledge <em>Robot Knowledge</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Robot Knowledge</em>'.
	 * @see cleaningrobots.RobotKnowledge
	 * @generated
	 */
	EClass getRobotKnowledge();

	/**
	 * Returns the meta object for the attribute '{@link cleaningrobots.RobotKnowledge#getName <em>Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Name</em>'.
	 * @see cleaningrobots.RobotKnowledge#getName()
	 * @see #getRobotKnowledge()
	 * @generated
	 */
	EAttribute getRobotKnowledge_Name();

	/**
	 * Returns the meta object for the reference '{@link cleaningrobots.RobotKnowledge#getDestination <em>Destination</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Destination</em>'.
	 * @see cleaningrobots.RobotKnowledge#getDestination()
	 * @see #getRobotKnowledge()
	 * @generated
	 */
	EReference getRobotKnowledge_Destination();

	/**
	 * Returns the meta object for the attribute list '{@link cleaningrobots.RobotKnowledge#getComponents <em>Components</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Components</em>'.
	 * @see cleaningrobots.RobotKnowledge#getComponents()
	 * @see #getRobotKnowledge()
	 * @generated
	 */
	EAttribute getRobotKnowledge_Components();

	/**
	 * Returns the meta object for the attribute '{@link cleaningrobots.RobotKnowledge#getLastArrange <em>Last Arrange</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Last Arrange</em>'.
	 * @see cleaningrobots.RobotKnowledge#getLastArrange()
	 * @see #getRobotKnowledge()
	 * @generated
	 */
	EAttribute getRobotKnowledge_LastArrange();

	/**
	 * Returns the meta object for the reference list '{@link cleaningrobots.RobotKnowledge#getRoles <em>Roles</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Roles</em>'.
	 * @see cleaningrobots.RobotKnowledge#getRoles()
	 * @see #getRobotKnowledge()
	 * @generated
	 */
	EReference getRobotKnowledge_Roles();

	/**
	 * Returns the meta object for the reference list '{@link cleaningrobots.RobotKnowledge#getKnowStates <em>Know States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>Know States</em>'.
	 * @see cleaningrobots.RobotKnowledge#getKnowStates()
	 * @see #getRobotKnowledge()
	 * @generated
	 */
	EReference getRobotKnowledge_KnowStates();

	/**
	 * Returns the meta object for class '{@link cleaningrobots.Position <em>Position</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Position</em>'.
	 * @see cleaningrobots.Position
	 * @generated
	 */
	EClass getPosition();

	/**
	 * Returns the meta object for the attribute '{@link cleaningrobots.Position#getXpos <em>Xpos</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Xpos</em>'.
	 * @see cleaningrobots.Position#getXpos()
	 * @see #getPosition()
	 * @generated
	 */
	EAttribute getPosition_Xpos();

	/**
	 * Returns the meta object for the attribute '{@link cleaningrobots.Position#getYpos <em>Ypos</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Ypos</em>'.
	 * @see cleaningrobots.Position#getYpos()
	 * @see #getPosition()
	 * @generated
	 */
	EAttribute getPosition_Ypos();

	/**
	 * Returns the meta object for class '{@link cleaningrobots.Role <em>Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Role</em>'.
	 * @see cleaningrobots.Role
	 * @generated
	 */
	EClass getRole();

	/**
	 * Returns the meta object for class '{@link cleaningrobots.FollowerRole <em>Follower Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Follower Role</em>'.
	 * @see cleaningrobots.FollowerRole
	 * @generated
	 */
	EClass getFollowerRole();

	/**
	 * Returns the meta object for the attribute '{@link cleaningrobots.FollowerRole#getMasterName <em>Master Name</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute '<em>Master Name</em>'.
	 * @see cleaningrobots.FollowerRole#getMasterName()
	 * @see #getFollowerRole()
	 * @generated
	 */
	EAttribute getFollowerRole_MasterName();

	/**
	 * Returns the meta object for class '{@link cleaningrobots.MasterRole <em>Master Role</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for class '<em>Master Role</em>'.
	 * @see cleaningrobots.MasterRole
	 * @generated
	 */
	EClass getMasterRole();

	/**
	 * Returns the meta object for the attribute list '{@link cleaningrobots.MasterRole#getFollowerNames <em>Follower Names</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the attribute list '<em>Follower Names</em>'.
	 * @see cleaningrobots.MasterRole#getFollowerNames()
	 * @see #getMasterRole()
	 * @generated
	 */
	EAttribute getMasterRole_FollowerNames();

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
	 * Returns the meta object for the containment reference list '{@link cleaningrobots.Field#getStates <em>States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>States</em>'.
	 * @see cleaningrobots.Field#getStates()
	 * @see #getField()
	 * @generated
	 */
	EReference getField_States();

	/**
	 * Returns the meta object for the reference '{@link cleaningrobots.Field#getPos <em>Pos</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference '<em>Pos</em>'.
	 * @see cleaningrobots.Field#getPos()
	 * @see #getField()
	 * @generated
	 */
	EReference getField_Pos();

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
	 * Returns the meta object for the reference list '{@link cleaningrobots.WorldPart#getWorldStates <em>World States</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the reference list '<em>World States</em>'.
	 * @see cleaningrobots.WorldPart#getWorldStates()
	 * @see #getWorldPart()
	 * @generated
	 */
	EReference getWorldPart_WorldStates();

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
	 * Returns the meta object for the containment reference list '{@link cleaningrobots.World#getChildren <em>Children</em>}'.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @return the meta object for the containment reference list '<em>Children</em>'.
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
		 * The meta object literal for the '<em><b>World</b></em>' containment reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROBOT__WORLD = eINSTANCE.getRobot_World();

		/**
		 * The meta object literal for the '<em><b>Destination</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROBOT__DESTINATION = eINSTANCE.getRobot_Destination();

		/**
		 * The meta object literal for the '<em><b>Components</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROBOT__COMPONENTS = eINSTANCE.getRobot_Components();

		/**
		 * The meta object literal for the '<em><b>Roles</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROBOT__ROLES = eINSTANCE.getRobot_Roles();

		/**
		 * The meta object literal for the '<em><b>Robot Knowledge</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROBOT__ROBOT_KNOWLEDGE = eINSTANCE.getRobot_RobotKnowledge();

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
		 * The meta object literal for the '{@link cleaningrobots.impl.RobotKnowledgeImpl <em>Robot Knowledge</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cleaningrobots.impl.RobotKnowledgeImpl
		 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getRobotKnowledge()
		 * @generated
		 */
		EClass ROBOT_KNOWLEDGE = eINSTANCE.getRobotKnowledge();

		/**
		 * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROBOT_KNOWLEDGE__NAME = eINSTANCE.getRobotKnowledge_Name();

		/**
		 * The meta object literal for the '<em><b>Destination</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROBOT_KNOWLEDGE__DESTINATION = eINSTANCE.getRobotKnowledge_Destination();

		/**
		 * The meta object literal for the '<em><b>Components</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROBOT_KNOWLEDGE__COMPONENTS = eINSTANCE.getRobotKnowledge_Components();

		/**
		 * The meta object literal for the '<em><b>Last Arrange</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute ROBOT_KNOWLEDGE__LAST_ARRANGE = eINSTANCE.getRobotKnowledge_LastArrange();

		/**
		 * The meta object literal for the '<em><b>Roles</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROBOT_KNOWLEDGE__ROLES = eINSTANCE.getRobotKnowledge_Roles();

		/**
		 * The meta object literal for the '<em><b>Know States</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference ROBOT_KNOWLEDGE__KNOW_STATES = eINSTANCE.getRobotKnowledge_KnowStates();

		/**
		 * The meta object literal for the '{@link cleaningrobots.impl.PositionImpl <em>Position</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cleaningrobots.impl.PositionImpl
		 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getPosition()
		 * @generated
		 */
		EClass POSITION = eINSTANCE.getPosition();

		/**
		 * The meta object literal for the '<em><b>Xpos</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POSITION__XPOS = eINSTANCE.getPosition_Xpos();

		/**
		 * The meta object literal for the '<em><b>Ypos</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute POSITION__YPOS = eINSTANCE.getPosition_Ypos();

		/**
		 * The meta object literal for the '{@link cleaningrobots.impl.RoleImpl <em>Role</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cleaningrobots.impl.RoleImpl
		 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getRole()
		 * @generated
		 */
		EClass ROLE = eINSTANCE.getRole();

		/**
		 * The meta object literal for the '{@link cleaningrobots.impl.FollowerRoleImpl <em>Follower Role</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cleaningrobots.impl.FollowerRoleImpl
		 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getFollowerRole()
		 * @generated
		 */
		EClass FOLLOWER_ROLE = eINSTANCE.getFollowerRole();

		/**
		 * The meta object literal for the '<em><b>Master Name</b></em>' attribute feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute FOLLOWER_ROLE__MASTER_NAME = eINSTANCE.getFollowerRole_MasterName();

		/**
		 * The meta object literal for the '{@link cleaningrobots.impl.MasterRoleImpl <em>Master Role</em>}' class.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @see cleaningrobots.impl.MasterRoleImpl
		 * @see cleaningrobots.impl.CleaningrobotsPackageImpl#getMasterRole()
		 * @generated
		 */
		EClass MASTER_ROLE = eINSTANCE.getMasterRole();

		/**
		 * The meta object literal for the '<em><b>Follower Names</b></em>' attribute list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EAttribute MASTER_ROLE__FOLLOWER_NAMES = eINSTANCE.getMasterRole_FollowerNames();

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
		 * The meta object literal for the '<em><b>States</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIELD__STATES = eINSTANCE.getField_States();

		/**
		 * The meta object literal for the '<em><b>Pos</b></em>' reference feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference FIELD__POS = eINSTANCE.getField_Pos();

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
		 * The meta object literal for the '<em><b>World States</b></em>' reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORLD_PART__WORLD_STATES = eINSTANCE.getWorldPart_WorldStates();

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
		 * The meta object literal for the '<em><b>Children</b></em>' containment reference list feature.
		 * <!-- begin-user-doc -->
		 * <!-- end-user-doc -->
		 * @generated
		 */
		EReference WORLD__CHILDREN = eINSTANCE.getWorld_Children();

	}

} //CleaningrobotsPackage

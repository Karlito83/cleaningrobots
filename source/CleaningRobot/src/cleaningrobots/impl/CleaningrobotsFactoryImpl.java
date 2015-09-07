/**
 */
package cleaningrobots.impl;

import cleaningrobots.*;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.EPackage;

import org.eclipse.emf.ecore.impl.EFactoryImpl;

import org.eclipse.emf.ecore.plugin.EcorePlugin;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model <b>Factory</b>.
 * <!-- end-user-doc -->
 * @generated
 */
public class CleaningrobotsFactoryImpl extends EFactoryImpl implements CleaningrobotsFactory {
	/**
	 * Creates the default factory implementation.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public static CleaningrobotsFactory init() {
		try {
			CleaningrobotsFactory theCleaningrobotsFactory = (CleaningrobotsFactory)EPackage.Registry.INSTANCE.getEFactory(CleaningrobotsPackage.eNS_URI);
			if (theCleaningrobotsFactory != null) {
				return theCleaningrobotsFactory;
			}
		}
		catch (Exception exception) {
			EcorePlugin.INSTANCE.log(exception);
		}
		return new CleaningrobotsFactoryImpl();
	}

	/**
	 * Creates an instance of the factory.
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CleaningrobotsFactoryImpl() {
		super();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	@Override
	public EObject create(EClass eClass) {
		switch (eClass.getClassifierID()) {
			case CleaningrobotsPackage.ROBOT: return createRobot();
			case CleaningrobotsPackage.ROBOT_KNOWLEDGE: return createRobotKnowledge();
			case CleaningrobotsPackage.FIELD: return createField();
			case CleaningrobotsPackage.POSITION: return createPosition();
			case CleaningrobotsPackage.STATE: return createState();
			case CleaningrobotsPackage.WORLD_PART: return createWorldPart();
			case CleaningrobotsPackage.WORLD: return createWorld();
			case CleaningrobotsPackage.MAP: return createMap();
			case CleaningrobotsPackage.FOLLOWER_ROLE: return createFollowerRole();
			case CleaningrobotsPackage.MASTER_ROLE: return createMasterRole();
			default:
				throw new IllegalArgumentException("The class '" + eClass.getName() + "' is not a valid classifier");
		}
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Robot createRobot() {
		RobotImpl robot = new RobotImpl();
		return robot;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Map createMap() {
		MapImpl map = new MapImpl();
		return map;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public RobotKnowledge createRobotKnowledge() {
		RobotKnowledgeImpl robotKnowledge = new RobotKnowledgeImpl();
		return robotKnowledge;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Position createPosition() {
		PositionImpl position = new PositionImpl();
		return position;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public FollowerRole createFollowerRole() {
		FollowerRoleImpl followerRole = new FollowerRoleImpl();
		return followerRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public MasterRole createMasterRole() {
		MasterRoleImpl masterRole = new MasterRoleImpl();
		return masterRole;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public Field createField() {
		FieldImpl field = new FieldImpl();
		return field;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public State createState() {
		StateImpl state = new StateImpl();
		return state;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public WorldPart createWorldPart() {
		WorldPartImpl worldPart = new WorldPartImpl();
		return worldPart;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public World createWorld() {
		WorldImpl world = new WorldImpl();
		return world;
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @generated
	 */
	public CleaningrobotsPackage getCleaningrobotsPackage() {
		return (CleaningrobotsPackage)getEPackage();
	}

	/**
	 * <!-- begin-user-doc -->
	 * <!-- end-user-doc -->
	 * @deprecated
	 * @generated
	 */
	@Deprecated
	public static CleaningrobotsPackage getPackage() {
		return CleaningrobotsPackage.eINSTANCE;
	}

} //CleaningrobotsFactoryImpl

package cleaningrobots.generator;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.imageio.ImageIO;

import org.eclipse.emf.common.util.URI;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.ecore.resource.Resource;
import org.eclipse.emf.ecore.resource.ResourceSet;
import org.eclipse.emf.ecore.resource.impl.ResourceImpl;
import org.eclipse.emf.ecore.resource.impl.ResourceSetImpl;
import org.eclipse.emf.ecore.xmi.XMLResource;
import org.eclipse.emf.ecore.xmi.impl.XMLResourceFactoryImpl;

import cleaningrobots.CleaningrobotsFactory;
import cleaningrobots.Field;
import cleaningrobots.Map;
import cleaningrobots.Position;
import cleaningrobots.Robot;
import cleaningrobots.State;

public class ScenarioGenerator {
	public static void main(String[] args) {
		Random r = new Random();
		
		ScenarioGenerator scGe = new ScenarioGenerator();
		//load real map
		boolean[][] fullMap = scGe.readMap("input/map.png");		
		
		for (int nr = 1; nr <= 10; nr++) {
			//randomly set a position for the robot
			int rx, ry;
			do {
				rx = r.nextInt(fullMap.length);
				ry = r.nextInt(fullMap.length);
			} while(!fullMap[rx][ry]);

			int realMapSize = fullMap.length;
			int visionRadius = realMapSize / 4;
			
			int startX = rx - (visionRadius/2);
			int startY = ry - (visionRadius/2);
			int stopX = rx + (visionRadius/2);
			int stopY = ry + (visionRadius/2);
			if(startX < 0) startX = 0;
			if(startY < 0) startY = 0;
			if(stopX >= realMapSize) stopX = realMapSize-1;
			if(stopY >= realMapSize) stopY = realMapSize-1;
			
			Robot robby = CleaningrobotsFactory.eINSTANCE.createRobot();
			robby.setName("Robot_" + nr);

			Map m = CleaningrobotsFactory.eINSTANCE.createMap();
			robby.setWorld(m);

			State s1 = CleaningrobotsFactory.eINSTANCE.createState();
			s1.setName("Clean");
			State s2 = CleaningrobotsFactory.eINSTANCE.createState();
			s2.setName("Dirty");
			State s3 = CleaningrobotsFactory.eINSTANCE.createState();
			s3.setName("Free");
			State s4 = CleaningrobotsFactory.eINSTANCE.createState();
			s4.setName("Blocked");

			
			State a, b;
			if (r.nextBoolean()) {
				a = s1;
				b = s2;
			} else {
				a = s3;
				b = s4;
			}

			robby.getKnownStates().add(a);
			robby.getKnownStates().add(b);

			//int mapsize = r.nextInt(10) + 5;
			m.setXdim(stopX - startX);
			m.setYdim(stopY - startY);

			for (int x = startX; x <= stopX; x++) {
				for (int y = startY; y <= stopY; y++) {
					Field f = CleaningrobotsFactory.eINSTANCE.createField();
					Position p = CleaningrobotsFactory.eINSTANCE.createPosition();
					p.setXpos(x);
					p.setYpos(y);
					f.setPos(p);//f.setXpos(x);f.setYpos(y);
					if (fullMap[x][y])
						f.getStates().add(a);
					else
						f.getStates().add(b);
					//TODO: randomly insert sensor faults (i.e., flip between a and b)
					m.getFields().add(f);
				}
			}

			ResourceSet rs = new ResourceSetImpl();
			Resource res = scGe.createAndAddResource("output/robot"+nr+".cleaningrobots", "cleaningrobots", rs);
			res.getContents().add(robby);
			java.util.Map<Object,Object> saveOptions = ((XMLResource)res).getDefaultSaveOptions();
		     saveOptions.put(XMLResource.OPTION_CONFIGURATION_CACHE, Boolean.TRUE);
		     saveOptions.put(XMLResource.OPTION_USE_CACHED_LOOKUP_TABLE, new ArrayList<>());
		     try {
		        res.save(saveOptions);
		     } catch (IOException e) {
		        throw new RuntimeException(e);
		     }
		}
	}
	
	public boolean[][] readMap(String file) {
		
		try {
			BufferedImage img = ImageIO.read(new File(file));
			int maxy = img.getHeight();
			int maxx = img.getWidth();
			boolean[][] ret = new boolean[maxx][maxy];
			for(int x = 0; x < maxx; x++) {
				for(int y = 0; y < maxy; y++) {
					int col = img.getRGB(x, y);
					ret[x][y] = (col == -1 ? false : true);
				}
			}
			
			return ret;
		} catch (IOException e) {
			e.printStackTrace();
		} 
		return null;
	}

	public Resource createAndAddResource(String outputFile, String ext,
			ResourceSet rs) {
		rs.getResourceFactoryRegistry().getExtensionToFactoryMap()
				.put(ext, new XMLResourceFactoryImpl());
		URI uri = URI.createFileURI(outputFile);
		Resource resource = rs.createResource(uri);
		((ResourceImpl) resource).setIntrinsicIDToEObjectMap(new HashMap<String, EObject>());
		return resource;

	}
}

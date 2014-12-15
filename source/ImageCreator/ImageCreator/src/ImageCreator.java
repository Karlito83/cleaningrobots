import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;


public class ImageCreator {
	static Random rand = new Random();
	
	public static void main(String[] args) {
		BufferedImage image = new BufferedImage(640, 480, BufferedImage.TYPE_BYTE_GRAY);
		
		for(int x=0; x<image.getWidth(); x++)
		{
			for(int y=0; y<image.getHeight(); y++)
			{
				image.setRGB(x, y, rand.nextFloat()>=0.8?Color.BLACK.getRGB():Color.WHITE.getRGB());
			}
		}
		
		try {
			File f = new File("output" + System.getProperty("file.separator") + "test.png");
			ImageIO.write(image, "png", f);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}

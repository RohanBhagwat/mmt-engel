package applications;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

public class DisplayImage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		if (args.length < 1) {
			System.err.println("select a file to display by commandlineargument.");
		}
		File f = new File(args[0]);

		if (!f.exists() || !f.isFile()) {
			System.err.println(f.getName() + "; File is invalid!");
		}
		
		// Image lesen und ausgeben.
		try {
			Image img = ImageIO.read(f);
			Graphics g;
			
			g.draw
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}

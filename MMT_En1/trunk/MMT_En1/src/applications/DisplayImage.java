package applications;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;

import javax.imageio.ImageIO;

/**
 * this is an optional programm used to display images. <br>
 * doesn´t work at the moment (no time).
 * @author Mürzl Harald
 *
 */
public class DisplayImage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO getting familiar with ImageObserver and creating code
		if (args.length < 1) {
			System.err.println("select a file to display by commandlineargument.");
		}
		File f = new File(args[0]);

		if (!f.exists() || !f.isFile()) {
			System.err.println(f.getName() + "; File is invalid!");
		}
		
		// Image lesen und ausgeben.
		try {
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}

}

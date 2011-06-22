package applications;

import java.io.File;
import java.io.IOException;

import mmt_image.FileImageReader;
import mmt_image.FileImageWriter;
import mmt_image.MMTImage;

/**
 * this class converts an image to another format.
 * usage: Converter [path]
 * @author Mürzl Harald
 *
 */
public class Converter {

	/**
	 * converts all images in a given directory to png format. <br>
	 * usage: Converter [path]
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			System.err.println("select a directory by commandlineparameter.");
			return;
		}
		File dir = new File(args[0]);
		
		// check directory
		if (!dir.isDirectory() || !dir.exists()) {
			System.err.println(dir.getName() + " invalid directory.");
			return;
		}
		
		// get picturefiles and save as *.png
		try {
		for (File f : dir.listFiles()) {
				MMTImage img = FileImageReader.read(f.getAbsolutePath());
				FileImageWriter.write(img, img.getName() + ".png");
			}
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}

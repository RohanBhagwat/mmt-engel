package applications;

import java.io.File;
import java.io.IOException;

import mmt_image.FileImageReader;
import mmt_image.FileImageWriter;
import mmt_image.MMTImage;

public class Converter {

	/**
	 * converts images in a given directory to png format.
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if (args.length < 1) {
			System.err.println("select an directory to convert by commandlineparameter.");
		}
		File dir = new File(args[0]);
		
		// check directory
		if (!dir.isDirectory() || !dir.exists()) {
			System.err.println(dir.getName() + " invalid directory.");
		}
		
		// get picturefiles
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

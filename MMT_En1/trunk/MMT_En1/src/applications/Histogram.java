package applications;

import java.io.File;
import java.io.IOException;

import mmt_image.FileImageReader;
import mmt_image.MMTImage;
import mmt_image.MMTImageComputer;

/**
 * generates a textual output of the histogram of an image, <br>
 * usage: Histogram [picturepath] <br>
 * the histogram is print to standard output.
 * @author Mürzl Harald
 *
 */
public class Histogram {

	/**
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length < 1) {
			System.err.println("set the image for creating a histogramm by commandline.");
			return;
		}
		File im = new File(args[0]);
		
		// check directory
		if (!im.isFile() || !im.exists()) {
			System.err.println(im.getName() + " --> invalid file.");
			return;
		}

		// open picture and print histogramm
		try {
			MMTImage img = FileImageReader.read(im.getAbsolutePath());
			int[] hist = MMTImageComputer.getHistogram(img);
			System.out.println("Histogramm of " + im.getName());
			for (int i=0; i<hist.length; i++) {
				System.out.println(i + ": " + hist[i]);
			}
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}

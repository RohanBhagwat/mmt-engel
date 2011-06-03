package applications;

import image_filter.AveragingFilter;
import image_filter.BorderHandling;

import java.io.File;
import java.io.IOException;

import mmt_image.FileImageReader;
import mmt_image.FileImageWriter;
import mmt_image.MMTImage;

/**
 * commandline programm for applying an averaging filter on an image.
 * filter coefficients are all 1.
 * outputfile is averagedImage.png
 * usage: UseAveragingFilter <imagepath>
 * @author muetze
 *
 */
public class UseAveragingFilter {

	public static void main(String[] args) {
		// TODO add code to read the filtercoefficieants from a file.
		
		if (args.length < 1) {
			System.err.println("set the image for applying an averaging filter.");
		}
		File im = new File(args[0]);
		
		// check directory
		if (!im.isFile() || !im.exists()) {
			System.err.println(im.getName() + " --> invalid file.");
		}

		// create filter mask
		int width = 15;
		MMTImage coef = new MMTImage(width,width);
		// set coefficients for averaging filter
		int[] cfvals = new int[width*width];
		for (int i=0; i<cfvals.length; i++) {
			cfvals[i] = 1;
		}
		coef.setData(cfvals);

		// open picture and average it
		try {
			MMTImage img = FileImageReader.read(im.getAbsolutePath());
			AveragingFilter af = new AveragingFilter(BorderHandling.PARTIAL, coef);
			MMTImage averaged = af.applyFilter(img);
			
			// save new Picture
			FileImageWriter.write(averaged, "averagedImage.png");
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}

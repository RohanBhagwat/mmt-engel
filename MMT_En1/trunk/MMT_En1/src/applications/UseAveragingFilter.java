package applications;

import image_filter.AveragingFilter;
import image_filter.BorderHandling;

import java.io.File;
import java.io.IOException;

import mmt_image.FileImageReader;
import mmt_image.FileImageWriter;
import mmt_image.MMTImage;

/**
 * command line program for applying an averaging filter to an image. <br>
 * usage: UseAveragingFilter [imagepath] [outputfile]<br><br>
 * image............the image to be transformed.<br>
 * outputfile.......specifies the location to save the transformed image.<br>
 * @author Mürzl Harald
 *
 */
public class UseAveragingFilter {

	public static void main(String[] args) {
		
		if (args.length < 2) {
			System.err.println("set the image for applying an averaging filter and the outputfile.");
			return;
		}
		File im = new File(args[0]);
		
		// check directory
		if (!im.isFile() || !im.exists()) {
			System.err.println(im.getName() + " --> invalid file.");
			return;
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
			FileImageWriter.write(averaged, args[1]);
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}

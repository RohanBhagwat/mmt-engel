package applications;

import image_filter.BorderHandling;
import image_filter.MedianFilter;

import java.io.File;
import java.io.IOException;

import mmt_image.FileImageReader;
import mmt_image.FileImageWriter;
import mmt_image.MMTImage;

/**
 * command line program for applying an median filter to an image.
 * 
 * usage: UseMedianFilter [image] [outputfile] [BorderHandling] [filtersize] <br>
 * BorderHandling can be partial, padding or limiting. partial is the default value. <br>
 * for detailed information please see documentation of BorderHandling enumeration.
 * 
 * @author Mürzl Harald
 *
 */
public class UseMedianFilter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length < 2) {
			System.err.println("set the image for applying an median filter and the outputfile.");
			return;
		}
		File im = new File(args[0]);
		BorderHandling bh = BorderHandling.PARTIAL;
		
		// check if borderhandling is given
		if (args.length == 3) {
			if (args[2].equals("partial") || args[2].equals("padding") || args[2].equals("limiting")) {
				bh = BorderHandling.valueOf(args[2].toUpperCase());
			}
		}
		
		// check directory
		if (!im.isFile() || !im.exists()) {
			System.err.println(im.getName() + " --> invalid file.");
			return;
		}

		// create filter mask
		int width = (args.length == 3) ? Integer.parseInt(args[2]) : 3;

		// open picture and use median filter
		try {
			MMTImage img = FileImageReader.read(im.getAbsolutePath());
			MedianFilter mf = new MedianFilter(bh, width);
			MMTImage averaged = mf.applyFilter(img);
			
			// save new Picture
			FileImageWriter.write(averaged, args[1]);
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}

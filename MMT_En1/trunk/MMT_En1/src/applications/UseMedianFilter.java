package applications;

import image_filter.BorderHandling;
import image_filter.MedianFilter;

import java.io.File;
import java.io.IOException;

import mmt_image.FileImageReader;
import mmt_image.FileImageWriter;
import mmt_image.MMTImage;

/**
 * commandline programm for applying an median filter to an image.
 * 
 * usage: UseMedianFilter <image> <BorderHandling>
 * BorderHandling can be partial, padding or limiting. partial is the defaultvalue.
 * 
 * outputfile is medianImage.png
 * @author Mürzl Harald
 *
 */
public class UseMedianFilter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length < 1) {
			System.err.println("set the image for applying an median filter.");
		}
		File im = new File(args[0]);
		BorderHandling bh = BorderHandling.PARTIAL;
		
		// check if borderhandling is given
		if (args.length == 2) {
			if (args[1].equals("partial") || args[1].equals("padding") || args[1].equals("limiting")) {
				bh = BorderHandling.valueOf(args[1].toUpperCase());
			}
		}
		
		// check directory
		if (!im.isFile() || !im.exists()) {
			System.err.println(im.getName() + " --> invalid file.");
		}

		// create filter mask
		int width = 3;

		// open picture and use median filter
		try {
			MMTImage img = FileImageReader.read(im.getAbsolutePath());
			MedianFilter mf = new MedianFilter(bh, width);
			MMTImage averaged = mf.applyFilter(img);
			
			// save new Picture
			FileImageWriter.write(averaged, "medianImage.png");
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}

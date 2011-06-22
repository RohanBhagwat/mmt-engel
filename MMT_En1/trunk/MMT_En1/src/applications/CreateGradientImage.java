package applications;

import image_filter.BorderHandling;
import image_filter.SobelOperator;

import java.io.File;
import java.io.IOException;

import mmt_image.FileImageReader;
import mmt_image.FileImageWriter;
import mmt_image.MMTImage;

/**
 * command line program for applying the sobel operator to an image.
 * 
 * usage: CreateGradientImage [image] [outputfile] [BorderHandling] <br><br>
 * image............the image to be transformed.<br>
 * outputfile.......specifies the location to save the transformed image.<br>
 * BorderHandling...can be partial, padding or limiting. partial is the default value. <br>
 * 
 * @author Mürzl Harald
 *
 */
public class CreateGradientImage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length < 2) {
			System.err.println("set the image for applying the sobel operator and the output file.");
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

		// open picture and sobel it
		try {
			MMTImage img = FileImageReader.read(im.getAbsolutePath());
			
			SobelOperator so = new SobelOperator(bh);
			MMTImage gradient = so.applyFilter(img);
			
			// limit picture to grayvalue range
			gradient.setData(gradient.getLimited_0_255());
			// save new Picture
			FileImageWriter.write(gradient, args[1]);
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
}

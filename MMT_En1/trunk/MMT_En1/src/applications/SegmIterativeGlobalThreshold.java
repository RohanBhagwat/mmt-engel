package applications;

import java.io.File;
import java.io.IOException;

import segmentation.ImageSegmenter;

import mmt_image.FileImageReader;
import mmt_image.FileImageWriter;
import mmt_image.MMTImage;

/**
 * command line program for creating a segmentation mask using iterative global thresholding. <br>
 * 
 * usage: SegmIterativeGlobalThreshold [image] [outputfile]<br><br>
 * image............the image to be transformed.<br>
 * outputfile.......specifies the location to save the transformed image.<br>
 * 
 * @author Mürzl Harald
 *
 */
public class SegmIterativeGlobalThreshold {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		if (args.length < 2) {
			System.err.println("set the image for applying iterative Global Threshold segmentation.");
			return;
		}
		File im = new File(args[0]);
		
		// check directory
		if (!im.isFile() || !im.exists()) {
			System.err.println(im.getName() + " --> invalid file.");
			return;
		}

		// open picture and apply glob threshold
		try {
			MMTImage img = FileImageReader.read(im.getAbsolutePath());
			
			ImageSegmenter gt = new ImageSegmenter();
			MMTImage thimg = gt.applyIterativeGlobalThreshold(img);
			
			FileImageWriter.write(thimg, args[1]);
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
}

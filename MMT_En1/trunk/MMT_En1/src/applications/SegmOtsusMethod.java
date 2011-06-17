package applications;

import java.io.File;
import java.io.IOException;

import segmentation.ImageSegmenter;

import mmt_image.FileImageReader;
import mmt_image.FileImageWriter;
import mmt_image.MMTImage;

/**
 * commandline program for segmenting an image using otsus method.
 * syntax: SegmOtsusMethod <inputfile>
 * @author M�rzl Harald
 *
 */
public class SegmOtsusMethod {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	
		if (args.length < 1) {
			System.err.println("set the image for applying Global Threshold segmentation.");
		}
		File im = new File(args[0]);
		
		// check directory
		if (!im.isFile() || !im.exists()) {
			System.err.println(im.getName() + " --> invalid file.");
		}

		// open picture and apply glob threshold
		try {
			MMTImage img = FileImageReader.read(im.getAbsolutePath());
			
			ImageSegmenter gt = new ImageSegmenter();
			MMTImage thimg = gt.applyOtsusMethod(img);
			
			FileImageWriter.write(thimg, "GlobThreshold_otsu.png");
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
}
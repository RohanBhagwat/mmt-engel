package applications;

import java.io.File;
import java.io.IOException;

import segmentation.GlobalThreshold;

import mmt_image.FileImageReader;
import mmt_image.FileImageWriter;
import mmt_image.MMTImage;

public class SegmIterativeGlobalThreshold {

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
			
			GlobalThreshold gt = new GlobalThreshold();
			MMTImage thimg = gt.applyIterativeGlobalThreshold(img);
			
			FileImageWriter.write(thimg, "GlobThreshold_iterative.png");
		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}
}

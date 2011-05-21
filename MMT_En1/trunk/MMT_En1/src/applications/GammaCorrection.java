package applications;

import java.io.File;
import java.io.IOException;

import mmt_image.FileImageReader;
import mmt_image.FileImageWriter;
import mmt_image.MMTImage;
import mmt_image.MMTImageComputer;

public class GammaCorrection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 3) {
			System.err.println("set the image for gamma correction, the name of the outputfile and gamma.");
			System.err.println("GammaCorrection <inputfile> <outputfile> <gamma>");
		}
		File im = new File(args[0]);
		
		// check file
		if (!im.isFile() || !im.exists()) {
			System.err.println(im.getName() + " --> invalid file.");
		}

		// open picture, correct gamma and output it
		try {
			MMTImage img = FileImageReader.read(im.getAbsolutePath());
			MMTImage nimg = MMTImageComputer.gammaCorrection(img, Double.valueOf(args[2]));
			FileImageWriter.write(nimg, args[1]);
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}

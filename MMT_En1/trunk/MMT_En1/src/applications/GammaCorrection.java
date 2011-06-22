package applications;

import java.io.File;
import java.io.IOException;

import mmt_image.FileImageReader;
import mmt_image.FileImageWriter;
import mmt_image.MMTImage;
import mmt_image.MMTImageComputer;

/**
 * the GammaCorrection is used if an image is under or overexposed. it improves the local contrast of an image. <br>
 * gamma is a floating point value. if 1.0 is used the image stays the same. <br>
 * usage: GammaCorrection [imagepath] [outputfile] [gamma] <br>
 * the output image is saved to the path specified by outputfile.
 * @author Mürzl Harald
 *
 */
public class GammaCorrection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 3) {
			System.err.println("set the image for gamma correction, the name of the outputfile and gamma.");
			System.err.println("GammaCorrection <inputfile> <outputfile> <gamma>");
			return;
		}
		File im = new File(args[0]);
		
		// check file
		if (!im.isFile() || !im.exists()) {
			System.err.println(im.getName() + " --> invalid file.");
			return;
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

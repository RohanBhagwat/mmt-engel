package applications;

import java.io.File;
import java.io.IOException;

import mmt_image.FileImageReader;
import mmt_image.FileImageWriter;
import mmt_image.MMTImage;
import mmt_image.MMTImageComputer;

/**
 * The ContrastStretcher is used if all pixels are in the lower or upper range of the histogram to 
 * scale them to the full range. <br>
 * usage: ContrastStretcher [imagepath] [outputfile] <br>
 * the output image is saved to the path specified by outputfile.
 * @author Mürzl Harald
 *
 */
public class ContrastStretcher {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 2) {
			System.err.println("set the image to stretch and the name of the outputfile.");
			return;
		}
		File im = new File(args[0]);
		
		// check file
		if (!im.isFile() || !im.exists()) {
			System.err.println(im.getName() + " --> invalid file.");
			return;
		}

		// open picture, stretch contrast and output it
		try {
			MMTImage img = FileImageReader.read(im.getAbsolutePath());
			MMTImage nimg = MMTImageComputer.contrastStretcher(img);
			FileImageWriter.write(nimg, args[1]);
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}

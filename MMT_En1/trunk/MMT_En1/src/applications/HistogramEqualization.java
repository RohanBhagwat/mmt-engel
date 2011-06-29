package applications;

import java.io.File;
import java.io.IOException;

import mmt_image.FileImageReader;
import mmt_image.FileImageWriter;
import mmt_image.MMTImage;
import mmt_image.MMTImageComputer;

/**
 * applies histogram equalisation to an image. <br>
 * usage: HistogramEqualization [image] [outputfile] <br>
 * image............the image to be transformed.<br>
 * outputfile.......specifies the location to save the transformed image.<br>
 * @author Mürzl Harald
 *
 */
public class HistogramEqualization {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 2) {
			System.err.println("set the image for histogram equalization and the outputfile.");
			return;
		}
		File im = new File(args[0]);

		// check directory
		if (!im.isFile() || !im.exists()) {
			System.err.println(im.getName() + " --> invalid file.");
			return;
		}

		// open picture and print histogramm
		try {
			MMTImage img = FileImageReader.read(im.getAbsolutePath());
			MMTImage eqimg = new MMTImage(img.getWidth(), img.getHeight());
			
			double[] nkhist = MMTImageComputer.getKumNormHistogram(img);
			int pix;
			for (int i=0; i<img.getWidth()*img.getHeight(); i++) {
				pix = img.getPixel(i);
				int npix = (int)(255 * nkhist[pix]);
				eqimg.setPixel(i, npix);
			}
			img = eqimg;
			
			
			FileImageWriter.write(eqimg, args[1]);

		}
		catch (IOException e) {
			e.printStackTrace();
		}

	}

}

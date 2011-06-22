package applications;

import image_filter.BorderHandling;
import image_filter.LaplacianFilter;
import image_filter.SobelOperator;

import java.io.File;
import java.io.IOException;

import mmt_image.FileImageReader;
import mmt_image.FileImageWriter;
import mmt_image.MMTImage;

/**
 * command line program for sharpening an image using laplace or sobel filter. <br>
 * 
 * usage: SharpenAnImage [image] [outputfile] [faktor] [filter] [neighborhood]<br><br>
 * image............the image to be transformed.<br>
 * outputfile.......specifies the location to save the transformed image.<br>
 * faktor...........specifies how much the filter response is weighted (new = old + filter * faktor). <br>
 * filter...........defines the used filter method. can be laplace or sobel, laplace is default. <br>
 * neighborhood.....specifies if the filter takes the diagonal direction into account or not <br>
 *                  can be 4 or 8, <br>
 *                  4 is the default value and takes only horizontal and vertical direction. <br>
 *                  8 also takes the diagonals into account.<br>
 * 
 * @author Mürzl Harald
 *
 */
public class SharpenAnImage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length < 3) {
			System.err.println("see documentation for usage.");
			return;
		}
		
		// get parameters
		File im = new File(args[0]);
		BorderHandling bh = BorderHandling.PARTIAL;
		double faktor = Double.parseDouble(args[2]);

		String filter = new String("laplace");
		if (args.length > 3) {
			if (args[3].equals("sobel")) {
				filter = args[3];
			}
		}

		int neighbourhood = 4;
		if (args.length > 4) {
			int nn = Integer.parseInt(args[4]);
			if ((nn == 4) || (nn==8))
				neighbourhood = nn;
			else {
				System.err.println("see documentation for usage.");
				return;
			}
		}

		// check directory
		if (!im.isFile() || !im.exists()) {
			System.err.println(im.getName() + " --> invalid file.");
			return;
		}

		// define filter for laplace
		int width = 3;
		MMTImage coef = new MMTImage(width,width);
		int[] cfvals = new int[width*width];

		if (neighbourhood == 4) { // 4 Neighborhood
			cfvals = new int[] {0, -1 , 0, -1, 4, -1, 0, -1, 0};
		}
		else {  // 8 Neighborhood
			cfvals = new int[] {-1, -1 , -1, -1, 8, -1, -1, -1, -1};
		}
		coef.setData(cfvals);
		if (filter.equals("laplace")) {
			
		}


		// open picture and sharpen it using the specified filter method
		try {
			MMTImage img = FileImageReader.read(im.getAbsolutePath());
			MMTImage filim;
			if (filter.equals("sobel")) {
				SobelOperator so = new SobelOperator(bh);
				filim = so.applyFilter(img);
			}
			else {
				LaplacianFilter lf = new LaplacianFilter(bh, coef);
				filim = lf.applyFilter(img);
			}
			
			// add filter response
			for (int i=0; i<img.getHeight()*img.getWidth(); i++) {
				img.setPixel(i, (int)(img.getPixel(i) + filim.getPixel(i)*faktor));
			}
			// get absolute values and limit from 0 to 255 to view the result as an image.
			img.setData(img.getAbs());
			img.setData(img.getLimited_0_255());
			// save new Picture
			FileImageWriter.write(img, args[1]);
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}

package applications;

import image_filter.BorderHandling;
import image_filter.LaplacianFilter;

import java.io.File;
import java.io.IOException;

import mmt_image.FileImageReader;
import mmt_image.FileImageWriter;
import mmt_image.MMTImage;

/**
 * command line program for applying a laplacian filter to an image. <br>
 * 
 * usage: UseLaplacianFilter [image] [outputfile] [BorderHandling] [neighborhood] <br><br>
 * image............the image to be transformed.<br>
 * outputfile.......specifies the location to save the transformed image.<br>
 * BorderHandling...can be partial, padding or limiting. partial is the default value. <br>
 * neighborhood.....specifies if the filter takes the diagonal direction into account or not <br>
 *                  can be 4 or 8, <br>
 *                  4 is the default value and takes only horizontal and vertical direction. <br>
 *                  8 also takes the diagonals into account.<br>
 * 
 * @author Mürzl Harald
 *
 */
public class UseLaplacianFilter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length < 2) {
			System.err.println("see documentation for usage.");
			return;
		}
		
		// get parameters
		File im = new File(args[0]);
		
		BorderHandling bh = BorderHandling.PARTIAL;
		if (args.length > 2) {
			if (args[2].equals("padding") || args[2].equals("partial") || args[2].equals("limiting")) {
				bh = BorderHandling.valueOf(args[2].toUpperCase());
			}
			else {
				System.err.println("see documentation for usage.");
				return;
			}
		}

		int neighbourhood = 4;
		if (args.length > 3) {
			int nn = Integer.parseInt(args[3]);
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

		// create filter mask
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

		// open picture and transform it using the laplacian filter
		try {
			MMTImage img = FileImageReader.read(im.getAbsolutePath());
			LaplacianFilter lf = new LaplacianFilter(bh, coef);
			MMTImage laplace = lf.applyFilter(img);
			
			// get absolute values and limit from 0 to 255 to view the result as an image.
			laplace.setData(laplace.getAbs());
			laplace.setData(laplace.getLimited_0_255());
			// save new Picture
			FileImageWriter.write(laplace, args[1]);
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}

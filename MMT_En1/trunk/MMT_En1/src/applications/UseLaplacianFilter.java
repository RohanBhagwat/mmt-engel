package applications;

import image_filter.BorderHandling;
import image_filter.LaplacianFilter;

import java.io.File;
import java.io.IOException;

import mmt_image.FileImageReader;
import mmt_image.FileImageWriter;
import mmt_image.MMTImage;

/**
 * commandline programm for applying an laplacian filter to an image.
 * 
 * usage: UseLaplacianFilter <image> <BorderHandling> <neighbourhood>
 * 
 * BorderHandling can be partial, padding or limiting. partial is the defaultvalue.
 * neighbourhood can be 4 or 8, 4 is the defaultvalue.
 * 
 * neighbourhood 4 means, that the 4 nearest pixels are processed with the filter.
 * so horizontal and vertical changes of the pixels are shown.
 * neighbourhood 8 means, that all directions are shown. so you also can see changes in 
 * diagonal direction.
 * 
 * outputfile is laplacianImage.png
 * @author Mürzl Harald
 *
 */
public class UseLaplacianFilter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		if (args.length < 1) {
			System.err.println("see documentation for usage.");
		}
		
		// get parameters
		File im = new File(args[0]);
		int neighbourhood = 4;
		BorderHandling bh = BorderHandling.PARTIAL;
		if (args.length > 1) {
			if (args[1].equals("padding") || args[1].equals("partial") || args[1].equals("limiting")) {
				bh = BorderHandling.valueOf(args[1].toUpperCase());
			}
			else {
				System.err.println("see documentation for usage.");
			}
		}
		if (args.length > 2) {
			int nn = Integer.parseInt(args[2]);
			if ((nn == 4) || (nn==8))
				neighbourhood = nn;
			else 
				System.err.println("see documentation for usage.");
		}
		
		// check directory
		if (!im.isFile() || !im.exists()) {
			System.err.println(im.getName() + " --> invalid file.");
		}

		// create filter mask
		int width = 3;
		MMTImage coef = new MMTImage(width,width);
		// set coefficients for 4 neighbourhood
		int[] cfvals = new int[width*width];
		if (neighbourhood == 4) {
			cfvals = new int[] {0, -1 , 0, -1, 4, -1, 0, -1, 0};
		}
		else {
			cfvals = new int[] {-1, -1 , -1, -1, 8, -1, -1, -1, -1};
		}
		coef.setData(cfvals);

		// open picture and laplace it
		try {
			MMTImage img = FileImageReader.read(im.getAbsolutePath());
			LaplacianFilter lf = new LaplacianFilter(bh, coef);
			MMTImage averaged = lf.applyFilter(img);
			
			// save new Picture
			FileImageWriter.write(averaged, "laplacianImage.png");
			
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}

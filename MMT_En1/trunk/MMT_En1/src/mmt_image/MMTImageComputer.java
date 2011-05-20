package mmt_image;

import java.util.HashMap;
import java.util.HashSet;

/**
 * this class provides several functions for MMTImages like
 * getHistogram(), contrastStrecher(), gammaCorrection(),...
 * @author muetze
 *
 */
public class MMTImageComputer {
	private static int grayval = 256;

	/**
	 * creates the histogramm of an MMTImage an returns the data
	 * as an integer array.
	 * note: this method only supports images with an max gray value of 255
	 * @param img, MMTImage
	 * @return int[]
	 */
	public static int[] getHistogramm (MMTImage img) {
		int[] hist = new int[grayval];
		int[] data = img.getData();
		int imsiz = img.getWidth() * img.getHeight();
		
		// generate Histogramm
		for (int i=0; i<imsiz; i++) {
			hist[data[i]]++;
		}
		return hist;
	}

	public static MMTImage contrastStretcher (MMTImage img) {
		MMTImage im = new MMTImage(img.getWidth(), img.getHeight());
		int[] data = img.getData();
		int imsiz = img.getWidth() * img.getHeight();
		int newval;
		
		// get factors
		int min = getMin(data);
		int factor = 255 / (getMax(data) - min);
		
		// contrast stretching
		for (int i=0; i<imsiz; i++) {
			newval = (img.getPixel(i) - min) * factor;
			im.setPixel(i, newval);
		}
		return im;
	}
	
	private static int getMin(int[] img) {
		int min =img[0];
		for (int i=1; i<img.length; i++) {
			min = (min < img[i]) ? min : img[i];
		}
		return min;
	}
	private static int getMax(int[] img) {
		int max =img[0];
		for (int i=1; i<img.length; i++) {
			max = (max > img[i]) ? max : img[i];
		}
		return max;
	}

}

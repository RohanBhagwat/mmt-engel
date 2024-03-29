package mmt_image;


/**
 * this class provides several static methods for MMTImages like
 * getHistogram(), contrastStrecher(), gammaCorrection(),...
 * @author M�rzl Harald
 *
 */
public class MMTImageComputer {
	private static int grayval = 256;

	/**
	 * creates the histogram of an MMTImage and returns the data as an integer array. <br>
	 * index 0 shows the nr of pixels with value 0, index 2.... , up to 255 <br>
	 * note: this method only supports images with an max gray value of 255. <br>
	 * @param img MMTImage
	 * @return int[] the histogram as an array of Integer.
	 */
	public static int[] getHistogram (MMTImage img) {
		int[] hist = new int[grayval];
		int[] data = img.getData();
		int imsiz = img.getWidth() * img.getHeight();
		
		// generate Histogramm
		for (int i=0; i<imsiz; i++) {
			hist[data[i]]++;
		}
		return hist;
	}

	/**
	 * gets the histogram of the image and calculates the normalized histogram. <br>
	 * @param img MMTImage
	 * @return double[]
	 */
	public static double[] getNormHistogram (MMTImage img) {
		int[] hist = getHistogram(img);
		double[] nhist = new double[hist.length];
		
		for (int i=0; i<hist.length; i++) {
			nhist[i] = (double)((double)hist[i] / (img.getHeight()*img.getWidth()));
		}
		return nhist;
	}
	
	/**
	 * calculates the kumulated normalized histogram of the image.
	 * @param img MMTImage
	 * @return double[]
	 */
	public static double[] getKumNormHistogram (MMTImage img) {
		double[] nhist = getNormHistogram(img);
		double[] knhist = new double[nhist.length];
		
		knhist[0] = nhist[0];
		for (int i=1; i<nhist.length; i++) {
			knhist[i] = nhist[i] + knhist[i-1];
		}
		
		return knhist;
	}
	/**
	 * returns an image with the content of img stretched from 0 to 255.
	 * @param img MMTImage, the image to stretch.
	 * @return MMTImage
	 */
	public static MMTImage contrastStretcher (MMTImage img) {
		MMTImage im = new MMTImage(img.getWidth(), img.getHeight());
		int[] data = img.getData();
		int imsiz = img.getWidth() * img.getHeight();
		int newval;
		
		// get factors
		int min = getMin(data);
		double factor = (double)((grayval-1)/ (double)(getMax(data) - min));
		
		// contrast stretching
		for (int i=0; i<imsiz; i++) {
			newval = (int)((img.getPixel(i) - min) * factor);
			im.setPixel(i, newval);
		}
		return im;
	}
	
	/**
	 * returns the gamma corrected image of img. pixels greater than 255 will be limited to 255; <br>
	 * @param img MMTImage
	 * @param gamma double
	 * @return MMTImage
	 */
	public static MMTImage gammaCorrection (MMTImage img, double gamma) {
		MMTImage im = new MMTImage(img.getWidth(), img.getHeight());
		int wmin = img.getMin();
		int wmax = img.getMax();
		int imsiz = img.getWidth() * img.getHeight();
		int newval;
		
		// gamma correction
		for (int i=0; i<imsiz; i++) {
			newval = (int)(wmin+wmax*Math.pow(((double)img.getPixel(i)-wmin)/(wmax-wmin), gamma));
			newval = (newval > 255) ? 255 : newval;
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

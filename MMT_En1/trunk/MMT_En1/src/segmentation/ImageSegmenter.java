package segmentation;

import mmt_image.MMTImage;
import mmt_image.MMTImageComputer;

/**
 * this class provides several methods to segment an image.
 * provided methods:
 * - global threshold
 * - iterative threshold
 * - otsus method
 * 
 * @author Mürzl Harald
 *
 */
public class ImageSegmenter {

	public ImageSegmenter() {
	}
	
	/**
	 * returns a segmentation mask in form of a MMTImage using a given threshold.
	 * @param img MMTImage
	 * @param threshold int, 0-255 Intensity level (threshold) for segmentation
	 * @return MMTImage
	 */
	public MMTImage applyGlobalThreshold(MMTImage img, int threshold) {
		return segmenter(img, threshold);
	}
	/**
	 * returns a segmentation mask in form of a MMTImage using iterative global thresholding
	 * @param img MMTImage
	 * @return MMTImage
	 */
	public MMTImage applyIterativeGlobalThreshold(MMTImage img) {
		MMTImage mask = new MMTImage(img.getWidth(), img.getHeight());
		int size = img.getWidth()*img.getHeight();
		
		int dthmax = 3;		// process until difference between thresholds < dtmax
		int thakt = 255/2;	// actual difference between 
		int thold = 0;
		long m1 = 0;
		long m2 = 0;
		long sizm1, sizm2;
		while (Math.abs(thakt - thold) >= dthmax) {
			mask = segmenter(img, thakt);
			m1 = 0;
			m2 = 0;
			sizm1 = 0;
			sizm2 = 0;
			// mittelwerte berechnen
			for (int i=0; i<size; i++) {
				if (mask.getPixel(i) == 0) {
					m1 += img.getPixel(i);
					sizm1++;
				}
				else {
					m2 += img.getPixel(i);
					sizm2++;
				}
			}
			if ((sizm1 != 0) && (sizm2 != 0)) {
				m1 /= sizm1;
				m2 /= sizm2;
				thold = thakt;
				thakt = (int)(m1+m2)/2;
			}
			else {
				thakt += 10;
			}
			
		}
		return mask;
	}

	/**
	 * returns a segmentation mask in form of a MMTImage using otsu´s method.
	 * @param img MMTImage
	 * @return MMTImage
	 */
	public MMTImage applyOtsusMethod(MMTImage img) {
		
		// get normalized and kumulated normalized histogramm
		double[] nhist = MMTImageComputer.getNormHistogram(img);
		double[] knhist = MMTImageComputer.getKumNormHistogram(img);
		double P1, P2, varianz, varianz_max;
		double mg, m1, m2;
		int kmax = 0;
		
		// calculate mg
		mg=0;
		for (int i=0; i<255; i++) {
			mg += nhist[i] * i;
		}		
		varianz_max = 0;
		// find max var(k) 
		for (int k=0; k<255; k++) {
			
			// calculate Probability if a pixel is in segment 1 or 2
			P1 = knhist[k];
			P2 = 1-P1;

			// calculate global mean gray value and 
			// mean gray value of segment 1 and 2 using nhist
			m1 = 0;
			m2 = 0;
			for (int i=0; i<255; i++) {
				if (i<=k) {
					m1 += nhist[i] * i;
				}
				else {
					m2 += nhist[i] * i;
				}
			}
			m1 /= P1;
			m2 /= P2;

			// calculate the varianz between the two segments
			varianz = Math.pow((P1 * Math.pow(m1 - mg,2) + P2 * Math.pow(m2 - mg,2)),2);
		
			kmax = (varianz > varianz_max) ? k : kmax;
			varianz_max = (varianz > varianz_max) ? varianz : varianz_max;
		}
		// segment first time
		MMTImage mask = segmenter(img, kmax);

		return mask;
	}

	/**
	 * returns a segmentation mask in form of a MMTImage using the watershed method.
	 * @param img MMTImage
	 * @return MMTImage
	 */
	public MMTImage applyWatershedSegmentation(MMTImage img) {
		// TODO finish the watershed algorithm
		MMTImage mask = new MMTImage(img.getWidth(), img.getHeight());
		
		// get initial parameters
		MMTImage label = new MMTImage(img.getWidth(), img.getHeight());
		int actLabel = 0;
		label.setAllPixels(actLabel);
		int counter = img.getMin();
		
		for (int i=0; i<255; i++) {
			
		}
		
		return mask;
	}

	private MMTImage segmenter(MMTImage img, int threshold) {
		int size = img.getWidth()*img.getHeight();
		int val0 = 0;
		int val1 = 255;
		int nval;
		MMTImage nim = new MMTImage(img.getWidth(), img.getHeight());
		
		for (int i=0; i<size; i++) {
			nval = (img.getPixel(i) <= threshold) ? val0 : val1;
			nim.setPixel(i, nval);
		}
		
		return nim;
	}
	
	private double kumProbability(double[] nhist, int start, int end) {
		double P = 0;
		for (int i=start; i<=end; i++) {
			P += nhist[i];
		}
		return P;
	}
}

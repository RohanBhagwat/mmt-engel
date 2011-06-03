package segmentation;

import mmt_image.MMTImage;
import mmt_image.MMTImageComputer;

public class GlobalThreshold {

	public GlobalThreshold() {
	}
	
	public MMTImage applyGlobalThreshold(MMTImage img, int threshold) {
		return segmenter(img, threshold);
	}
	
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

	public MMTImage applyOtsusMethod(MMTImage img) {
		
		// get normalized histogramm
		int[] hist = MMTImageComputer.getHistogramm(img);
		
		double[] nhist = new double[hist.length];
		for (int i=0; i<hist.length; i++) {
			nhist[i] = hist[i] / (img.getWidth()*img.getHeight());
		}
		// TODO otsus method einbauen
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
}

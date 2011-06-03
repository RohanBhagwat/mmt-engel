package image_filter;

import mmt_image.MMTImage;

/**
 * applies an averaging filter to the given image.
 * coefficients and borderhandling are to be defined in the constructor.
 * @author Mürzl Harald
 *
 */
public class AveragingFilter extends LinearImageFilter {

	public AveragingFilter(BorderHandling bh, MMTImage coefficients) {
		super(bh, coefficients);

		// check coefficients
		if (this.getSumOfCoefficients(coefficients) == 0) {
			throw new IllegalArgumentException("The sum of the averaging filter coefficients must not be 0.");
		}
	}
	

	/**
	 * calculates the average of each pixel in the neighbourhood.
	 * the width of the coefficient image spcifies the width of this filter.
	 */
	public MMTImage applyFilter(MMTImage img) {
		MMTImage nim = new MMTImage(img.getWidth(), img.getHeight());
		
		for (int x=0; x<img.getWidth(); x++) {
			for (int y=0; y<img.getHeight(); y++) {
				MMTImage cf = this.getSubCoeffs(x, y, img);	//coefficients
				MMTImage sim = this.getPart(x, y, img);		//subimage
				
				// calculate new pixel
				long nval=0;
				int pixels = cf.getHeight()*cf.getWidth();
				for (int i=0; i<pixels; i++) {
					nval += cf.getPixel(i) * sim.getPixel(i);
				}
				nval /= this.getSumOfCoefficients(cf);
				nim.setPixel(x, y, (int)nval);
			}
		}
		if (this.border == BorderHandling.LIMITING) {
			int wh = (this.width - 1) / 2;
			int nx = img.getWidth() - 2 * wh;
			int ny = img.getHeight() - 2 * wh;
			nim = nim.getSubPicture(wh, wh, nx, ny);
		}
		
		return nim;
	}

}

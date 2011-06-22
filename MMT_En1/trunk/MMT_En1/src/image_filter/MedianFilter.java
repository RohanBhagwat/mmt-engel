package image_filter;

import java.util.Arrays;

import mmt_image.MMTImage;

/**
 * applies a median filter to the given image.
 * borderhandling and filter width are to be defined in the constructor.
 * @author Mürzl Harald
 *
 */
public class MedianFilter extends ImageFilter {

	// constructors
	public MedianFilter(BorderHandling borderHandling, int width) {
		super(borderHandling, width);
	}

	/**
	 * gets the median value around every pixel.
	 * the size is defined by width. 
	 */
	@Override
	public MMTImage applyFilter(MMTImage img) {
		MMTImage nim = new MMTImage(img.getWidth(), img.getHeight());
		
		for (int x=0; x<img.getWidth(); x++) {
			for (int y=0; y<img.getHeight(); y++) {
				MMTImage sim = this.getPart(x, y, img);		//subimage
				int index = (sim.getWidth() * sim.getHeight() - 1) / 2;
				
				// calculate new pixel
				int[] values = sim.getData();
				Arrays.sort(values);

				nim.setPixel(x, y, values[index]);
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

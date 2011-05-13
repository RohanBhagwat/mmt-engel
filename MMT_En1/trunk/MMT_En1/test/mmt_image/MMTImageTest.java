package mmt_image;

import junit.framework.Assert;

import org.junit.Test;

/**
 * provides testfunctions for the methods of MMTImage
 * @author muetze
 *
 */
public class MMTImageTest {

	/**
	 * tests the 1D Pixelsetter and getter
	 */
	@Test
	public void testPixelsetter_1D() {
		MMTImage img = new MMTImage(10, 10);
		int[] testarr = new int[100];
		testarr[65] = 10;
		img.setData(testarr);
		img.setPixel(13, 70);
		Assert.assertEquals(10, img.getPixel(65));
		Assert.assertEquals(70, img.getPixel(13));
	}

	/**
	 * tests the 2D Pixelsetter and getter
	 */
	@Test
	public void testPixelsetter_2D() {
		MMTImage img = new MMTImage(10, 10);
		int[] testarr = new int[100];
		testarr[65] = 10;
		img.setData(testarr);
		img.setPixel(8, 3, 17);
		Assert.assertEquals(10, img.getPixel(5, 6));
		Assert.assertEquals(17, img.getPixel(8, 3));
	}
}

package mmt_image;



/**
 * 
 * This class is used for holding gray scale image data.
 */
public class MMTImage {

	/** the image data */
	private int[] data;

	/** the width of the image */
	private int w;

	/** the height of the image */
	private int h;

	/** the name of the image */
	private String name;

	/**
	 * creates an image and allocates memory
	 * 
	 * @param width int
	 *            width of the image
	 * @param height int
	 *			  height of the image 
	 */
	public MMTImage(int width, int height) {
		this.data = new int[width*height];
		this.h = height;
		this.w = width;
	}

	/**
	 * get pixel at specified position, 2D
	 * 
	 * @param x int
	 * @param y int
	 * @return pixel int, pixel at (x,y)
	 */
	public int getPixel(int x, int y) {
		return this.data[x+this.w*y];
	}
	
	/**
	 * get pixel at specified position, 1D
	 * 
	 * @param i int
	 * @return pixel int, pixel at (i)
	 */
	public int getPixel(int i) {
		return this.data[i];
	}

	/** the height of the image */
	public int getHeight() {
		return this.h;
	}

	/** the width of the image */
	public int getWidth() {
		return this.w;
	}

	/** the name of the image */
	public String getName() {
		return this.name;
	}
		
	/**
	 * set pixel at position (x,y) to gray value val, 2D
	 * 
	 * @param x int
	 * @param y int
	 * @param val int
	 */
	public void setPixel(int x, int y, int val) {
		this.data[x+this.w*y] = val;
	}

	/**
	 * set pixel at position (i) to gray value val, 1D
	 * 
	 * @param i int
	 * @param val int
	 */
	public void setPixel(int i, int val) {
		this.data[i] = val;
	}

	/**
	 * 
	 * @return int[], the array with the image data
	 */
	public int[] getData() {
		return data;
	}

	/**
	 * change imagedata array
	 * caution: may width and height have to be changed too.
	 * @param data int[]
	 */
	public void setData(int[] data) {
		this.data = data;
	}

	/**
	 * sets the name of the image
	 * @param name String
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Returns a piece of the original image starting at xstart, ystart with width and height in pixel. <br>
	 * The startpoint is in the left upper corner.
	 * @param xstart int, startpoint x
	 * @param ystart int, startpoint y
	 * @param width int, width of subpicture
	 * @param height int, height of subpicture
	 * @return MMTImage, the subpicture
	 */
	public MMTImage getSubPicture(int xstart, int ystart, int width, int height) {

		// check Parameter
		if ((xstart < 0) || (ystart < 0) || (width < 0) || (height < 0)) {
			throw new IllegalArgumentException("negativ values are not allowed!");
		}
		if (((xstart + width) > this.w) || (ystart + height) > this.h) {
			throw new IllegalArgumentException("Area of subpicture is outside the original image.");
		}

		MMTImage im = new MMTImage(width, height);
		
		for (int x=0; x<width; x++) {
			for (int y=0; y<height; y++) {
				im.setPixel(x, y, this.getPixel(x+xstart, y+ystart));
			}
		}
		return im;
	}

	/**
	 * returns a new array of the absolute values of all pixels.
	 * the original pixels stay the same
	 * @return int[]
	 */
	public int[] getAbs() {
		int[] absdata = new int[this.data.length];
		for(int i=0; i<this.data.length; i++) {
			absdata[i] = Math.abs(this.data[i]);
		}
		return absdata;
	}
	
	/**
	 * returns a new array of the pixels. 
	 * the pixels are limited from 0 to 255. <br> 
	 * pixels less than 0 are set to zero. <br>
	 * pixels greater than 255 are set to 255 <br>
	 * other pixels stay the same.
	 * @return int[]
	 */
	public int[] getLimited_0_255() {
		int[] limdata = new int[this.data.length];
		for(int i=0; i<this.data.length; i++) {
			limdata[i] = (this.data[i] < 0) ? 0 : this.data[i];
			limdata[i] = (this.data[i] > 255) ? 255 : this.data[i];
		}
		return limdata;
	}
	
	/**
	 * sets all pixels to the give value.
	 * @param value int
	 */
	public void setAllPixels(int value) {
		for (@SuppressWarnings("unused") int val : this.data) {
			val = value;
		}
	}
	
	/**
	 * returns the smallest value of all pixels.
	 * @return int
	 */
	public int getMin() {
		int min=0;;
		
		for (int val : this.data) {
			min = (min < val) ? min : val;
		}
		return min;
	}

	/**
	 * returns the maximum value of all pixels
	 * @return int
	 */
	public int getMax() {
		int max=0;
		
		for (int val : this.data) {
			max = (max > val) ? max : val;
		}
		return max;
	}
}

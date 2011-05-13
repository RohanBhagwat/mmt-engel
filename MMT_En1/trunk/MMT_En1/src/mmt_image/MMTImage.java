package mmt_image;

//TODO import ...

/**
 * 
 * This class is used for holding gray scale image data
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
	 * @param width
	 *            width of the image
	 * @param height 
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
	 * @param x
	 * @param y
	 * @return pixel at (x,y)
	 */
	public int getPixel(int x, int y) {
		return this.data[x+this.w*y];
	}
	
	/**
	 * get pixel at specified position, 1D
	 * 
	 * @param i
	 * @return pixel at (i)
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
	 * @param x
	 * @param y
	 * @param val
	 */
	public void setPixel(int x, int y, int val) {
		this.data[x+this.w*y] = val;
	}

	/**
	 * set pixel at position (i) to gray value val, 1D
	 * 
	 * @param i
	 * @param val
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
	 * caution: width and height must also be changed.
	 * @param data, int[]
	 */
	public void setData(int[] data) {
		this.data = data;
	}

	/**
	 * sets the name of the image
	 * @param name, String
	 */
	public void setName(String name) {
		this.name = name;
	}

	//TODO 
	//Getters & Setters for
	// data
	// name
	// width
	// height

}

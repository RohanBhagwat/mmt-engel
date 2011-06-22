package image_filter;

/**
 * lists the methods for handling the borders of the images, when filters are applied.<br><br>
 * 
 * LIMITING...the border of the image is cut off, so the new image is smaller.<br>
 * PARTIAL....only the part of the image which overlaps with the coefficients is processed by the filter. <br>
 * PADDING....the pixels outside the image are set to 0 before the filter is applied. <br><br>
 * 
 * CAUTION: when LIMITING is set as BorderHandling, then the filter class cuts off the border --> image gets smaller!
 * @author Mürzl Harald
 *
 */
public enum BorderHandling {

	LIMITING,
	PARTIAL,
	PADDING
}

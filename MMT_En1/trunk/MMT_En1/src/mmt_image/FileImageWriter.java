package mmt_image;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * provides methods for writing an MMTImage file
 * @author Mürzl Harald
 *
 */
public class FileImageWriter {

	/**
	 * writes the MMTImage to the file specified by fname. 
	 * The type of the imagefile is automatically set by the 
	 * file extension (*.jpg, *.png,...). <br>
	 * See doc of javax.imageio.ImageIO for further information.<br>
	 * @param img MMTImage image to save
	 * @param fname String name of the file
	 */
	public static void write(MMTImage img, String fname) {
		BufferedImage bi = new BufferedImage(img.getWidth(), img.getHeight(), 
				BufferedImage.TYPE_BYTE_GRAY);
		
		WritableRaster r = bi.getRaster();
		
		r.setSamples(0, 0, img.getWidth(), img.getHeight(), 0, img.getData());
		
		File f = new File(fname);
		
		try {
			ImageIO.write(bi, fname.substring(fname.lastIndexOf('.')+1), f);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
}

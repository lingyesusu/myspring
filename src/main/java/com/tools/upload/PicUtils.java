package com.tools.upload;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * <p>描述：oms_common工程中的Java类[PicUtils]</p>
 *
 * 类描述：图片压缩
 *
 * @version 1.0
 **/
@SuppressWarnings("restriction")
public class PicUtils {

	/**
	 * 生成文件缩略图
	 * @param src 
	 * @param dest
	 * @param proportion
	 * @param width
	 * @param height
	 * @throws IOException
	 */
	public static void compress(File src, File dest, Boolean proportion, Integer width, Integer height)
			throws IOException {
		
		if (!src.exists()) {
			throw new FileNotFoundException("CompressPic: src file is not exist! ");
		}
		Image img = ImageIO.read(src);

		int newWidth; int newHeight;
		// 判断是否是等比缩放
		if (proportion) {
			// 为等比缩放计算输出的图片宽度及高度
			double rate1 = ((double) img.getWidth(null)) / (double) width + 0.1;
			double rate2 = ((double) img.getHeight(null)) / (double) height + 0.1;
			// 根据缩放比率大的进行缩放控制
			double rate = rate1 > rate2 ? rate1 : rate2;
			newWidth = (int) (((double) img.getWidth(null)) / rate);
			newHeight = (int) (((double) img.getHeight(null)) / rate);
		} else {
			newWidth = width; // 输出的图片宽度
			newHeight = height; // 输出的图片高度
		}

		BufferedImage tag = new BufferedImage((int) newWidth, (int) newHeight, BufferedImage.TYPE_INT_RGB);
		/*
		 * Image.SCALE_SMOOTH 的缩略算法 生成缩略图片的平滑度的
		 * 优先级比速度高 生成的图片质量比较好 但速度慢
		 */
		tag.getGraphics().drawImage(img.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH), 0, 0, null);
		
		dest.getParentFile().mkdirs();
		FileOutputStream out = new FileOutputStream(dest);
		// JPEGImageEncoder可适用于其他图片类型的转换
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		encoder.encode(tag);
		out.close();
	}

}
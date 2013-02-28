package edu.toronto.ece1779.ec2.service;

import java.awt.Dimension;
import java.awt.Rectangle;

import magick.ColorspaceType;
import magick.CompressionType;
import magick.DrawInfo;
import magick.ImageInfo;
import magick.MagickException;
import magick.MagickImage;
import magick.PixelPacket;
import magick.PreviewType;
import magick.QuantizeInfo;

public class ImageTransformation {

	static {
		System.setProperty("jmagick.systemclassloader", "no");
	}

	// resize
	public static void resizeImage(String filePath, String toPath)
			throws MagickException {
		ImageInfo info = null;
		MagickImage image = null;
		Dimension imageDim = null;
		MagickImage scaled = null;
		try {
			info = new ImageInfo(filePath);
			image = new MagickImage(info);
			imageDim = image.getDimension();
			int wideth = imageDim.width;
			int height = imageDim.height;
			height = height / 2;
			wideth = wideth / 2;
			scaled = image.scaleImage(wideth, height);// 小图片文件的大小.
			scaled.setFileName(toPath);
			scaled.writeImage(info);
		} finally {
			if (scaled != null) {
				scaled.destroyImages();
			}
		}
	}

	// add text
	public static String addTextToImage(String filePath, String newPath,
			String text) throws MagickException {
		System.setProperty("jmagick.systemclassloader", "no");
		String newFileName = newPath;
		try {
			ImageInfo info = new ImageInfo(filePath);
			if (filePath.toUpperCase().endsWith("JPG")
					|| filePath.toUpperCase().endsWith("JPEG")) {
				info.setCompression(CompressionType.JPEGCompression); // 压缩类别为JPEG格式
				info.setPreviewType(PreviewType.JPEGPreview); // 预览格式为JPEG格式
				info.setQuality(95);
			}
			MagickImage aImage = new MagickImage(info);
			DrawInfo aInfo = new DrawInfo(info);
			aInfo.setFill(PixelPacket.queryColorDatabase("white"));
			aInfo.setUnderColor(PixelPacket.queryColorDatabase("black"));
			aInfo.setOpacity(0);
			aInfo.setPointsize(20);

			aInfo.setTextAntialias(true);
			// Step 3: Writing The Text
			aInfo.setText(text);
			aInfo.setGeometry("+20+20");

			// Step 4: Annotating
			aImage.annotateImage(aInfo);
			// Step 5: Writing the new file
			aImage.setFileName(newFileName);
			aImage.writeImage(info);
			aImage.destroyImages();
			aImage = null;
		} catch (MagickException e) {
		}
		return newFileName;
	}

	// cut
	public static void cutImgae(String imgPath, String toPath)
			throws MagickException {
		ImageInfo infoS = null;
		MagickImage image = null;
		Dimension imageDim = null;
		MagickImage cropped = null;
		Rectangle rect = null;
		try {
			infoS = new ImageInfo(imgPath);
			image = new MagickImage(infoS);
			imageDim = image.getDimension();
			int wideth = imageDim.width;
			int height = imageDim.height;
			rect = new Rectangle(0, 0, wideth / 2, height / 2);
			cropped = image.cropImage(rect);
			cropped.setFileName(toPath);
			cropped.writeImage(infoS);

		} catch (MagickException e) {
		} finally {
			if (cropped != null) {
				cropped.destroyImages();
			}
		}
	}

	public static void blackAndWhite(String fromFileStr, String saveToFileStr) {
		MagickImage image = null;

		try {
			System.setProperty("jmagick.systemclassloader", "no");
			ImageInfo info = new ImageInfo(fromFileStr);
			image = new MagickImage(info);
			image.imageToBlob(info);
			QuantizeInfo quantizeInfo = new QuantizeInfo();
			quantizeInfo.setColorspace(ColorspaceType.GRAYColorspace);
			image.quantizeImage(quantizeInfo);
			image.setFileName(saveToFileStr);
			image.writeImage(info);
		} catch (MagickException e) {
		}
	}

}

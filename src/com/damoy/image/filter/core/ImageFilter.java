package com.damoy.image.filter.core;

import java.awt.Color;
import java.awt.image.BufferedImage;

/**
 * Filter an image using the 8-colors palette.<br>
 * 
 * @author Damoy
 */
public final class ImageFilter {

	private ImageFilter() {}
	
	public static final BufferedImage[] process(BufferedImage image) {
		BufferedImage processedImagePalette5Colors = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		BufferedImage processedImagePalette8Colors = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		BufferedImage processedImagePalette16Colors = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		for(int y = 0; y < image.getHeight(); ++y) {
			for(int x = 0; x < image.getWidth(); ++x) {
				processedImagePalette5Colors.setRGB(x, y, Palette5Colors.nearestColor(new Color(image.getRGB(x, y))).get().getRGB());
				processedImagePalette8Colors.setRGB(x, y, Palette8Colors.nearestColor(new Color(image.getRGB(x, y))).get().getRGB());
				processedImagePalette16Colors.setRGB(x, y, Palette16Colors.nearestColor(new Color(image.getRGB(x, y))).get().getRGB());
			}
		}
		
		return new BufferedImage[] {processedImagePalette5Colors, processedImagePalette8Colors, processedImagePalette16Colors};
	}
	
}

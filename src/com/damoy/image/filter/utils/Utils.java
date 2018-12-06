package com.damoy.image.filter.utils;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.damoy.image.filter.core.ImageFilter;

/**
 * Utilities to process images.
 * 
 * @author Damoy
 */
public final class Utils {
	
	private final static String[] SUPPORTED_EXTENSIONS = new String[] {
		"png", "jpg", "jpeg", "gif", "bmp", "svg"
	};

	private Utils() {}
	
	/**
	 * Process an image file given its path.
	 * @param filePath
	 */
	public static void processFile(String filePath) {
		String input = filePath;
		String[] split = input.split(Pattern.quote("\\"));
		String fileName = split[split.length - 1].trim();
		
		logn(">> Image to process: " + input);
		
		String[] splitByDot = input.split("\\.");
		
		if(splitByDot.length != 3) {
			logn("!! Image format should be inquired.");
			return;
		}
		
		String format = splitByDot[2].trim();
		logn(">> Image format: " + format);
		checkFormat(format);
		
		try {
			BufferedImage sourceImage = ImageIO.read(new File(input));
			BufferedImage[] processedImages = ImageFilter.process(sourceImage);
			logn(">> Image processed !");
			
			String resultsFolderPath = "./resources/output/" + fileName.split("\\.")[0] + "/";
			new File(resultsFolderPath).mkdirs();
			
			ImageIO.write(sourceImage, format, new File(resultsFolderPath + "source_" + fileName));
			ImageIO.write(processedImages[0], format, new File(resultsFolderPath + "p5_" + fileName));
			ImageIO.write(processedImages[1], format, new File(resultsFolderPath + "p8_" + fileName));
			ImageIO.write(processedImages[2], format, new File(resultsFolderPath + "p16_" + fileName));
			
			logn(">> Results at " + resultsFolderPath);
			
		} catch (IOException e1) {
			logn("!! Error occured while processing " + input + " image.");
			e1.printStackTrace();
		}
	}
	
	/**
	 * Compute distance between two colors.
	 */
	public static double distance(Color color1, Color color2) {
		int r = color2.getRed() - color1.getRed();
		int g = color2.getGreen() - color1.getGreen();
		int b = color2.getBlue() - color1.getBlue();
		return Math.sqrt(r * r + g * g + b * b);
	}
	
	private static void checkFormat(String format) {
		if(format == null || format.isEmpty()){
			throw new IllegalStateException("Image has null format !");
		}
		
		boolean validated = false;
		
		for(int i = 0; i < SUPPORTED_EXTENSIONS.length; ++i) {
			if(SUPPORTED_EXTENSIONS[i].equals(format)) {
				validated = true;
			}
		}
		
		if(!validated)
			throw new IllegalStateException(format + " is an unknown format !");
	}
	
	public static final String getResourcesInputPath() {
		return "./resources/input/";
	}
	
	public static void log(String msg) {
		System.out.print(msg);
	}
	
	public static void logn(String msg) {
		System.out.println(msg);
	}
	
}

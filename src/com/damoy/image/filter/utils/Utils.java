package com.damoy.image.filter.utils;

import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

/**
 * Utilities to process images.
 * 
 * @author Damoy
 */
public final class Utils {
	
	private final static String[] SUPPORTED_EXTENSIONS = new String[] {
		"png", "jpg", "jpeg", "gif", "bmp", "svg"
	};
	
	private final static Random SEED = new Random();

	private Utils() {}
	
	public static int irand(int min, int max) {
		return SEED.nextInt((max - min) + 1) + min;
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
	
	public static void validateFormat(String format) {
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
	
	public static final String[] getFiles(String folder) {
		File palettesFolder = new File(folder);
		
		if(!palettesFolder.exists())
			palettesFolder.mkdirs();
		
		File[] files = palettesFolder.listFiles();
		String[] filesPaths = new String[files.length];
		
		for(int i = 0; i < files.length; ++i) {
			filesPaths[i] = files[i].getPath();
		}
		
		return filesPaths;
	}
	
	public static String loadFileContent(String filePath) {
		StringBuffer buffer = new StringBuffer();
		
		try {
			Scanner sc = new Scanner(new File(filePath));
			
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				buffer.append(line);
				buffer.append("\n");
			}
			
			sc.close();
			return buffer.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new IllegalStateException("!! Error while trying to load " + filePath + ".");
		}
	}
	
	public static void deleteFolderContent(String folderFilePath) {
		File resultsFolder = new File(folderFilePath);
		deleteFolderContent(resultsFolder);
	}
	
	public static void deleteFolderContent(File folder) {
		for (File file : folder.listFiles()) {
			if (file.isDirectory()) {
				deleteFolderContent(file);
			}
			
			file.delete();
		}
	}
	
	public static final String getResourcesInputPath() {
		return "./resources/input/";
	}
	
	public static final String getPalettesFolderPath() {
		return "./resources/palettes/";
	}
	
	public static final String getRandomPalettesFilePath() {
		return "./resources/palettes/palettes_random.if";
	}
	
	public static final String getResoucesOutputFolderPath() {
		return "./resources/output/";
	}
	
	public static void log(String msg) {
		System.out.print(msg);
	}
	
	public static void logn(String msg) {
		System.out.println(msg);
	}
	
}

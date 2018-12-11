package com.damoy.image.filter.core;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import javax.imageio.ImageIO;

import com.damoy.image.filter.core.palette.Palette;
import com.damoy.image.filter.core.palette.PaletteGenerator;
import com.damoy.image.filter.core.palette.PaletteParser;
import com.damoy.image.filter.core.palette.PaletteWriter;
import com.damoy.image.filter.utils.AppConfig;
import com.damoy.image.filter.utils.Utils;

/**
 * Filter images using provided palettes.<br>
 * 
 * @author Damoy
 */
public final class ImageFilter {

	private AppConfig config;
	private PaletteGenerator generator;
	private PaletteParser parser;
	private PaletteWriter writer;
	private List<Palette> palettes;
	private List<Palette> randomPalettes;
	
	public ImageFilter(AppConfig inconfig) {
		config = inconfig;
		palettes = new ArrayList<>();
		randomPalettes = new ArrayList<>();
		
		generator = new PaletteGenerator(this);
		parser = new PaletteParser(this);
		writer = new PaletteWriter(this);
		
		generatePalettes();
		buildPalettes();
	}
	
	private void generatePalettes() {
		if(config.isPaletteGeneration()) {
			List<Palette> generated = generator.generateRandomly(randomPalettes, config.getPaletteGenerationCount());
			writer.write(generated, config.getPaletteGenerationFileOutput());
		}
	}
	
	private void buildPalettes() {
		File file = new File(config.getPaletteFileOrFolderToUse());
		
		if(!file.exists())
			throw new IllegalStateException("Unknown state, should not happen.");
		
		if(file.isDirectory()) {
			for(String paletteFilePath : Utils.getFiles(file.getPath()))
				buildPaletteFromFilePath(paletteFilePath);
		} else {
			buildPaletteFromFilePath(file.getPath());
		}
		
		if(randomPalettes == null)
			randomPalettes = new ArrayList<>();
	}
	
	private void buildPaletteFromFilePath(String paletteFilePath) {
		List<Palette> parsedPalettes = parser.parsePalettes(paletteFilePath);
		palettes.addAll(parsedPalettes);
		if(paletteFilePath.equals(Utils.getRandomPalettesFilePath()))
			randomPalettes = parsedPalettes;
	}
	
	/**
	 * Process an image file given its path.
	 * @param filePath
	 */
	public void processFile(String filePath) {
		String input = filePath;
		String[] split = input.split(Pattern.quote("\\"));
		String fileName = split[split.length - 1].trim();
		
		Utils.logn(">> Image to process: " + input);
		
		String[] splitByDot = fileName.split("\\.");
		
		if(splitByDot.length != 2) {
			throw new IllegalArgumentException("!! Image format should be inquired.");
		}
		
		String format = splitByDot[1].trim();
		Utils.logn(">> Image format: " + format);
		Utils.validateFormat(format);
		
		try {
			BufferedImage sourceImage = ImageIO.read(new File(input));
			BufferedImage[] results = process(sourceImage);
			Utils.logn(">> Image processed !");
			
			String resultsFolderPath = "./resources/output/" + fileName.split("\\.")[0] + "/";
			File resultsFolder = new File(resultsFolderPath);
			
			if(resultsFolder.exists()) {
				Utils.deleteFolderContent(resultsFolder);
			}
			
			resultsFolder.mkdirs();
			
			// first copy the source image (in order for the user to compare outputs)
			ImageIO.write(sourceImage, format, new File(resultsFolderPath + "source_" + fileName));
			
			for(int i = 0; i < results.length; ++i) {
				ImageIO.write(results[i], format, new File(resultsFolderPath + palettes.get(i).getName() + "_" + fileName));
			}
			
			Utils.logn(">> Results at " + resultsFolderPath);
			
		} catch (IOException e1) {
			Utils.logn("!! Error occured while processing " + input + " image.");
			e1.printStackTrace();
		}
	}
	
	public BufferedImage[] process(BufferedImage image) {
		BufferedImage[] processedImages = new BufferedImage[palettes.size()];
		
		for(int i = 0; i < processedImages.length; ++i) 
			processedImages[i] = new BufferedImage(image.getWidth(), image.getHeight(), BufferedImage.TYPE_INT_ARGB);
		
		for(int i = 0; i < processedImages.length; ++i)
			for(int y = 0; y < image.getHeight(); ++y)
				for(int x = 0; x < image.getWidth(); ++x)
					processedImages[i].setRGB(x, y, palettes.get(i).nearestColor(new Color(image.getRGB(x, y))).getColor().getRGB());
		
		return processedImages;
	}

	public List<Palette> getPalettes() {
		return palettes;
	}

	public List<Palette> getRandomPalettes() {
		return randomPalettes;
	}
	
	public AppConfig getConfig() {
		return config;
	}

	public PaletteGenerator getGenerator() {
		return generator;
	}

	public PaletteParser getParser() {
		return parser;
	}

	public PaletteWriter getWriter() {
		return writer;
	}
	
}

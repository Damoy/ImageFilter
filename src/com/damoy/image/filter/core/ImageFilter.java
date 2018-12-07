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
import com.damoy.image.filter.utils.Utils;

/**
 * Filter images using provided palettes.<br>
 * 
 * @author Damoy
 */
public final class ImageFilter {

	private PaletteGenerator generator;
	private PaletteParser parser;
	private PaletteWriter writer;
	private List<Palette> palettes;
	private List<Palette> randomPalettes;
	
	public ImageFilter() {}
	
	public ImageFilter build() {
		palettes = new ArrayList<>();
		randomPalettes = new ArrayList<>();
		
		generator = new PaletteGenerator(this);
		parser = new PaletteParser(this);
		writer = new PaletteWriter(this);
		
		for(String paletteFilePath : Utils.getPalettesFilesPaths()) {
			List<Palette> parsedPalettes = parser.parsePalettes(paletteFilePath);
			palettes.addAll(parsedPalettes);
			if(paletteFilePath.equals(Utils.getRandomPalettesFilePath()))
				randomPalettes = parsedPalettes;
		}
		
		if(randomPalettes == null)
			randomPalettes = new ArrayList<>();
		
		return this;
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
		
		String[] splitByDot = input.split("\\.");
		
		if(splitByDot.length != 3) {
			Utils.logn("!! Image format should be inquired.");
			return;
		}
		
		String format = splitByDot[2].trim();
		Utils.logn(">> Image format: " + format);
		Utils.checkFormat(format);
		
		try {
			BufferedImage sourceImage = ImageIO.read(new File(input));
			BufferedImage[] results = process(sourceImage);
			Utils.logn(">> Image processed !");
			
			String resultsFolderPath = "./resources/output/" + fileName.split("\\.")[0] + "/";
			new File(resultsFolderPath).mkdirs();
			
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
	
	public void generationRandomPalettesOnRandomFile(int palettesCount) {
		List<Palette> generated = generator.generateRandomly(randomPalettes, palettesCount);
		writer.write(generated, Utils.getRandomPalettesFilePath());
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

package com.damoy.image.filter.core.palette;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.damoy.image.filter.core.ImageFilter;
import com.damoy.image.filter.utils.Utils;

public final class PaletteGenerator {

	private final static Random SEED = new Random();
	
	private ImageFilter filter;
	private int generationId;
	
	public PaletteGenerator(ImageFilter filter) {
		this.filter = filter;
		this.generationId = 0;
	}
	
	public List<Palette> generateRandomly(List<Palette> existingRandomPalettes, int palettesCount) {
		List<Palette> newlyGenerated = new ArrayList<>();
		int colorGenMin = filter.getConfig().getPaletteColorsMin();
		int colorGenMax = filter.getConfig().getPaletteColorsMax();
		
		for(int i = 0; i < palettesCount; ++i) {
			int colorsCount = colorGenMin == colorGenMax ? colorGenMax : Utils.irand(colorGenMin, colorGenMax);
			Palette generatedPalette = generatePaletteOf(existingRandomPalettes, i + 1, colorsCount);
			existingRandomPalettes.add(generatedPalette);
			newlyGenerated.add(generatedPalette);
		}
		
		return newlyGenerated;
	}
	
	public Palette generatePaletteOf(List<Palette> existingRandomPalettes, int id, int colorsCount) {
		String paletteName = "p" + generationId++ + "_" + colorsCount;
		List<PaletteColor> colors = new ArrayList<>();
		
		for(int i = 0; i < colorsCount; ++i) {
			int r = SEED.nextInt(255);
			int g = SEED.nextInt(255);
			int b = SEED.nextInt(255);
			String name = paletteName + "_r" + r + "g" + g + "b" + b;
			colors.add(new PaletteColor(name, r, g, b));
		}
		
		return new Palette(paletteName, colors);
	}
	
}

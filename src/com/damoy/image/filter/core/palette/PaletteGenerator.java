package com.damoy.image.filter.core.palette;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.damoy.image.filter.core.ImageFilter;

public final class PaletteGenerator {

	private final static Random SEED = new Random();
	
	@SuppressWarnings("unused")
	private ImageFilter filter;
	private int generationId;
	
	public PaletteGenerator(ImageFilter filter) {
		this.filter = filter;
		this.generationId = 0;
	}
	
	public List<Palette> generateRandomly(List<Palette> existingRandomPalettes, int palettesCount) {
		List<Palette> newlyGenerated = new ArrayList<>();
		
		for(int i = 0; i < palettesCount; ++i) {
			Palette generatedPalette = generatePaletteOf(existingRandomPalettes, i + 1, SEED.nextInt(256) + 256);
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
	
//	private int getPaletteId(List<Palette> existingRandomPalettes) {
//		int maxId = -1;
//		
//		if(existingRandomPalettes.isEmpty())
//			return maxId + 1;
//
//		Palette lastPalette = existingRandomPalettes.get(existingRandomPalettes.size() - 1);
//		String paletteName = lastPalette.getName();
//		String fullPaletteId = paletteName.split("\r")[0];
//		String paletteId = fullPaletteId.substring(3, fullPaletteId.length() - 1);
//		
//		if(paletteId != null && !paletteId.isEmpty()) {
//			maxId = Math.max(maxId, Integer.parseInt(paletteId));
//		}
//		
//		return maxId + 1;
//	}
}

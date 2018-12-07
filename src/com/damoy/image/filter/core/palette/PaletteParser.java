package com.damoy.image.filter.core.palette;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.damoy.image.filter.core.ImageFilter;
import com.damoy.image.filter.utils.Utils;

public final class PaletteParser {

	@SuppressWarnings("unused")
	private ImageFilter filter;
	
	public PaletteParser(ImageFilter filter) {
		this.filter = filter;
	}
	
	public List<Palette> parsePalettes(String filePath){
		List<Palette> palettes = new ArrayList<>();
		String paletteFileContent = loadAndFilter(filePath);
		String[] palettesDefinition = paletteFileContent.split("#palette");
		
		for(int i = 1; i < palettesDefinition.length; ++i) {
			String[] palettesDefinitionContent = palettesDefinition[i].trim().split("\n");
			String paletteName = palettesDefinitionContent[0].replaceAll("\"", "").trim();
			List<PaletteColor> paletteColors = new ArrayList<>();
			
			for(int j = 1; j < palettesDefinitionContent.length; ++j) {
				paletteColors.add(parsePaletteColor(palettesDefinitionContent[j]));
			}
			
			palettes.add(new Palette(paletteName, paletteColors));
		}
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < palettes.size(); ++i) {
			sb.append(i + 1);
			sb.append(". ");
			sb.append(palettes.get(i).toString());
			sb.append("\n");
		}
		
		Utils.log(sb.toString());
		
		return palettes;
	}
	
	public static PaletteColor parsePaletteColor(String source) {
		source = source.replaceAll("[\\[|\\]]", "");
		String[] content = source.split(";");
		return new PaletteColor(content[0].trim(), Integer.parseInt(content[1]),
				Integer.parseInt(content[2]), Integer.parseInt(content[3]));
	}
	
	private static String loadAndFilter(String filePath) {
		StringBuffer buffer = new StringBuffer();
		
		try {
			Scanner sc = new Scanner(new File(filePath));
			
			while(sc.hasNextLine()) {
				String line = sc.nextLine();
				if(line.isEmpty() || line.startsWith("##") || "\n".equals(line))
					continue;
					
				buffer.append(line.replaceAll("##.*", ""));
				buffer.append("\n");
			}
			
			sc.close();
			return buffer.toString();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new IllegalStateException("!! Error while trying to load " + filePath + ".");
		}
	}
	
}

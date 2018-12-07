package com.damoy.image.filter.core.palette;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.List;

import com.damoy.image.filter.core.ImageFilter;
import com.damoy.image.filter.utils.Utils;

public final class PaletteWriter {

	@SuppressWarnings("unused")
	private ImageFilter filter;
	
	public PaletteWriter(ImageFilter filter) {
		this.filter = filter;
	}
	
	public void write(List<Palette> palettes, String destFilePath) {
		File file = new File(destFilePath);
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < palettes.size(); ++i) {
			sb.append("#palette\n");
			sb.append(palettes.get(i).toString());
			sb.append("\n");
		}
		
		try {
			PrintWriter pw = new PrintWriter(file); 
			pw.print(sb.toString());
			pw.close();
		} catch (FileNotFoundException e) {
			Utils.logn("!! Could not write palettes to " + destFilePath + ".");
			e.printStackTrace();
		}
	}
}

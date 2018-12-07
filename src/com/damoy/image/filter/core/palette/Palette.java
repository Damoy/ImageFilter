package com.damoy.image.filter.core.palette;

import java.awt.Color;
import java.util.List;

import com.damoy.image.filter.utils.Utils;

public class Palette {

	private String name;
	private List<PaletteColor> colors;
	
	public Palette(String name, List<PaletteColor> colors) {
		this.name = name;
		this.colors = colors;
	}
	
	public PaletteColor nearestColor(Color sourceColor){
		PaletteColor nearestColor = null;
		double minDistance = Double.MAX_VALUE;
		double currentDistance = Double.MAX_VALUE;
		
		for(PaletteColor pagc : colors){
			currentDistance = Utils.distance(sourceColor, pagc.getColor());
			
			if(currentDistance < minDistance){
				nearestColor = pagc;
				minDistance = currentDistance;
			}
		}
		
		return nearestColor;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<PaletteColor> getColors() {
		return colors;
	}

	public void setColors(List<PaletteColor> colors) {
		this.colors = colors;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\"" + name + "\"\n");
		colors.forEach(c -> {sb.append(c.toString()); sb.append("\n");});
		return sb.toString();
	}
	
}

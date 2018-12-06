package com.damoy.image.filter.core;

import java.awt.Color;

import com.damoy.image.filter.utils.Utils;

/**
 * 8 colors palette.
 */
public enum Palette8Colors {

	// palette' colors
	BLACK(0, 0, 0),
	VIOLET(85, 65, 95),
	GRAY(100, 105, 100),
	ORANGE(215, 115, 85),
	BLUE(80, 140, 215),
	GREEN(100, 185, 100),
	YELLOW(230, 200, 110),
	WHITE(220, 245, 255);
	
	/**
	 * Get nearest palette color of inquired source color;
	 */
	public static Palette8Colors nearestColor(Color sourceColor){
		Palette8Colors nearestColor = null;
		double minDistance = Double.MAX_VALUE;
		double currentDistance = Double.MAX_VALUE;
		
		for(Palette8Colors pagc : values()){
			currentDistance = Utils.distance(sourceColor, pagc.get());
			
			if(currentDistance < minDistance){
				nearestColor = pagc;
				minDistance = currentDistance;
			}
		}
		
		return nearestColor;
	}

	private Color color;
	
	private Palette8Colors(int r, int g, int b) {
		this.color = new Color(r, g, b);
	}
	
	public Color get() {
		return color;
	}

}

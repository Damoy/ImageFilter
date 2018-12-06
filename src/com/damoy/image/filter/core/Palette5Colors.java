package com.damoy.image.filter.core;

import java.awt.Color;

import com.damoy.image.filter.utils.Utils;

/**
 * 5 colors palette.
 * 
 * https://coolors.co/4f3130-753742-aa5042-d8bd8a-d8d78f
 */
public enum Palette5Colors {

	// palette' colors
	DARK_BURGUNDY(79, 49, 48),
	BURGUNDY(80, 140, 215),
	ORANGE(170, 80, 66),
	SKIN(216, 189, 138),
	YELLOW(216, 215, 143);
	
	/**
	 * Get nearest palette color of inquired source color;
	 */
	public static Palette5Colors nearestColor(Color sourceColor){
		Palette5Colors nearestColor = null;
		double minDistance = Double.MAX_VALUE;
		double currentDistance = Double.MAX_VALUE;
		
		for(Palette5Colors pagc : values()){
			currentDistance = Utils.distance(sourceColor, pagc.get());
			
			if(currentDistance < minDistance){
				nearestColor = pagc;
				minDistance = currentDistance;
			}
		}
		
		return nearestColor;
	}

	private Color color;
	
	private Palette5Colors(int r, int g, int b) {
		this.color = new Color(r, g, b);
	}
	
	public Color get() {
		return color;
	}

}

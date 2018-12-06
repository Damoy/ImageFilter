package com.damoy.image.filter.core;

import java.awt.Color;

import com.damoy.image.filter.utils.Utils;

/**
 * 16 colors palette.
 * 
 * https://coolors.co/e8ccd2-ded6ca-cbd3bb-abc1b9-9cabb1
 * https://coolors.co/d6a2ad-c3b59f-a0af84-668f80-4a6670
 * https://coolors.co/624a4f-595349-49503c-2f413b-222f33
 */
public enum Palette16Colors {

	// palette' colors
	LIGHT_PINK(232, 204, 210),
	LIGHT_BEIGE(222, 214, 202),
	LIGHT_LGREEN(203, 211, 187),
	LIGHT_DGREEN(171, 193, 185),
	LIGHT_DBLUE(156, 171, 177),
	PINK(214, 162, 173),
	BEIGE(195, 181, 159),
	LGREEN(160, 175, 132),
	DGREEN(102, 143, 128),
	DBLUE(74, 102, 112),
	DARK_PINK(98, 74, 79),
	DARK_BEIGE(89, 83, 73),
	DARK_LGREEN(73, 80, 60),
	DARK_DGREEN(47, 65, 59),
	DARK_DBLUE(34, 47, 51),
	GRAY(100, 105, 100);
	
	/**
	 * Get nearest palette color of inquired source color;
	 */
	public static Palette16Colors nearestColor(Color sourceColor){
		Palette16Colors nearestColor = null;
		double minDistance = Double.MAX_VALUE;
		double currentDistance = Double.MAX_VALUE;
		
		for(Palette16Colors pagc : values()){
			currentDistance = Utils.distance(sourceColor, pagc.get());
			
			if(currentDistance < minDistance){
				nearestColor = pagc;
				minDistance = currentDistance;
			}
		}
		
		return nearestColor;
	}

	private Color color;
	
	private Palette16Colors(int r, int g, int b) {
		this.color = new Color(r, g, b);
	}
	
	public Color get() {
		return color;
	}

}

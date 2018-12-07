package com.damoy.image.filter.core.palette;

import java.awt.Color;

public class PaletteColor {

	private String name;
	private Color color;
	
	public PaletteColor(String name, int red, int green, int blue) {
		this.name = name;
		this.color = new Color(red, green, blue);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}
	
	@Override
	public String toString() {
		return "[" + name + ";" + color.getRed() + ";" + color.getGreen() + ";" + color.getBlue() + "]";
	}

}

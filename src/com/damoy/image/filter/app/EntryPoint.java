package com.damoy.image.filter.app;

import java.io.File;

import com.damoy.image.filter.utils.Utils;

/**
 * Application entry point.
 * 
 * @author Damoy
 */
public final class EntryPoint {

	public static void main(String[] args) {
		File folder = new File(Utils.getResourcesInputPath());
		File[] images = folder.listFiles();

		Utils.logn(">> Processing all " + folder + " content...");
		Utils.logn("");
		
		// process all folder images
		for(int i = 0; i < images.length; ++i) {
			if(images[i].isFile()) {
				Utils.processFile(images[i].getPath());
				Utils.logn("");
			}
		}
		
		Utils.logn(">> Done !");
	}
}
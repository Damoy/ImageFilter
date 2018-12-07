package com.damoy.image.filter.app;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import com.damoy.image.filter.core.ImageFilter;
import com.damoy.image.filter.utils.Utils;

/**
 * Application entry point.
 * 
 * @author Damoy
 */
public final class EntryPoint {

	public static void main(String[] args) {
		ImageFilter filter = new ImageFilter().build();
		filter.generationRandomPalettesOnRandomFile(5);
		processAllInputImages(filter);
	}
	
	private static void processAllInputImages(ImageFilter filter) {
		File folder = new File(Utils.getResourcesInputPath());
		
		// delete output content
		Path dirPath = Paths.get(Utils.getResoucesOutputFolderPath());
		try {
			Files.walk(dirPath)
				.map(Path::toFile)
				.sorted(Comparator.comparing( File::isDirectory)) 
				.forEach(File::delete);
		} catch (IOException e) {
			Utils.logn("Failed to delete previous output.");
		}
		
		File[] images = folder.listFiles();

		Utils.logn(">> Processing all " + folder + " content...");
		Utils.logn("");
		
		// process all folder images
		for(int i = 0; i < images.length; ++i) {
			if(images[i].isFile()) {
				filter.processFile(images[i].getPath());
				Utils.logn("");
			}
		}
		
		Utils.logn(">> Done !");
	}
}
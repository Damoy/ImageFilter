package com.damoy.image.filter.app;

import com.damoy.image.filter.utils.ArgumentsParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;

import com.damoy.image.filter.core.ImageFilter;
import com.damoy.image.filter.utils.AppConfig;
import com.damoy.image.filter.utils.Utils;

public final class Launcher {

	private Launcher() {}
	
	public static void launch(String[] args) {
		Utils.logn(">> Building configuration from inquired arguments...");
		AppConfig appConfig = ArgumentsParser.parse(args);
		Utils.logn(appConfig.toString());
		Utils.logn(">> Done.");
		
		File input = new File(appConfig.getInputFileOrFolderToProcess());
		
		if(!input.exists())
			throw new IllegalStateException("Unknown resources input.");
		
		ImageFilter filter = new ImageFilter(appConfig);
		launchInputProcessing(input, filter);
	}
	
	private static void launchInputProcessing(File input, ImageFilter filter) {
		if(input.isDirectory()) {
			launchInputDirectoryProcessing(input, filter);
		} else {
			launchInputFileProcessing(input, filter);
		}
	}
	
	private static void launchInputDirectoryProcessing(File input, ImageFilter filter) {
		// delete output content
		Path dirPath = Paths.get(Utils.getResoucesOutputFolderPath());
		try {
			Files.walk(dirPath)
				.map(Path::toFile)
				.sorted(Comparator.comparing(File::isDirectory)) 
				.forEach(File::delete);
		} catch (IOException e) {
			Utils.logn("Failed to delete previous output.");
		}
		
		File[] images = input.listFiles();

		Utils.logn(">> Processing all " + input + " content...");
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
	
	private static void launchInputFileProcessing(File input, ImageFilter filter) {
		String inputPath = input.getPath();
		Utils.logn(">> Processing " + inputPath + " file...");
		filter.processFile(inputPath);
		Utils.logn(">> Done !");
	}
}

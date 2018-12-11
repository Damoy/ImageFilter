package com.damoy.image.filter.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ArgumentsParser {

	private static List<String> LCOMMANDS = new ArrayList<>();
	
	static {
		LCOMMANDS.add("finput");
		LCOMMANDS.add("fpalette");
		LCOMMANDS.add("fgen");
		LCOMMANDS.add("pgen");
		LCOMMANDS.add("cgenMin");
		LCOMMANDS.add("cgenMax");
	}
	
	private ArgumentsParser() {}
	
	public static AppConfig parse(String[] args) {
		AppConfig config = new AppConfig();
		
		if(args == null || args.length == 0)
			return config;
		else if(args.length == 1)
			throw new IllegalStateException("Command \"" + args[0] + "\" requires value.");
		
		Map<String, String> arguments = new HashMap<>();
		
		for(int i = 0; i < args.length - 1; ++i) {
			if(args[i].startsWith("-")) {
				String commandKey = args[i];
				String commandValue = args[i + 1];
				commandKey = commandKey.substring(1, commandKey.length());
				
				validateCommand(commandKey, commandValue);
				arguments.put(commandKey, commandValue);
			}
		}
		
		return buildConfigFromArgs(arguments);
	}
	
	private static void validateCommand(String commandKey, String commandValue) {
		if(!LCOMMANDS.contains(commandKey))
			throw new IllegalArgumentException("Unknown command found \"" + commandKey + "\".");
		
		if(commandKey.startsWith("f")) {
			validatePath(commandKey, commandValue);
		} else if(commandKey.startsWith("p") || commandKey.startsWith("c")) {
			validateValue(commandValue);
		}
	}
	
	private static void validatePath(String commandKey, String path) {
		if(path == null || path.trim().isEmpty())
			throw new IllegalArgumentException("Unknown path \"" + path + "\" inquired.");
		
		if(commandKey.equals("fpalette") && path.equals("_auto"))
			return;
		
		File file = new File(path);
		int commandIndex = LCOMMANDS.indexOf(commandKey);
		
		if(commandIndex == -1)
			throw new IllegalStateException("Unknown state that should not happen.");
		
		if(commandKey.equals("finput")) {
			if(!file.exists())
				throw new IllegalArgumentException("Unknown path \"" + path + "\" for command \"" + commandKey + "\".");
		}
		
	}
	
	private static void validateValue(String value) {
		try {
			Integer.parseInt(value);
		} catch(NumberFormatException e) {
			throw new IllegalArgumentException("Wrong command value inquired: \"" + value + "\". Input should be an integer.");
		}
	}
	
	private static AppConfig buildConfigFromArgs(Map<String, String> arguments) {
		AppConfig config = new AppConfig();
		
		if(arguments.containsKey("finput")) {
			config.setInputFileOrFolderToProcess(arguments.get("finput"));
		}
		
		if(arguments.containsKey("fpalette")) {
			config.setPaletteFileOrFolderToUse(arguments.get("fpalette"));
		}
		
		if(arguments.containsKey("pgen")) {
			config.setPaletteGeneration(true);
			config.setPaletteGenerationCount(Integer.parseInt(arguments.get("pgen")));
		}
		
		if(arguments.containsKey("fgen")) {
			config.setPaletteGeneration(true);
			config.setPaletteGenerationFileOutput(arguments.get("fgen"));
		}
		
		if(arguments.containsKey("cgenMin")) {
			config.setPaletteGeneration(true);
			config.setPaletteColorsMin(Integer.parseInt(arguments.get("cgenMin")));
		}
		
		if(arguments.containsKey("cgenMax")) {
			config.setPaletteGeneration(true);
			config.setPaletteColorsMax(Integer.parseInt(arguments.get("cgenMax")));
		}
		
		return config;
	}
	
}

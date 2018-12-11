package com.damoy.image.filter.utils;

public class AppConfig {

	private String inputFileOrFolderToProcess;
	private String paletteFileOrFolderToUse;
	
	private String paletteGenerationFileOutput;
	private boolean paletteGeneration;
	private int paletteGenerationCount;
	private int paletteColorsMin;
	private int paletteColorsMax;
	
	public AppConfig() {
		inputFileOrFolderToProcess = Utils.getResourcesInputPath();
		paletteFileOrFolderToUse = Utils.getPalettesFolderPath();
		paletteGenerationFileOutput = Utils.getRandomPalettesFilePath();
		paletteGeneration = false;
		paletteGenerationCount = 5;
		paletteColorsMin = 4;
		paletteColorsMax = 64;
	}
	
	public boolean isPaletteFileOrFolderToUseAuto() {
		return "_auto".equals(paletteFileOrFolderToUse.toLowerCase().trim());
	}

	public String getInputFileOrFolderToProcess() {
		return inputFileOrFolderToProcess;
	}

	public void setInputFileOrFolderToProcess(String inputFileOrFolderToProcess) {
		this.inputFileOrFolderToProcess = inputFileOrFolderToProcess;
	}

	public String getPaletteFileOrFolderToUse() {
		if(isPaletteFileOrFolderToUseAuto())
			return getPaletteGenerationFileOutput();
		
		return paletteFileOrFolderToUse;
	}

	public void setPaletteFileOrFolderToUse(String paletteFileOrFolderToUse) {
		this.paletteFileOrFolderToUse = paletteFileOrFolderToUse;
	}

	public String getPaletteGenerationFileOutput() {
		return paletteGenerationFileOutput;
	}

	public void setPaletteGenerationFileOutput(String paletteGenerationFileOutput) {
		this.paletteGenerationFileOutput = paletteGenerationFileOutput;
	}

	public boolean isPaletteGeneration() {
		return paletteGeneration;
	}

	public void setPaletteGeneration(boolean paletteGeneration) {
		this.paletteGeneration = paletteGeneration;
	}

	public int getPaletteGenerationCount() {
		return paletteGenerationCount;
	}

	public void setPaletteGenerationCount(int paletteGenerationCount) {
		this.paletteGenerationCount = paletteGenerationCount;
	}

	public int getPaletteColorsMin() {
		return paletteColorsMin;
	}

	public void setPaletteColorsMin(int paletteColorsMin) {
		this.paletteColorsMin = paletteColorsMin;
	}

	public int getPaletteColorsMax() {
		return paletteColorsMax;
	}

	public void setPaletteColorsMax(int paletteColorsMax) {
		this.paletteColorsMax = paletteColorsMax;
	}

	@Override
	public String toString() {
		return "AppConfig [inputFileOrFolderToProcess=" + inputFileOrFolderToProcess + ", paletteFileOrFolderToUse="
				+ paletteFileOrFolderToUse + ", paletteGenerationFileOutput=" + paletteGenerationFileOutput
				+ ", paletteGeneration=" + paletteGeneration + ", paletteGenerationCount=" + paletteGenerationCount
				+ ", paletteColorsMin=" + paletteColorsMin + ", paletteColorsMax=" + paletteColorsMax + "]";
	}

}

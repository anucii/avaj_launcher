package fr._42lyon.avaj.simulator;

import java.io.File;

class ScenarioParser {

	private File scenarioFile;
	private Scenario scenario;

	public ScenarioParser(String filePath) throws ScenarioException {
		super();
		setScenarioFile(filePath);
		setScenario(new Scenario());
	}

	public Scenario getScenario() {
		return scenario;
	}

	private void setScenarioFile(String filePath) throws ScenarioException {
		this.scenarioFile = getFileFromFilePath(filePath);
	}

	private File getFileFromFilePath(String filePath) throws ScenarioException {
		String message;
		
		if ((filePath == null) || filePath.isBlank()) {
			message = "Scenario file path missing.";
			throw new ScenarioException(message);
		}
		
		File fileToCheck = new File(filePath);
		if (!(fileToCheck.isFile() && fileToCheck.canRead())) {
			message = String.format("No readable file at the provided path : %s", filePath);
			throw new ScenarioException(message);
		}
		
		return fileToCheck;
	}

	private void setScenario(Scenario scenario) {
		this.scenario = scenario;
	}

	static boolean test(String filePath) {
		return !filePath.isBlank();
	}
}

package fr._42lyon.avaj.simulator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class ScenarioParser {

	private File scenarioFile;
	private Scenario scenario;

	public ScenarioParser(String filePath) throws ScenarioException {
		super();
		setScenarioFile(filePath);
		setScenario(new Scenario());
		buildScenario();
	}

	private void buildScenario() throws ScenarioException {

		if (scenarioFile == null) {
			String message = "Scenario source could not be determined.";
			throw new ScenarioException(message);
		}
		try (
			FileReader fileReader = new FileReader(scenarioFile);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			) {
				String line = bufferedReader.readLine();
				boolean firstLine = true;
				while (line != null) {
					if (firstLine) {
						scenario.setChangesCount(line);
						firstLine = false;
					}
					else {
						scenario.recordAircraftCreationLine(line);
					}
					line = bufferedReader.readLine();
				}
				scenario.checkAircraftsExistence();
		}
		catch (FileNotFoundException fe) {
			throw new ScenarioException(fe);
		}
		catch (IOException ie) {
			throw new ScenarioException(ie);
		}	
	}

	public Scenario getScenario() {
		return scenario;
	}

	private void setScenarioFile(String filePath) throws ScenarioException {
		this.scenarioFile = getFileFromFilePath(filePath);
	}

	private File getFileFromFilePath(String filePath) throws ScenarioException {
		String message;
		
		if ((filePath == null) || filePath.trim().isEmpty()) {
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
		return !filePath.trim().isEmpty();
	}
}

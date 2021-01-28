package fr._42lyon.avaj.simulator;

import java.util.HashSet;

import fr._42lyon.avaj.aircraft.AircraftCreationData;

class Scenario {
	
	private int changesCount;
	private HashSet<AircraftCreationData> aircraftCreationData;

	public Scenario() {
		super();
		setAircraftCreationData(new HashSet<AircraftCreationData>());
	}

	public int getChangesCount() {
		return changesCount;
	}

	public void setChangesCount(String value) throws ScenarioException {
		try {
			changesCount = Integer.parseInt(value.trim());
		} catch (NumberFormatException e) {
			String message = String
			.format("The scenario's first line should contain an integer (illicit value : %s)", value);
			throw new ScenarioException(message, e);
		}
	}

	public void recordAircraftCreationLine(String value) throws ScenarioException {
			
		AircraftCreationData lineData = new AircraftCreationData(value);
		aircraftCreationData.add(lineData);
	}

	public void checkAircraftsExistence() throws ScenarioException {
		if (aircraftCreationData.isEmpty()) {
			String message = "The scenario should contain at least one aircraft definition line.";
			throw new ScenarioException(message);
		}
	}

	private void setAircraftCreationData(HashSet<AircraftCreationData> collection) {
		aircraftCreationData = collection;
	}

	public HashSet<AircraftCreationData> getAircraftCreationData() {
		return aircraftCreationData;
	}
	
}

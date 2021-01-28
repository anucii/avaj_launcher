package fr._42lyon.avaj.simulator;

import java.util.HashSet;

class Scenario {
	
	private int changesCount;
	private HashSet<Object> aircraftCreationData;

	public Scenario() {
		super();
		setAircraftCreationData(new HashSet<Object>());
	}

	public int getChangesCount() {
		return changesCount;
	}

	public void setChangesCount(String value) throws ScenarioException {
		try {
			changesCount = Integer.parseInt(value);
		} catch (NumberFormatException e) {
			String message = String
			.format("The scenario's first line should contain an integer (illicit value : %s)", value);
			throw new ScenarioException(message, e);
		}
	}

	public void checkAircraftsExistence() throws ScenarioException {
		if (aircraftCreationData.isEmpty()) {
			String message = "The scenario should contain at least one aircraft definition line.";
			throw new ScenarioException(message);
		}
	}

	private void setAircraftCreationData(HashSet<Object> collection) {
		aircraftCreationData = collection;
	}

	public HashSet<Object> getAircraftCreationData() {
		return aircraftCreationData;
	}
	
}

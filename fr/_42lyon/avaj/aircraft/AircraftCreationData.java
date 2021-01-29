package fr._42lyon.avaj.aircraft;

import fr._42lyon.avaj.simulator.ScenarioException;

public class AircraftCreationData {

	public AircraftCreationData() {
		super();
	}
	
	public AircraftCreationData(String line) throws ScenarioException {
		this();
		parseLine(line);
	}
	

	private void parseLine(String line) throws ScenarioException {

		String errorMessage;
		var parts = line.split("\\s+");

		if (parts.length != 5){
			errorMessage = String.format("Missing information in the following line : %s", line);
			throw new ScenarioException(errorMessage);
		}
		checkAircraftType(parts[0]);
		checkName(parts[1]);
		checkCoordinates(parts[2], parts[3], parts[4]);
	}

	private void checkAircraftType(String aircraftType) throws ScenarioException {
		
		AircraftTypes matchingType = null;
		for (AircraftTypes item : AircraftTypes.values()) {
			if (item.matchesName(aircraftType)){
				matchingType = item;
				break ;
			}
		}
		if (matchingType == null) {
			String message = String
			.format("Wrong type: %s%nShould be one of %s", aircraftType, AircraftTypes.values());
			throw new ScenarioException(message);
		}
		setAircraftType(matchingType);
	}

	private void checkName(String name) throws ScenarioException {

		if (name.matches("\\w+")) {
			this.name = name;
		}
		else {
			String message = String.format("Invalid name value : %s", name);
			throw new ScenarioException(message);
		}
	}

	private void checkCoordinates(String longitude, String latitude, String height) throws ScenarioException {
		
		int checkedLongitude = checkedInt(longitude);
		int checkedLatitude = checkedInt(latitude);
		int checkedHeight = checkedInt(height);

		try {
			if (checkedHeight > 100) {
				String warning = String.format("Warning : scenario excessive height (%d) reset to 100.", checkedHeight);
				System.out.println(warning);
			}
			coordinates = new Coordinates(checkedLongitude, checkedLatitude, checkedHeight);			
		} catch (CoordinatesException e) {
			throw new ScenarioException(e);
		}
	}

	private int checkedInt(String value) throws ScenarioException {
		
		String errorMessage;

		try {
			int parsedValue = Integer.parseInt(value);
			return parsedValue;

		} catch (NumberFormatException e) {
			errorMessage = String.format("Invalid coordinate value: %s", value);
			throw new ScenarioException(errorMessage);
		}
	}

	private AircraftTypes aircraftType;

	private void setAircraftType(AircraftTypes aircraftType) {
		this.aircraftType = aircraftType;
	}

	public AircraftTypes getAircraftType() {
		return aircraftType;
	}

	private String name;
	
	private void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	
	private Coordinates coordinates;
	
	private void setCoordinates(Coordinates coordinates) {
		this.coordinates = coordinates;
	}
	
	public Coordinates getCoordinates() {
		return coordinates;
	}
	
}

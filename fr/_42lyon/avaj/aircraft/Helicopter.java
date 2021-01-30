package fr._42lyon.avaj.aircraft;

import fr._42lyon.avaj.logger.LoggerException;
import fr._42lyon.avaj.weather.TowerException;
import fr._42lyon.avaj.weather.WeatherException;
import fr._42lyon.avaj.weather.WeatherTower;
import fr._42lyon.avaj.weather.WeatherTypes;

class Helicopter extends Aircraft implements Flyable {

	private WeatherTower weatherTower;

	Helicopter(String name, Coordinates coordinates) throws LoggerException {
		super(name, coordinates);
	}

	@Override
	public void updateConditions() throws AircraftException, TowerException, WeatherException, LoggerException,
			CoordinatesException {
		
		if (weatherTower == null) {
			String message = "Error : cannot update conditions without any weather tower registration.";
			throw new AircraftException(message);
		}
		String weatherValue = weatherTower.getWeather(coordinates);
		switch (WeatherTypes.getFromName(weatherValue)) {
			case SUN:
				updateSun();
				break;
			case RAIN:
				updateRain();
				break;
			case FOG:
				updateFog();
				break;
			case SNOW:
				updateSnow();
				break;
		}
		checkIfLanded();
	}

	@Override
	public void registerTower(WeatherTower weatherTower) throws TowerException, LoggerException {
		this.weatherTower = weatherTower;
		this.weatherTower.register(this);
	}

	@Override
	public void notifyUnregistration() throws LoggerException {
		String message = String.format(Flyable.UNREGISTRATION_PATTERN, this);
		logger.addMessage(message);
	}

	@Override
	public void notifyRegistration() throws LoggerException {
		String message = String.format(Flyable.REGISTRATION_PATTERN, this);
		logger.addMessage(message);
	}

	@Override
	public String toString() {
		return String.format("%s#%s(%d)", AircraftTypes.HELICOPTER, name, id);
	}

	@Override
	public void checkIfLanded() throws LoggerException, TowerException {

		if (coordinates.getHeight() <= 0) {
			weatherTower.unregister(this);
			String landingMessage = String
			.format("%s: landing at longitude %d, latitude %d (height %d)", 
			this, coordinates.getLongitude(), coordinates.getLatitude(), coordinates.getHeight());
			logger.addMessage(landingMessage);
		}
	}

	private void updateSun() throws LoggerException, CoordinatesException {
		String message = String
		.format("%s: Ain't no sunshine when you're drone. (Bill Weathers)", this);
		logger.addMessage(message);
		int newLongitude = coordinates.getLongitude() + 10;
		int newHeight = coordinates.getHeight() + 2;
		coordinates.setLongitude(checkedCoordinate(newLongitude));
		coordinates.setHeight(checkedCoordinate(newHeight));
	}

	private void updateRain() throws LoggerException, CoordinatesException {
		String message = String
		.format("%s: But I set fire to the rain, Watch it pour as I crashed to earth. (Adel)", this);
		logger.addMessage(message);
		int newLongitude = coordinates.getLongitude() + 5;
		coordinates.setLongitude(checkedCoordinate(newLongitude));
	}

	private void updateSnow() throws LoggerException, CoordinatesException {
		String message = String
		.format("%s: Deep beneath the cover of another perfect engine where it's so white as snow. (Red Hot Chili Paper)", this);
		int newHeight = coordinates.getHeight() - 12;
		coordinates.setHeight(checkedCoordinate(newHeight));
	}

	private void updateFog() throws LoggerException, CoordinatesException {
		String message = String
		.format("%s: And the fog comes up from the fuel tank, And glows in the dark. (Radiored)", this);
		logger.addMessage(message);
		int newLongitude = coordinates.getLongitude() + 1;
		coordinates.setLongitude(checkedCoordinate(newLongitude));
	}

	private int checkedCoordinate(int value) {
		if (value < 0) {
			return 0;
		}
		return value;
	}
}

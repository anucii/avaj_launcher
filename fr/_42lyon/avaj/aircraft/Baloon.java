package fr._42lyon.avaj.aircraft;

import fr._42lyon.avaj.logger.LoggerException;
import fr._42lyon.avaj.weather.TowerException;
import fr._42lyon.avaj.weather.WeatherException;
import fr._42lyon.avaj.weather.WeatherTower;
import fr._42lyon.avaj.weather.WeatherTypes;

class Baloon extends Aircraft implements Flyable {

	private WeatherTower weatherTower;

	Baloon(String name, Coordinates coordinates) throws LoggerException {
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
		return String.format("%s#%s(%d)", AircraftTypes.BALOON, name, id);
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
		
		String message = String.format("%s: The sun is shining, the weather is sweet (Bob Marlone)", this);
		logger.addMessage(message);
		int newLongitude = coordinates.getLongitude() + 2;
		int newHeight = coordinates.getHeight() + 4;
		coordinates.setLongitude(checkedCoordinate(newLongitude));
		coordinates.setHeight(checkedCoordinate(newHeight));
	}

	private void updateRain() throws LoggerException, CoordinatesException {

		String message = String.format("%s: I'd rather be dry, but at least I'm alive, Rain on me, rain, rain (Lady Glagla)", this);
		logger.addMessage(message);
		int newHeight = coordinates.getHeight() - 5;
		coordinates.setHeight(checkedCoordinate(newHeight));
	}

	private void updateSnow() throws LoggerException, CoordinatesException {

		String message = String.format("%s: But my burners, returned to sister winter, But my valve is, as cold as ice (Soufiane Stevens)", this);
		logger.addMessage(message);
		int newHeight = coordinates.getHeight() - 15;
		coordinates.setHeight(checkedCoordinate(newHeight));
	}

	private void updateFog() throws LoggerException, CoordinatesException {
		
		String message = String.format("%s: So I'm packing my basgs for the Misty Mountains, Where the spirits go now (Led Balloon)", this);
		logger.addMessage(message);
		int newHeight = coordinates.getHeight() - 3;
		coordinates.setHeight(checkedCoordinate(newHeight));
	}

	private int checkedCoordinate(int value) {
		if (value < 0) {
			return 0;
		}
		return value;
	}
}

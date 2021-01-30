package fr._42lyon.avaj.aircraft;

import fr._42lyon.avaj.logger.LoggerException;
import fr._42lyon.avaj.weather.TowerException;
import fr._42lyon.avaj.weather.WeatherException;
import fr._42lyon.avaj.weather.WeatherTower;
import fr._42lyon.avaj.weather.WeatherTypes;

class JetPlane extends Aircraft implements Flyable {

	private WeatherTower weatherTower;

	JetPlane(String name, Coordinates coordinates) throws LoggerException {
		super(name, coordinates);
	}

	@Override
	public void updateConditions() throws AircraftException, TowerException, WeatherException, LoggerException,
			CoordinatesException {
		
		String message;

		if (weatherTower == null) {
			message = "Error : cannot update conditions without any weather tower registration.";
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
		return String.format("%s#%s(%d)", AircraftTypes.JETPLANE, name, id);
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
		String message = String.format("%s: On an island in the sun, We'll be plane and having fun. (Weazer)", this);
		logger.addMessage(message);
		int newLatitude = coordinates.getLatitude() + 10;
		int newHeight = coordinates.getHeight() + 2;
		coordinates.setLatitude(checkedCoordinate(newLatitude));
		coordinates.setHeight(checkedCoordinate(newHeight));
		
	}

	private void updateRain() throws LoggerException, CoordinatesException {
		String message = String.format("%s: And the ships are left to rust, That's what the water gave us. (Laurence + The Machine)", this);
		logger.addMessage(message);
		int newLatitude = coordinates.getLatitude() + 5;
		coordinates.setLatitude(checkedCoordinate(newLatitude));
	}

	private void updateSnow() throws LoggerException, CoordinatesException {
		String message = String
		.format("%s: Sleigh bells ring, are you listenning? In the plane, snow is glistenning. (Wing Crosby)", this);
		logger.addMessage(message);
		int newHeight = coordinates.getHeight() - 7;
		coordinates.setHeight(checkedCoordinate(newHeight));
	}

	private void updateFog() throws LoggerException, CoordinatesException {
		String message = String.format("%s: Misty morning, don't see no rhum. (Bob Marlone)", this);
		logger.addMessage(message);
		int newLatitude = coordinates.getLatitude() + 1;
		coordinates.setLatitude(checkedCoordinate(newLatitude));
	}

	private int checkedCoordinate(int value) {
		if (value < 0) {
			return 0;
		}
		return value;
	}
}

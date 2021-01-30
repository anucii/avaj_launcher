package fr._42lyon.avaj.aircraft;

import fr._42lyon.avaj.logger.LoggerException;
import fr._42lyon.avaj.weather.TowerException;
import fr._42lyon.avaj.weather.WeatherException;
import fr._42lyon.avaj.weather.WeatherTower;

public interface Flyable {
	
	public static String REGISTRATION_PATTERN = "Tower says: %s registered to weather tower";
	public static String UNREGISTRATION_PATTERN = "Tower says: %s unregistered from weather tower";

	public void updateConditions() throws AircraftException, TowerException, WeatherException, LoggerException, CoordinatesException;

	public void registerTower(WeatherTower weatherTower) throws TowerException, LoggerException;

	public void notifyUnregistration() throws LoggerException;

	public void notifyRegistration() throws LoggerException;

	void checkIfLanded() throws LoggerException, TowerException;
}

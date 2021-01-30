package fr._42lyon.avaj.weather;

import fr._42lyon.avaj.aircraft.AircraftException;
import fr._42lyon.avaj.aircraft.Coordinates;
import fr._42lyon.avaj.aircraft.CoordinatesException;
import fr._42lyon.avaj.logger.LoggerException;

public class WeatherTower extends Tower {

	private WeatherProvider weatherProvider;

	public WeatherTower() {
		super();
		weatherProvider = WeatherProvider.getProvider();
	}

	public String getWeather(Coordinates coordinates) throws TowerException {

		try {
			return weatherProvider.getCurrentWeather(coordinates);
		} catch (WeatherException e) {
			throw new TowerException(e);
		}
	}

	public void triggerWeatherChange()
			throws TowerException, AircraftException, WeatherException, LoggerException, CoordinatesException {
		changeWeather();
	}

	void changeWeather()
			throws TowerException, AircraftException, WeatherException, LoggerException, CoordinatesException {
		weatherProvider.resetWeatherSeed();
		conditionsChanged();
	}
}

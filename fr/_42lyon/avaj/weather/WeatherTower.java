package fr._42lyon.avaj.weather;

import fr._42lyon.avaj.aircraft.Coordinates;

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

	void changeWeather() throws TowerException {
		weatherProvider.resetWeatherSeed();
		conditionsChanged();
	}
}

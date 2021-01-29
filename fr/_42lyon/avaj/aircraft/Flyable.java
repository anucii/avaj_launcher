package fr._42lyon.avaj.aircraft;

import fr._42lyon.avaj.weather.WeatherTower;

public interface Flyable {
	
	public void updateConditions();

	public void registerTower(WeatherTower weatherTower);
}

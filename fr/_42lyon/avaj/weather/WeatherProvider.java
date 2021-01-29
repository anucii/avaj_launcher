package fr._42lyon.avaj.weather;

import java.util.Random;

import fr._42lyon.avaj.aircraft.Coordinates;

public class WeatherProvider {

	private static WeatherProvider weatherProvider;
	private static String[] weather = {
		WeatherTypes.SUN.name(), 
		WeatherTypes.RAIN.name(),
		WeatherTypes.FOG.name(),
		WeatherTypes.SNOW.name()
	};
	private Random rand;
	private double weatherSeed;

	public static WeatherProvider getProvider() {
		
		if (weatherProvider == null) {
			weatherProvider = new WeatherProvider();
			weatherProvider.resetWeatherSeed();
		}
		
		return weatherProvider;
	}

	public String getCurrentWeather(Coordinates coordinates) throws WeatherException {

		double shiftConstant = 0.40;
		double dividend = weatherSeed
		* reduceCoordinate(coordinates.getLongitude())
		* reduceCoordinate(coordinates.getLatitude());
		double divisor = amplifyHeight(coordinates.getHeight());
		double ratio = dividend / divisor;
		ratio = ratio < 0.01 ? amplifyRatio(ratio) : ratio;
		int index = (int)Math.round(shiftConstant + ratio);

		if (index > weather.length) {
			String errorMessage = String.format("Out of range weather index: %d", index);
			throw new WeatherException(errorMessage);
		}
		
		return weather[index];
	}

	protected void resetWeatherSeed() {
	
		int seed = 10 + rand.nextInt(28);
		weatherSeed = seed / 10.0;
	}

	private WeatherProvider() {
		super();
		rand = new Random();
	}

	private int reduceCoordinate(int coordinate) {
		return 1 + (coordinate % 9);
	}

	private double amplifyHeight(int height) {
		return 100.0 * (height + 1);
	}
	
	private double amplifyRatio(double ratio) {
		while (ratio < 0.3) {
			ratio *= 10.0;
		}

		return ratio;
	}
}

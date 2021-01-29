package fr._42lyon.avaj.weather;

public class WeatherException extends Exception {

	public WeatherException() {
		super();
	}

	public WeatherException(String message) {
		super(message);
	}

	public WeatherException(Throwable cause) {
		super(cause);
	}
	public WeatherException(String message, Throwable cause) {
		super(message, cause);
	}
}

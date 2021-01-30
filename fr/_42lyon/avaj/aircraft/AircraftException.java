package fr._42lyon.avaj.aircraft;

public class AircraftException extends Exception {

	public AircraftException() {
		super();
	}

	public AircraftException(String message) {
		super(message);
	}

	public AircraftException(Throwable cause) {
		super(cause);
	}
	public AircraftException(String message, Throwable cause) {
		super(message, cause);
	}
}

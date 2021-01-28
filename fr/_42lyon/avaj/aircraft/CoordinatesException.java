package fr._42lyon.avaj.aircraft;

public class CoordinatesException extends Exception {
	
	public CoordinatesException() {
		super();
	}

	public CoordinatesException(String message) {
		super(message);
	}

	public CoordinatesException(Throwable cause) {
		super(cause);
	}

	public CoordinatesException(String message, Throwable cause) {
		super(message, cause);
	}
}

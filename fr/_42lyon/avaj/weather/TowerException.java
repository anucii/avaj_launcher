package fr._42lyon.avaj.weather;

public class TowerException extends Exception {

	public TowerException() {
		super();
	}

	public TowerException(String message) {
		super(message);
	}

	public TowerException(Throwable cause) {
		super(cause);
	}
	public TowerException(String message, Throwable cause) {
		super(message, cause);
	}
}

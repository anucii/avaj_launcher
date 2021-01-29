package fr._42lyon.avaj.logger;

public class LoggerException extends Exception {
	
	public LoggerException() {
		super();
	}

	public LoggerException(String message) {
		super(message);
	}

	public LoggerException(Throwable cause) {
		super(cause);
	}

	public LoggerException(String message, Throwable cause) {
		super(message, cause);
	}
}

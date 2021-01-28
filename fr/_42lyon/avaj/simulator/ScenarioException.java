package fr._42lyon.avaj.simulator;

public class ScenarioException extends Exception { 
	
	public ScenarioException() {
		super();
	}

	public ScenarioException(String message) {
		super(message);
	}

	public ScenarioException(Throwable cause) {
		super(cause);
	}

	public ScenarioException(String message, Throwable cause) {
		super(message, cause);
	}
}

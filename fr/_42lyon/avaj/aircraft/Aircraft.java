package fr._42lyon.avaj.aircraft;

import fr._42lyon.avaj.logger.Logger;
import fr._42lyon.avaj.logger.LoggerException;

public abstract class Aircraft {

	private static long idCounter;
	
	protected long id;
	protected String name;
	protected Coordinates coordinates;
	protected Logger logger;

	private Aircraft() {
		super();
	}

	protected Aircraft(String name, Coordinates coordinates) throws LoggerException {
		this();
		id = nextId();
		this.name = name;
		this.coordinates = coordinates;
		logger = Logger.getInstance();
	}

	private static void incrementCounter() {
		++idCounter;
	}

	private long nextId() {
		incrementCounter();
		return idCounter;
	}
}

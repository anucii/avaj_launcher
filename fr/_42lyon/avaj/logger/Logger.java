package fr._42lyon.avaj.logger;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.*;
import java.util.Arrays;

public class Logger {

	private static Logger instance = null;
	private Path loggerPath;
	
	public static void initialize() throws LoggerException {
		
		if (instance != null) {
			String message = "Error: logger can be initialized only once.";
			throw new LoggerException(message);
		}
		
		String fileName = "simulation.txt";
		try (FileWriter writer = new FileWriter(fileName)) {
			writer.flush();
			instance = new Logger();
			instance.loggerPath = Paths.get(fileName).toAbsolutePath();
		} catch (IOException e) {
			String message = String.format("Logger initialization failed.%nCause: %s", e.getMessage());
			throw new LoggerException(message);
		}
	}

	public static Logger getInstance() throws LoggerException {
		
		if (instance == null) {
			String message = "Error : logger not initialized";
			throw new LoggerException(message);
		}

		return instance;
	}

	public void addMessage(String message) throws LoggerException {

		if (message == null) {
			return ;
		}

		try {
			Files.write(loggerPath, Arrays.asList(message), StandardOpenOption.APPEND);
		} catch (IOException e) {
			String errorMessage = String.format("Logger writing failed.%nCause: %s", e.getMessage());
			throw new LoggerException(errorMessage);
		}
	}
}

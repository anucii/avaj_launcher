package fr._42lyon.avaj.simulator;

import fr._42lyon.avaj.aircraft.AircraftException;
import fr._42lyon.avaj.aircraft.AircraftFactory;
import fr._42lyon.avaj.aircraft.Coordinates;
import fr._42lyon.avaj.aircraft.CoordinatesException;
import fr._42lyon.avaj.aircraft.Flyable;
import fr._42lyon.avaj.logger.Logger;
import fr._42lyon.avaj.logger.LoggerException;
import fr._42lyon.avaj.weather.TowerException;
import fr._42lyon.avaj.weather.WeatherException;
import fr._42lyon.avaj.weather.WeatherTower;

public class Simulator {

	private Scenario scenario;

	public static void main(String[] args) {
		try {

			if (!checkArgs(args)) {
				displayHelp();
				return;
			}
			Logger.initialize();
			Simulator simulator = new Simulator(args[0]);
			simulator.run();
		} catch (WeatherException we) {
			System.out.println(we.getMessage());
		} catch (LoggerException le) {
			System.out.println(le.getMessage());
		} catch (ScenarioException se) {
			System.out.println(se.getMessage());
		} catch (AircraftException e) {
			System.out.println(e.getMessage());
		} catch (TowerException e) {
			System.out.println(e.getMessage());
		} catch (CoordinatesException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void displayHelp() {
		String message = "Usage:\n$ java fr._42lyon.avaj.simulator.Simulator <scenario file>";
		System.out.println(message);
	}

	public static boolean checkArgs(String[] args) {
		if (args.length != 1) {
			return false;
		}
		return true;
	}

	private Simulator(String scenarioPath) throws ScenarioException {

		ScenarioParser parser;

		parser = new ScenarioParser(scenarioPath);
		scenario = parser.getScenario();
	}

	private void run() throws LoggerException, WeatherException, ScenarioException, AircraftException, TowerException,
			CoordinatesException {
		
		int changesLeft = scenario.getChangesCount();
		WeatherTower weatherTower = new WeatherTower();
		prepareSimulation(weatherTower);
		while (changesLeft-- > 0) {
			weatherTower.triggerWeatherChange();
		}
	}

	private void prepareSimulation(WeatherTower weatherTower)
			throws ScenarioException, AircraftException, LoggerException, TowerException {
		scenario.checkAircraftsExistence();

		for (var creationData : scenario.getAircraftCreationData()) {
			
			Coordinates coord = creationData.getCoordinates();
			String name = creationData.getName();
			String type = creationData.getAircraftType().name();
			
			Flyable aircraft = AircraftFactory
			.newAircraft(type, name, coord.getLongitude(), coord.getLatitude(), coord.getHeight());
			
			aircraft.registerTower(weatherTower);
		}
	}
}

package fr._42lyon.avaj.simulator;

import fr._42lyon.avaj.logger.Logger;
import fr._42lyon.avaj.logger.LoggerException;

public class Simulator {
	public static void main(String[] args) {
		try {
			ScenarioParser parser;
			Scenario scenario;

			if (!checkArgs(args)) {
				displayHelp();
				return ;
			}
			Logger.initialize();
			parser = new ScenarioParser(args[0]);
			scenario = parser.getScenario();
			return;
			
		}
		catch (LoggerException le) {
			System.out.println(le.getMessage());
		}
		catch (ScenarioException se) {
			System.out.println(se.getMessage());
		} 
		catch (Exception e) {
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
}

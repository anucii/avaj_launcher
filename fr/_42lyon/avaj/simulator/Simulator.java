package fr._42lyon.avaj.simulator;

import fr._42lyon.avaj.logger.Logger;
import fr._42lyon.avaj.logger.LoggerException;

public class Simulator {

	private Scenario scenario;

	public static void main(String[] args) {
		try {

			if (!checkArgs(args)) {
				displayHelp();
				return ;
			}
			Logger.initialize();
			Simulator simulator = new Simulator(args[0]);
			simulator.run();			
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

	private Simulator(String scenarioPath) throws ScenarioException {
		
		ScenarioParser parser;

		parser = new ScenarioParser(scenarioPath);
		scenario = parser.getScenario();
	}

	private void run() throws LoggerException {
		Logger.getInstance().addMessage("DBG : simulation now running"); // TODO remove dbg line
	}
}

package fr._42lyon.avaj.simulator;

public class Simulator {
	public static void main(String[] args) {
		if (!checkArgs(args)) {
			displayHelp();
			return;
		}
		return;
	}

	public static void displayHelp() {
		String message = "Usage:\n$ fr._42lyon.avaj.simulator.Simulator <scenario file>";
		System.out.println(message);
	}

	public static boolean checkArgs(String[] args) {
		if (args.length != 1) {
			return false;
		}
		return ScenarioParser.test(args[0]);
	}
}

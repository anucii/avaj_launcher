package fr._42lyon.avaj.aircraft;

import fr._42lyon.avaj.logger.LoggerException;

public abstract class AircraftFactory {

	public static Flyable newAircraft(String type, String name, int longitude, int latitude, int height)
			throws AircraftException, LoggerException {
		try {
			AircraftTypes flyableType = AircraftTypes.valueOf(type);
			Coordinates coordinates = new Coordinates(longitude, latitude, height);
			switch (flyableType) {
				case BALOON:
					return new Baloon(name, coordinates);
				case HELICOPTER:
					return new Helicopter(name, coordinates);
				case JETPLANE:
					return new JetPlane(name, coordinates);
			}
			String message = String.format("No aircraft constructor know for the following type : %s", type);
			throw new AircraftException(message);
		}
		catch (CoordinatesException ce) {
			throw new AircraftException(ce);
		}
		catch (IllegalArgumentException ae) {
			String message = String.format("Error: wrong aircraft type : %s", type);
			throw new AircraftException(message);
		} 
		catch (NullPointerException ne) {
			String message = "Error: null aircraft type";
			throw new AircraftException(message);
		}
	}
}

package fr._42lyon.avaj.aircraft;

public enum AircraftTypes {
	BALOON,
	JETPLANE,
	HELICOPTER;

	public boolean matchesName(String value) {
		if (value == null) {
			return false;
		}
		return name().equalsIgnoreCase(value);
	}
}

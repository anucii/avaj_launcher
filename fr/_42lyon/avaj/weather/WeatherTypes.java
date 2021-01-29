package fr._42lyon.avaj.weather;

public enum WeatherTypes {
	SUN,
	RAIN,
	FOG,
	SNOW;

	public boolean matchesName(String value) {
		if (value == null) {
			return false;
		}
		return name().equalsIgnoreCase(value);
	}
}

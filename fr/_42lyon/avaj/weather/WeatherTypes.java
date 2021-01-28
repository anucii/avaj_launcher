package fr._42lyon.avaj.weather;

public enum WeatherTypes {
	RAIN,
	FOG,
	SUN,
	SNOW;

	public boolean matchesName(String value) {
		if (value == null) {
			return false;
		}
		for (WeatherTypes item : WeatherTypes.values()) {
			if (value.equalsIgnoreCase(item.name())) {
				return true;
			}
		}
		return false;
	}
}

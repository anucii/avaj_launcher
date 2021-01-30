package fr._42lyon.avaj.weather;

public enum WeatherTypes {
	SUN,
	RAIN,
	FOG,
	SNOW;

	public static WeatherTypes getFromName(String name) throws WeatherException {
		try {
			return WeatherTypes.valueOf(name);
		}
		catch (IllegalArgumentException ae) {
			String message = String.format("Error : %s does not match with any existing weather type");
			throw new WeatherException(message);
		} 
		catch (NullPointerException ne) {
			String message = "Error : attempt to get weather type from null string.";
			throw new WeatherException(message);
		}
	}
}

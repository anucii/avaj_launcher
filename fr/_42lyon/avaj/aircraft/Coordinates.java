package fr._42lyon.avaj.aircraft;

public class Coordinates {
	private int latitude;
	private int longitude;
	private int height;
	
	Coordinates () {
		super();
	}

	Coordinates(int longitude, int latitude, int height) throws CoordinatesException {
		this();
		setLongitude(longitude);
		setLatitude(latitude);
		setHeight(height);
	}

	public int getLongitude() {
		return longitude;
	}

	public int getLatitude() {
		return latitude;
	}

	public int getHeight() {
		return height;
	}

	public void setLongitude(int longitude) throws CoordinatesException {
		this.longitude = checkedCoordinate(longitude);
	}

	public void setLatitude(int latitude) throws CoordinatesException {
		this.latitude = checkedCoordinate(latitude);
	}

	public void setHeight(int height) throws CoordinatesException {
		int positiveHeight = checkedCoordinate(height);
		this.height = positiveHeight <= 100 ? positiveHeight : 100;
	}

	private int checkedCoordinate(int value) throws CoordinatesException {
		if (value < 0) {
			String message = String.format("Invalid coordinate value (negatives forbidden): %d", value);
			throw new CoordinatesException(message);
		}
		return value;
	}
}

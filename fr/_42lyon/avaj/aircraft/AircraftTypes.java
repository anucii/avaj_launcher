package fr._42lyon.avaj.aircraft;

public enum AircraftTypes {
	BALOON("baloon"),
	JETPLANE("jetplane"),
	HELICOPTER("helicopter");

	private String label;

	private AircraftTypes(String label) {
		setLabel(label);
	}

	public boolean fitsLabel(String value) {
		if (value == null) {
			return false;
		}
		return value.equalsIgnoreCase(label);
	}

	private void setLabel(String value) {
		label = value.toLowerCase();
	}

	@Override
	public String toString() {
		return label;
	}

}

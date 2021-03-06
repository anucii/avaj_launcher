package fr._42lyon.avaj.weather;

import java.util.Collection;
import java.util.HashSet;

import fr._42lyon.avaj.aircraft.AircraftException;
import fr._42lyon.avaj.aircraft.CoordinatesException;
import fr._42lyon.avaj.aircraft.Flyable;
import fr._42lyon.avaj.logger.LoggerException;

public abstract class Tower {

	private Collection<Flyable> observers;
	private Collection<Flyable> unregisteredObservers;

	protected Tower() {
		super();
		observers = new HashSet<>();
	}

	public void register(Flyable flyable) throws TowerException, LoggerException {

		checkObserversInitialisation();
		observers.add(flyable);
		flyable.notifyRegistration();
	}

	public void unregister(Flyable flyable) throws TowerException, LoggerException {
		checkObserversInitialisation();
		unregisteredObservers.add(flyable);
	}

	protected void conditionsChanged()
			throws TowerException, AircraftException, WeatherException, LoggerException, CoordinatesException {
		
		checkObserversInitialisation();
		unregisteredObservers = new HashSet<>();
		for (Flyable item : observers) {
			item.updateConditions();
		}
		removeUnregisteredObservers();
	}

	private void removeUnregisteredObservers() throws LoggerException {
		for (Flyable flyable : unregisteredObservers) {
			observers.remove(flyable);
			flyable.notifyUnregistration();
		}
		unregisteredObservers = null;
	}

	private void checkObserversInitialisation() throws TowerException {
		
		if (observers == null) {
			String message = "Tower observers collection not set.";
			throw new TowerException(message);
		}
	}

}

package fr._42lyon.avaj.weather;

import java.util.Collection;
import java.util.HashSet;

import fr._42lyon.avaj.aircraft.Flyable;

public abstract class Tower {
	
	private Collection<Flyable> observers;

	protected Tower() {
		super();
		observers = new HashSet<>();
	}

	public void register(Flyable flyable) throws TowerException {
		
		checkObserversInitialisation();
		observers.add(flyable);
	}

	public void unregister(Flyable flyable) throws TowerException {
		checkObserversInitialisation();
		observers.remove(flyable);
	}

	protected void conditionsChanged() throws TowerException {
		
		checkObserversInitialisation();
		for (Flyable item : observers) {
			item.updateConditions();
		}
	}

	private void checkObserversInitialisation() throws TowerException {
		
		if (observers == null) {
			String message = "Tower observers collection not set.";
			throw new TowerException(message);
		}
	}

}

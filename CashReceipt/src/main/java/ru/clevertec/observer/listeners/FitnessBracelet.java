package ru.clevertec.observer.listeners;

import ru.clevertec.observer.entities.State;

/**
 * Фитнес - браслет старшего сына
 */
public class FitnessBracelet implements EventListener {
    @Override
    public void update(State eventType, String message) {
        System.out.println(eventType.name() + ": " + message);
    }

}

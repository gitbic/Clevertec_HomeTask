package ru.clevertec.patterns.observer.listeners;

import ru.clevertec.patterns.observer.entities.State;

public interface EventListener {
    void update(State eventType, String message);
}

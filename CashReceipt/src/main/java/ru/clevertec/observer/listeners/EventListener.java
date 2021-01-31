package ru.clevertec.observer.listeners;

import ru.clevertec.observer.entities.State;

public interface EventListener {
    void update(State eventType, String message);
}

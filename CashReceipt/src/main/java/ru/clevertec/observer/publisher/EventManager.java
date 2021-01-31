package ru.clevertec.observer.publisher;

import ru.clevertec.observer.entities.State;
import ru.clevertec.observer.listeners.EventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EventManager {
    Map<State, List<EventListener>> listeners = new HashMap<>();

    public EventManager(State... states) {
        for (State state : states) {
            this.listeners.put(state, new ArrayList<>());
        }
    }

    public void subscribe(State eventType, EventListener listener) {
        List<EventListener> events = listeners.get(eventType);
        events.add(listener);
    }

    public void unsubscribe(State eventType, EventListener listener) {
        List<EventListener> events = listeners.get(eventType);
        events.remove(listener);
    }

    public void notify(State eventType, String message) {
        List<EventListener> events = listeners.get(eventType);
        for (EventListener listener : events) {
            listener.update(eventType, message);
        }
    }
}

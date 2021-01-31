package ru.clevertec.observer;

import ru.clevertec.observer.entities.State;
import ru.clevertec.observer.listeners.EventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Hub {
    Map<State, List<EventListener>> listeners = new HashMap<>();

    public Hub(State... states) {
        for (State state : states) {
            this.listeners.put(state, new ArrayList<>());
        }
    }

    public void subscribe(State eventType, EventListener listener) {
        List<EventListener> eventListeners = listeners.get(eventType);
        eventListeners.add(listener);
    }

    public void unsubscribe(State eventType, EventListener listener) {
        List<EventListener> eventListeners = listeners.get(eventType);
        eventListeners.remove(listener);
    }

    public void notify(State eventType, String message) {
        List<EventListener> eventListeners = listeners.get(eventType);
        for (EventListener eventListener : eventListeners) {
            eventListener.update(eventType, message);
        }
    }

    @Override
    public String toString() {
        return "Hub{" +
                "listeners=" + listeners +
                '}';
    }
}

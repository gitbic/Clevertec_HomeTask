package ru.clevertec.observer.entities;


import ru.clevertec.observer.Hub;

public class Kettle {
    private Hub hub;

    public Kettle(Hub hub) {
        this.hub = hub;
    }

    public Hub getHub() {
        return hub;
    }

    public void warmUp(){
        System.out.println("boiling...");
        hub.notify(State.KETTLE_WARMED_UP, "The water temperature has is 50 degrees");
    }

    @Override
    public String toString() {
        return "Kettle{" +
                "hub=" + hub +
                '}';
    }
}

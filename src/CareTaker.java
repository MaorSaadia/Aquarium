/**
 * careTaker class has LinkedHashSet of immobile and swimmable and save the memento of object
 */

import java.util.LinkedHashSet;

public class CareTaker {

    //Create a new LinkedHashSet for the restore Animals and Planet
    public static LinkedHashSet<Memento> RestoreAnimals = new LinkedHashSet<Memento>();
    public static LinkedHashSet<Memento> RestorePlanet =  new LinkedHashSet<Memento>();

    //Constructor
    public CareTaker() {

    }

    //add new restore animal to the LinkedHashSet
    public void addSwimmableMemento(Memento state) {
        RestoreAnimals.add(state);

    }

    //add new restore Planet to the LinkedHashSet
    public void addImmobileMemento(Memento state) {
        RestorePlanet.add(state);
    }

    //Gets the LinkedHashSet
    public static LinkedHashSet<Memento> getRestoreAnimals() {
        return RestoreAnimals;
    }
    public static LinkedHashSet<Memento> getRestorePlanet() {
        return RestorePlanet;
    }

}

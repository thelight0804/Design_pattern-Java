package menu.interfaces;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * This class works as Subject role in Observer pattern.
 * implement this interface to be able to use the Observer pattern.
 *
 * @author Mina_
 * @version 1.0
 */
public abstract class NotifyElement {
    Map<String, Observer> observers;

    public NotifyElement() {
        observers = new HashMap<>();
    }

    /**
     * add observer to the list of observers
     *
     * @param name     the name of the observer.
     * @param observer observer instance to be added
     */
    public void addObserver(String name, Observer observer) {
        observers.put(name, observer);
    }

    /**
     * remove observer from the list of observers
     *
     * @param name name of the observer to be removed
     */
    public void removeObserver(String name) {
        observers.remove(name);
    }

    public List<String> getObserverNames() {
        return new ArrayList<>(observers.keySet());
    }

    /**
     * This method is used to notify all observers that the menu has been created.
     *
     * @param name  the name of the new menu.
     * @param price the price of the new menu.
     */
    protected void onCreate(String name, int price) {
        observers.forEach((key, observer) -> observer.create(name, price));
    }

    /**
     * This method is used to notify all observers that the menu has been updated.
     *
     * @param lastName  the name of the menu before update.
     * @param lastPrice the price of the menu before update.
     * @param name      the name of the menu after update.
     * @param price     the price of the menu after update.
     */
    protected void onModify(String lastName, int lastPrice, String name, int price) {
        observers.forEach((key, observer) -> observer.modify(lastName, lastPrice, name, price));
    }

    /**
     * This method is used to notify all observers that the menu has been deleted.
     *
     * @param name the name of the menu to delete.
     */
    protected void onDelete(String name) {
        observers.forEach((key, observer) -> observer.delete(name));
    }
}

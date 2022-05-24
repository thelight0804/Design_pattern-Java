package menu.interfaces;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * This class works as Subject role in Observer pattern.
 * implement this interface to be able to use the Observer pattern.
 * @author Mina_
 * @version 1.0
 */
public abstract class NotifyElement {
    List<Observer> observers;

    public NotifyElement() {
        observers = new LinkedList<>();
    }

    /**
     * add observer to the list of observers
     * @param observer observer to be added
     */
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    /**
     * remove observer from the list of observers
     * @param observer observer to be removed
     */
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    /**
     * This method is used to notify all observers that the menu has been created.
     * @param name the name of the new menu.
     * @param price the price of the new menu.
     */
    protected void onCreate(String name, int price){
        observers.forEach(observer -> observer.create(name, price));
    }

    /**
     * This method is used to notify all observers that the menu has been updated.
     * @param lastName the name of the menu before update.
     * @param lastPrice the price of the menu before update.
     * @param name the name of the menu after update.
     * @param price the price of the menu after update.
     */
    protected void onModify(String lastName, int lastPrice, String name, int price){
        observers.forEach(observer -> observer.modify(lastName, lastPrice, name, price));
    }

    /**
     * This method is used to notify all observers that the menu has been deleted.
     * @param name the name of the menu to delete.
     */
    protected void onDelete(String name){
        observers.forEach(observer -> observer.delete(name));
    }
}

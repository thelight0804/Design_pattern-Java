package menu.interfaces;

/**
 * Class for the observer pattern.
 * implement this class to create new observer and register it to the subject.
 *
 * @author Mina_
 * @version 1.0
 * @see NotifyElement
 */
public interface Observer {
    /**
     * this method invoked when the subject creates a new element and notified to the observer.
     *
     * @param name  the name of the new element.
     * @param price the price of the new element.
     */
    void create(String name, int price);

    /**
     * this method invoked when the subject modifies an element and notified to the observer.
     *
     * @param lastName  the last name of the element.
     * @param lastPrice the last price of the element.
     * @param name      the name of the element that changed.
     * @param price     the price of the element that changed.
     */
    void modify(String lastName, int lastPrice, String name, int price);

    /**
     * this method invoked when the subject removes an element and notified to the observer.
     *
     * @param name the name of the element that removed.
     */
    void delete(String name);
}

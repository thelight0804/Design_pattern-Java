package repository;

import menu.Menu;

import java.util.ArrayList;
import java.util.Optional;

/**
 * This class is used to store the menu items.
 * @author Mina_
 * @version 0.1
 */
public class MenuRepository {
/*
     TODO: Because this project is currently prototype, we will not use the database.
     When prototype is passed, please change to the database.
*/
    // In-memory array to store the menu items.
    private ArrayList<Menu> menuItems;

    // Uses Singleton pattern
    private volatile static MenuRepository instance = null;

    public static MenuRepository getInstance() {
        if (instance == null) {
            synchronized (MenuRepository.class) {
                if (instance == null) {
                    instance = new MenuRepository();
                }
            }
        }
        return instance;
    }

    /**
     * Constructor
     */
    private MenuRepository() {
        menuItems = new ArrayList<>();
    }

    /**
     * Add a menu item to the repository.
     * @param menuItem The menu item to be added.
     */
    public void createMenu(Menu menuItem) {
        menuItems.add(menuItem);
    }

    /**
     * Get all the menu items in the repository.
     * @return An array of all the menu items.
     */
    public ArrayList<Menu> getAllMenuItems() {
        return menuItems;
    }

    /**
     * Get a menu item by its name.
     * @param name The name of the menu item to search.
     * @return The menu item with the given name.
     */
    public Optional<Menu> getMenuByName(String name) {
        return menuItems.stream()
                .filter(menuItem -> menuItem.getName().equals(name))
                .findFirst();
    }

    /**
     * Delete a menu item.
     * @param menuItem The menu item to be deleted.
     */
    public void deleteMenu(Menu menuItem) {
        menuItems.remove(menuItem);
    }

    /**
     * Update a menu item.
     * @param menuItem The menu item to be updated.
     */
    public void modifyMenu(Menu menuItem) {
        menuItems.remove(menuItem);
        menuItems.add(menuItem);
    }
}

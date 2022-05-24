package menu.observers;

import menu.interfaces.Observer;

/**
 * This class manages the outdoor board display.
 * @author Mina_
 * @version 1.0
 * @see menu.interfaces.Observer
 * @see menu.interfaces.NotifyElement
 */
public class OutdoorBoardModifierModule implements Observer {
    String boardText = "";

    @Override
    public void create(String name, int price) {
        System.out.println("OutdoorBoardModifierModule accepted create signal ------------");
        // change display text to new created menu item
        boardText = name + " " + price;
        System.out.println(boardText);
        System.out.println("create signal handle done ------------");
    }

    @Override
    public void modify(String lastName, int lastPrice, String name, int price) {
        System.out.println("OutdoorBoardModifierModule accepted modify signal ------------");
        // change display text to modified menu item
        boardText = name + " " + price;
        System.out.println(boardText);
        System.out.println("modify signal handle done ------------");
    }

    @Override
    public void delete(String name) {
        System.out.println("OutdoorBoardModifierModule accepted delete signal ------------");
        // change display text to deleted menu item
        boardText = name;
        System.out.println(boardText);
        System.out.println("delete signal handle done ------------");
    }
}

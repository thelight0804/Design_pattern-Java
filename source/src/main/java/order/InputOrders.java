package order;

import lombok.AllArgsConstructor;
import order.factory.AlcoholOrderFactory;
import order.factory.DrinkOrderFactory;
import order.factory.FoodOrderFactory;
import order.factory.SideMenuOrderFactory;

public class InputOrders {
    /**
     * Collection of InputOrder objects
     */
    // Need class: FoodInputOrder, DrinkInputOrder, AlcoholInputOrder, SideMenuInputOrder
    @AllArgsConstructor
    public static class FoodInputOrder implements Order {
        // Factory class dependency
        private FoodOrderFactory foodFactory;

        @Override
        public void execute(String menu, int amount) {
            // creates the Food order and adds it to the collection
        }
    }

    @AllArgsConstructor
    public static class DrinkInputOrder implements Order {
        // Factory class dependency
        private DrinkOrderFactory drinkFactory;

        @Override
        public void execute(String menu, int amount) {
            // creates the Drink order and adds it to the collection
        }
    }

    @AllArgsConstructor
    public static class AlcoholInputOrder implements Order {
        // Factory class dependency
        private AlcoholOrderFactory alcoholFactory;

        @Override
        public void execute(String menu, int amount) {
            // creates the Alcohol order and adds it to the collection
        }
    }

    @AllArgsConstructor
    public static class SideMenuInputOrder implements Order {
        // Factory class dependency
        private SideMenuOrderFactory sideMenuFactory;

        @Override
        public void execute(String menu, int amount) {
            // creates the SideMenu order and adds it to the collection
        }
    }

}

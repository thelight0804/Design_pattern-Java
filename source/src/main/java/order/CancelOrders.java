package order;

public class CancelOrders {
    /**
     * Collection of the commands to cancel orders.
     */
    // Need class: FoodCancelOrder, DrinkCancelOrder, AlcoholCancelOrder, SideMenuCancelOrder

    public static class FoodCancelOrder implements Order{
        @Override
        public void execute(String menu, int amount) {

        }
    }

    public static class DrinkCancelOrder implements Order{
        @Override
        public void execute(String menu, int amount) {

        }
    }

    public static class AlcoholCancelOrder implements Order{
        @Override
        public void execute(String menu, int amount) {

        }
    }

    public static class SideMenuCancelOrder implements Order{
        @Override
        public void execute(String menu, int amount) {

        }
    }
}

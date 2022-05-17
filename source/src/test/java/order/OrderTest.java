package order;

import order.factory.AlcoholOrderFactory;
import order.factory.DrinkOrderFactory;
import order.factory.FoodOrderFactory;
import order.factory.SideMenuOrderFactory;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTest {
    
    @Test @DisplayName("Command Pattern Test")
    public void commandPatternTest(){
        // Factory Pattern may be initialized by Singleton Pattern.
        // This test can be vary to project development situation.
        FoodOrderFactory foodFactory = new FoodOrderFactory();
        DrinkOrderFactory drinkFactory = new DrinkOrderFactory();
        AlcoholOrderFactory alcoholFactory = new AlcoholOrderFactory();
        SideMenuOrderFactory sideMenuFactory = new SideMenuOrderFactory();

        // Define Command Pattern invoker.
        RemoteControl remoteControl = new RemoteControl();

        // Fill the dependency of each Orders.
        remoteControl.setOrder(0,
                new InputOrders.FoodInputOrder(foodFactory),
                new CancelOrders.FoodCancelOrder());
        remoteControl.setOrder(1,
                new InputOrders.DrinkInputOrder(drinkFactory),
                new CancelOrders.DrinkCancelOrder());
        remoteControl.setOrder(2,
                new InputOrders.AlcoholInputOrder(alcoholFactory),
                new CancelOrders.AlcoholCancelOrder());
        remoteControl.setOrder(3,
                new InputOrders.SideMenuInputOrder(sideMenuFactory),
                new CancelOrders.SideMenuCancelOrder());

    }
    
}

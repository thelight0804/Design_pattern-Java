package order.input;

/**
 * 어플로 주문했을 때 작용되는 클래스
 */
public class App implements OrderType{
    public void takeOrder(){
        System.out.println("Order to App");
    }
}

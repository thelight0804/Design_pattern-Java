package order.input;

/**
 * 방문으로 주문했을 때 작용되는 클래스
 */
public class Visit implements OrderType{
    public void takeOrder(){
        System.out.println("Order to Call");
    }
}
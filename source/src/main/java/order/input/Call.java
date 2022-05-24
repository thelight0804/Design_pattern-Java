package order.input;

/**
 * 전화로 주문했을 때 작용되는 클래스
 */
public class Call implements OrderType{
    public void takeOrder(){
        System.out.println("Order to Call");
    }
}

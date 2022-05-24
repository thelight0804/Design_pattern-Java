package order.input;

/**
 * 배달 방식을 구현하는 클래스
 */
public class Delivery implements ServeType{
    public void serving(){
        System.out.println("배달");
    }
}
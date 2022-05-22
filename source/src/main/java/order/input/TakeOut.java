package order.input;

/**
 * 포장 방식을 구현하는 클래스
 */
public class TakeOut implements ServeType{
    public void serving(){
        System.out.println("포장");
    }
}
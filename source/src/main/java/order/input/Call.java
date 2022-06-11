package order.input;

/**
 * 주문 방식을 전화로 정하는 클래스
 */
public class Call implements OrderType {
    public void takeOrder(ServeType serveType) {
        String order = "전화";
        String serve = serveType.serving();
        System.out.println("[" + order + " 주문]");
    }
}

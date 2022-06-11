package order.input;

/**
 * 주문 방식을 어플로 정하는 클래스
 */
public class App implements OrderType {
    public void takeOrder(ServeType serveType) {
        String order = "어플";
        String serve = serveType.serving();
        System.out.println("[" + order + serve + " 주문]");
    }
}

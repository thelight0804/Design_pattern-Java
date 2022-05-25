package order.input;

/**
 * 주문 방식을 방문으로 정하는 클래스
 */
public class Visit implements OrderType{
    public void takeOrder(ServeType serveType){
        String order = "방문";
        String serve = serveType.serving();
        System.out.println("[" + order + serve + " 주문]");
    }
}
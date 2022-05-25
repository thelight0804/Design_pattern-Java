package order.input;

/**
 * 주문 방식을 정하는 interface
 */
public interface OrderType {
    void takeOrder(ServeType serveType);
}

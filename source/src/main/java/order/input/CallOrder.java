package order.input;
import menu.Menu;
import java.util.List;

/**
 * 전화로 주문했을 때 적용되는 클래스
 */
public class CallOrder extends Order {
  public CallOrder(List<Menu> orders, ServeType serveType) {
    super(orders);
    orderType = new Call();
    this.serveType = serveType;
  }
}

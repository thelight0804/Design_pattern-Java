package order.input;
import menu.Menu;
import java.util.List;

/**
 * 방문으로 주문했을 때 적용되는 클래스
 */
public class VisitOrder extends Order {
  public VisitOrder(List<Menu> orders, ServeType serveType) {
    super(orders);
    orderType = new Visit();
    this.serveType = serveType;
  }

}

package order.input;
import menu.Menu;
import java.util.List;

/**
 * 어플로 주문했을 때 적용되는 클래스
 */
public class AppOrder extends Order {
  public AppOrder(List<Menu> orders, ServeType serveType) {
    super(orders);
    orderType = new App();
    this.serveType = serveType;
  }

}

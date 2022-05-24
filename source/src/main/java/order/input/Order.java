package order.input;
import java.util.ArrayList;

/**
 * 주문 클래스
 */
public abstract class Order {
  private OrderType orderType;
  private ServeType serveType;
  private ArrayList<Menu> menuList = new ArrayList<Menu>();

  public void runOrder() {
  }

  public void runServe() {
  }

  public void setServeType(ServeType st) {
  }

  public int getMenuPrices() {
  }

  public String getOrderSheet() {
  }
}

package order.input;
import menu.Menu;
import repository.MenuRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * 주문 클래스
 */
public abstract class Order {
  protected OrderType orderType;
  protected ServeType serveType;
  List<Menu> menuList = new ArrayList<Menu>();

  public Order(List<Menu> menuList){
    this.menuList = menuList;
  }

  public void runOrder() {
    orderType.takeOrder(serveType);
  }

  public void setServeType(ServeType st) {
    serveType = st;
  }

  public int getMenuPrices() {
    int priceSum = menuList.stream().mapToInt(Menu::getPrice).sum();
    return priceSum;
  }

  public String getOrderSheet(List<Menu> menuList) {
    String count = "주문 수량 : " + Integer.toString(menuList.size()) + "개";
    return count;
  }
}

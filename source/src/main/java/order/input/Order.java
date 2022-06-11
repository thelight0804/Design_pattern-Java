package order.input;

import lombok.AccessLevel;
import lombok.Getter;
import menu.Menu;

import java.util.ArrayList;
import java.util.List;

/**
 * 주문 클래스
 */
public abstract class Order {
    protected OrderType orderType;
    protected ServeType serveType;
    List<Menu> menuList = new ArrayList<Menu>();

    public Order(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public void runOrder() {
        orderType.takeOrder(serveType);
    }

    public void finishOrder() {
        System.out.println(serveType.serving());
    }

    public void setServeType(ServeType st) {
        serveType = st;
    }

    public int getMenuPrices() {
        int priceSum = menuList.stream().mapToInt(Menu::getPrice).sum();
        return priceSum;
    }

    public String getOrderSheet() {
        StringBuilder sb = new StringBuilder();
        // print number of menus first
        sb.append(menuList.size()).append("개 메뉴가 존재합니다. \n");
        // print menu names
        menuList.forEach(menu -> sb.append(menu.getName()).append("\n"));
        // print total price
        sb.append("총 가격은 ").append(getMenuPrices()).append("원 입니다.");
        return sb.toString();
    }
}

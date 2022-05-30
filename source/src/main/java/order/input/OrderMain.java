package order.input;

import menu.Menu;
import repository.MenuRepository;
import java.util.ArrayList;
import java.util.List;

/**
 * 주문 받는 main 클래스
 */
public class OrderMain {
    static MenuRepository menuRepository = MenuRepository.getInstance();
    public static void main(String[] args){
        //메뉴 추가
        menuRepository.createMenu(new Menu("팔보채", 23000));
        menuRepository.createMenu(new Menu("짜장면", 6000));
        menuRepository.createMenu(new Menu("짬뽕", 7000));
        menuRepository.createMenu(new Menu("탕수육", 20000));
        
        //주문 추가
        List<Menu> menuList = new ArrayList<Menu>();
        menuList.add(menuRepository.getMenuByName("짜장면").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("짬뽕").orElseThrow(null));

        //전화 + 홀 주문
        CallOrder callDiningOrder = new CallOrder(menuList, new Dining());
        callDiningOrder.runOrder(); //주문 방식 출력
        System.out.println(callDiningOrder.menuList.toString()); //주문서 출력
        System.out.println(callDiningOrder.getMenuPrices() + "원"); //총 가격 출력
        System.out.println(callDiningOrder.getOrderSheet(menuList));
        System.out.println();

        //전화 + 배달 주문
        menuList.clear(); //주문서 초기화
        menuList.add(menuRepository.getMenuByName("짜장면").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("팔보채").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("짬뽕").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("짬뽕").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("짬뽕").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("탕수육").orElseThrow(null));

        CallOrder callDeliveryOrder = new CallOrder(menuList, new Delivery());
        callDeliveryOrder.runOrder();
        System.out.println(callDeliveryOrder.menuList.toString());
        System.out.println(callDeliveryOrder.getMenuPrices() + "원");
        System.out.println(callDeliveryOrder.getOrderSheet(menuList));
        System.out.println();

        //전화 + 포장 주문
        menuList.clear();
        menuList.add(menuRepository.getMenuByName("탕수육").orElseThrow(null));

        CallOrder callTakeOutOrder = new CallOrder(menuList, new TakeOut());
        callTakeOutOrder.runOrder();
        System.out.println(callTakeOutOrder.menuList.toString());
        System.out.println(callTakeOutOrder.getMenuPrices() + "원");
        System.out.println(callTakeOutOrder.getOrderSheet(menuList));
        System.out.println();

        //어플 + 홀 주문
        menuList.clear();
        menuList.add(menuRepository.getMenuByName("탕수육").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("짜장면").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("짬뽕").orElseThrow(null));

        AppOrder appDiningOrder = new AppOrder(menuList, new Dining());
        appDiningOrder.runOrder();
        System.out.println(appDiningOrder.menuList.toString());
        System.out.println(appDiningOrder.getMenuPrices() + "원");
        System.out.println(appDiningOrder.getOrderSheet(menuList));
        System.out.println();

        //어플 + 배달 주문
        menuList.clear();
        menuList.add(menuRepository.getMenuByName("탕수육").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("짜장면").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("짬뽕").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("짬뽕").orElseThrow(null));

        AppOrder appDeliveryOrder = new AppOrder(menuList, new Delivery());
        appDeliveryOrder.runOrder();
        System.out.println(appDeliveryOrder.menuList.toString());
        System.out.println(appDeliveryOrder.getMenuPrices() + "원");
        System.out.println(appDeliveryOrder.getOrderSheet(menuList));
        System.out.println();

        //어플 + 포장 주문
        menuList.clear();
        menuList.add(menuRepository.getMenuByName("탕수육").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("짜장면").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("짬뽕").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("팔보채").orElseThrow(null));

        AppOrder appTakeOutOrder = new AppOrder(menuList, new TakeOut());
        appTakeOutOrder.runOrder();
        System.out.println(appTakeOutOrder.menuList.toString());
        System.out.println(appTakeOutOrder.getMenuPrices() + "원");
        System.out.println(appTakeOutOrder.getOrderSheet(menuList));
        System.out.println();

        //방문 + 홀 주문
        menuList.clear();
        menuList.add(menuRepository.getMenuByName("탕수육").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("팔보채").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("팔보채").orElseThrow(null));

        VisitOrder visitDiningOrder = new VisitOrder(menuList, new Dining());
        visitDiningOrder.runOrder();
        System.out.println(visitDiningOrder.menuList.toString());
        System.out.println(visitDiningOrder.getMenuPrices() + "원");
        System.out.println(visitDiningOrder.getOrderSheet(menuList));
        System.out.println();

        //방문 + 배달 주문
        menuList.clear();
        menuList.add(menuRepository.getMenuByName("짜장면").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("짜장면").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("짬뽕").orElseThrow(null));

        VisitOrder visitDeliveryOrder = new VisitOrder(menuList, new Delivery());
        visitDeliveryOrder.runOrder();
        System.out.println(visitDeliveryOrder.menuList.toString());
        System.out.println(visitDeliveryOrder.getMenuPrices() + "원");
        System.out.println(visitDeliveryOrder.getOrderSheet(menuList));
        System.out.println();

        //방문 + 포장 주문
        menuList.clear();
        menuList.add(menuRepository.getMenuByName("탕수육").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("탕수육").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("짜장면").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("짜장면").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("짜장면").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("짬뽕").orElseThrow(null));
        menuList.add(menuRepository.getMenuByName("짬뽕").orElseThrow(null));

        VisitOrder visitTakeOutOrder = new VisitOrder(menuList, new TakeOut());
        visitTakeOutOrder.runOrder();
        System.out.println(visitTakeOutOrder.menuList.toString());
        System.out.println(visitTakeOutOrder.getMenuPrices() + "원");
        System.out.println(visitTakeOutOrder.getOrderSheet(menuList));
        System.out.println();
    }
}

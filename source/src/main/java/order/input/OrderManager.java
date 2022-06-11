package order.input;

import conf.enums.UserType;
import conf.interfaces.EndpointElement;
import conf.interfaces.Manager;
import conf.middleware.Console;
import conf.middleware.SessionStorage;
import lombok.AllArgsConstructor;
import lombok.Getter;
import menu.Menu;
import repository.MenuRepository;
import repository.OrderRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * 주문 받는 main 클래스
 */
public class OrderManager implements Manager {
    MenuRepository menuRepository = MenuRepository.getInstance();
    OrderRepository orderRepository = OrderRepository.getInstance();

    // Singleton Pattern
    private volatile static OrderManager instance = null;
    public static OrderManager getInstance() {
        if (instance == null) {
            synchronized (OrderManager.class) {
                if (instance == null) {
                    instance = new OrderManager();
                }
            }
        }
        return instance;
    }

    public void createOrder() {
        // Get order information from console
        // loop while user types -1 as menu name
        List<Menu> menus = new ArrayList<>();
        while (true) {
            String menuName = Console.getInput("메뉴를 입력하세요: ");
            if (menuName.equals("-1")) {
                break;
            }
            Optional<Menu> menu = menuRepository.getMenuByName(menuName);
            if (!menu.isPresent()) {
                System.out.println("존재하지 않는 메뉴입니다.");
                continue;
            }
            menus.add(menu.get());
        }
        // check menus name again
        System.out.println("주문하신 메뉴는 다음과 같습니다.");
        menus.forEach(menu -> {
            System.out.println(menu.getName() + ": " + menu.getPrice() + "원");
        });
        // set pickup type
        System.out.println("배달 유형을 선택하세요");
        for (ServingType type : ServingType.values()){
            System.out.println(type.getName());
        }
        String servingType = Console.getInput("선택: ");
        ServingType servingTypeEnum = ServingType.getServeType(servingType);
        if (servingTypeEnum == null) {
            System.out.println("잘못된 입력입니다.");
            return;
        }
        // set order type
        System.out.println("주문 유형을 선택하세요");
        for (OrderType type : OrderType.values()){
            System.out.println(type.getName());
        }
        String orderType = Console.getInput("선택: ");
        OrderType orderTypeEnum = OrderType.getOrderType(orderType);
        if (orderTypeEnum == null) {
            System.out.println("잘못된 입력입니다.");
            return;
        }
        // create Order
        Order order = orderTypeEnum.getOrderSupplier().apply(menus, servingTypeEnum.getServeTypeSupplier().get());

        // add order to repository
        int ordered = orderRepository.addOrder(order);

        System.out.println("주문이 완료되었습니다. 주문 번호는 " + ordered + "입니다.");
    }

    public void cancelOrder() {
        int orderId = Integer.parseInt(Console.getInput("주문 번호를 입력하세요: "));
        orderRepository.removeOrder(orderId);

        System.out.println("주문이 취소되었습니다.");
    }

    public void retrieveOrder() {
        orderRepository.getOrderList().forEach((orderNum, orderData) -> {
            System.out.println(orderNum + ": " + orderData.getOrderSheet());
        });
    }

    @Override
    public void run() {
        while(true) {
            if (!Manager.invokeMenu(OrderEndpointContext.values())) break;
        }
    }

    private enum OrderEndpointContext implements EndpointElement {
        CREATE_ORDER {
            @Override public Runnable getRunner() {
                return OrderManager.getInstance()::createOrder;
            }
            @Override public String getName() {
                return "주문 받기";
            }
            @Override public String getDescription() {
                return "주문을 받습니다.";
            }
            @Override public Function<UserType, Boolean> requireAuthentication() {
                return userType -> true;
            }
        },
        CANCEL_ORDER {
            @Override public Runnable getRunner() {
                return OrderManager.getInstance()::cancelOrder;
            }
            @Override public String getName() {
                return "주문 취소";
            }
            @Override public String getDescription() {
                return "주문을 취소합니다.";
            }
            @Override public Function<UserType, Boolean> requireAuthentication() {
                return userType -> true;
            }
        },
        RETRIEVE_ORDER {
            @Override public Runnable getRunner() {
                return OrderManager.getInstance()::retrieveOrder;
            }
            @Override public String getName() {
                return "주문 조회";
            }
            @Override public String getDescription() {
                return "주문을 조회합니다.";
            }
            @Override public Function<UserType, Boolean> requireAuthentication() {
                return userType -> true;
            }
        }
    }

    @AllArgsConstructor @Getter
    private enum ServingType {
        DELIVERY("배달", Delivery::new),
        TAKEOUT("픽업", TakeOut::new),
        DINING("식사", Dining::new);

        private final String name;
        private final Supplier<ServeType> serveTypeSupplier;

        public static ServingType getServeType(String name) {
            for (ServingType type : values()) {
                if (type.getName().equals(name)) {
                    return type;
                }
            }
            return null;
        }
    }

    @AllArgsConstructor @Getter
    private enum OrderType{
        DELIVERY("어플리케이션", AppOrder::new),
        TAKEOUT("전화", CallOrder::new),
        DINING("방문", VisitOrder::new);

        private final String name;
        private final BiFunction<List<Menu>, ServeType, Order> OrderSupplier;

        public static OrderType getOrderType(String name) {
            for (OrderType type : values()) {
                if (type.getName().equals(name)) {
                    return type;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) {
        SessionStorage.getInstance().getStorage().put("user", UserType.ADMIN);
        OrderManager orderManager = OrderManager.getInstance();
        orderManager.run();
    }
}

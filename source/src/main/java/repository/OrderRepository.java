package repository;

import order.input.Order;

import java.util.HashMap;
import java.util.Map;

public class OrderRepository {
    // Singleton pattern
    private volatile static OrderRepository instance = null;
    public static OrderRepository getInstance() {
        if (instance == null) {
            synchronized (OrderRepository.class) {
                if (instance == null) {
                    instance = new OrderRepository();
                }
            }
        }
        return instance;
    }

    Map<Integer, Order> orderStorage = new HashMap<>();
    Integer nextOrderId = 0;

    public int addOrder(Order order) {
        orderStorage.put(nextOrderId, order);
        return nextOrderId++;
    }

    public Order getOrder(int orderId) {
        return orderStorage.get(orderId);
    }

    public void removeOrder(int orderId) {
        orderStorage.remove(orderId);
    }

    public Map<Integer, Order> getOrderList() {
        return orderStorage;
    }

}

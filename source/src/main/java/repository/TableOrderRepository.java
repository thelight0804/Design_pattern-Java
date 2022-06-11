package repository;

import order.input.Order;
import table.Table;

import java.util.HashMap;
import java.util.Map;

public class TableOrderRepository {
    // Singleton pattern
    private volatile static TableOrderRepository instance = null;
    public static TableOrderRepository getInstance() {
        if (instance == null) {
            synchronized (TableOrderRepository.class) {
                if (instance == null) {
                    instance = new TableOrderRepository();
                }
            }
        }
        return instance;
    }

    private Map<Table, Order> tableOrderStorage = new HashMap<>();

    // Add order to table
    public void addOrder(Table table, Order order) {
        tableOrderStorage.put(table, order);
    }

    // Get order from table
    public Order getOrder(Table table) {
        return tableOrderStorage.get(table);
    }

    // Remove order from table
    public void removeOrder(Table table) {
        tableOrderStorage.remove(table);
    }

    // Get all orders
    public Map<Table, Order> getOrderList() {
        return tableOrderStorage;
    }
}

package repository;

import menu.sales.Sales;

import java.util.ArrayList;
import java.util.List;

/**
 * This class is used to store the sales information.
 * Can be connected to the database.
 * @version 1.0
 */
public class SalesRepository {
/*
TODO: you should connect this class to the database On production
*/
    List<Sales> salesStorage = new ArrayList<>();

    // Uses signleton pattern
    private static SalesRepository instance = null;

    public static SalesRepository getInstance() {
        if (instance == null) {
            instance = new SalesRepository();
        }
        return instance;
    }

    public void addSales(Sales sales) {
        salesStorage.add(sales);
    }

    public List<Sales> getSales() {
        return salesStorage;
    }

}

package repository;

import table.Table;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class TableRepository {
    // Singleton pattern
    private volatile static TableRepository instance;
    public static TableRepository getInstance() {
        if (instance == null) {
            synchronized (TableRepository.class) {
                if (instance == null) {
                    instance = new TableRepository();
                }
            }
        }
        return instance;
    }

    private List<Table> tables;

    private TableRepository() {
        // Create table
        this.tables = new ArrayList<>();
    }

    public void createTable(Table table) {
        this.tables.add(table);
    }

    public List<Table> getTables() {
        return this.tables;
    }

    public Optional<Table> searchTableByNumber(String tableNum) {
        return this.tables.stream()
                .filter(table -> Objects.equals(table.getTableNum(), tableNum))
                .findFirst();
    }
}

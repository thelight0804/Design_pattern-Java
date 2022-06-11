package table;

import conf.enums.UserType;
import conf.interfaces.EndpointElement;
import conf.interfaces.Manager;
import conf.middleware.Console;
import conf.middleware.SessionStorage;
import employee.Employee;
import menu.Menu;
import order.input.Dining;
import order.input.Order;
import order.input.VisitOrder;
import repository.MenuRepository;
import repository.OrderRepository;
import repository.TableOrderRepository;
import repository.TableRepository;
import repository.exception.EntityNotFoundException;

import java.util.List;
import java.util.function.Function;

public class TableManagement implements Manager {
    // Use singleton pattern
    private volatile static TableManagement instance = null;

    public static TableManagement getInstance() {
        if (instance == null) {
            synchronized (TableManagement.class) {
                if (instance == null) {
                    instance = new TableManagement();
                }
            }
        }
        return instance;
    }

    private TableRepository tableRepository = TableRepository.getInstance();

    public void retrieveTables() {
        List<Table> tables = tableRepository.getTables();
        tables.forEach(table -> {
            System.out.println("테이블: " + table.getTableNum() + "[" + table.getTableCapacity() + "] : " + table.getTableStatus());
            TableOrderRepository tableOrderRepository = TableOrderRepository.getInstance();
            Order tableOrder = tableOrderRepository.getOrder(table);
            if (tableOrder != null) {
                System.out.println(" 주문: " + tableOrder.getOrderSheet());
            }
        });
    }

    public void createTable() {
        // get table number and capacity
        String tableNum = Console.getInput("테이블 번호를 입력하세요: ");
        int tableCapacity = Integer.parseInt(Console.getInput("테이블 인원을 입력하세요: "));

        // create table
        tableRepository.createTable(Table.builder()
                .tableNum(tableNum)
                .tableCapacity(tableCapacity)
                .tableStatus(0)
                .build());

        System.out.println("테이블이 생성되었습니다.");
    }

    public void assignOrder() {
        // get table number
        String tableNum = Console.getInput("테이블 번호를 입력하세요: ");
        Table table;
        try {
            table = tableRepository.searchTableByNumber(tableNum).orElseThrow(EntityNotFoundException::new);
        } catch (EntityNotFoundException e) {
            System.out.println("존재하지 않는 테이블입니다.");
            return;
        }
        // get order number
        int orderId = Integer.parseInt(Console.getInput("주문 번호를 입력하세요: "));
        // get order from orderRepository
        Order order = OrderRepository.getInstance().getOrder(orderId);
        if (order == null){
            System.out.println("존재하지 않는 주문입니다.");
            return;
        }

        // assign order to table
        TableOrderRepository.getInstance().addOrder(table, order);
        System.out.println("주문이 테이블에 배정되었습니다.");
        table.setTableStatus(1);
    }

    @Override
    public void run() {
        while(true) {
            if (!Manager.invokeMenu(TableEndpointContext.values())) break;
        }
    }

    public static void main(String[] args) {
        MenuRepository.getInstance().createMenu(Menu.builder().name("짜장면").price(6000).build());
        MenuRepository.getInstance().createMenu(Menu.builder().name("탕수육").price(13000).build());
        SessionStorage.getInstance().getStorage().put("user", UserType.ADMIN);
        TableManagement.getInstance().run();
    }

    private enum TableEndpointContext implements EndpointElement {
        // TODO: Retrieve table list, assign order to table(decorator pattern) should be implemented
        RETRIEVE_TABLE_LIST {
            @Override public Runnable getRunner() {
                return TableManagement.getInstance()::retrieveTables;
            }
            @Override public String getName() {
                return "테이블 목록 조회";
            }
            @Override public String getDescription() {
                return "테이블 목록을 조회합니다.";
            }
            @Override public Function<UserType, Boolean> requireAuthentication() {
                return userType -> true;
            }
        },
        ASSIGN_ORDER_TO_TABLE {
            @Override public Runnable getRunner() {
                return TableManagement.getInstance()::assignOrder;
            }
            @Override public String getName() {
                return "주문 전달";
            }
            @Override public String getDescription() {
                return "주문을 테이블에 전달합니다.";
            }
            @Override public Function<UserType, Boolean> requireAuthentication() {
                return (user) -> true;
            }
        },
        CREATE_TABLE {
            @Override public Runnable getRunner() {
                return TableManagement.getInstance()::createTable;
            }
            @Override public String getName() {
                return "테이블 생성";
            }
            @Override public String getDescription() {
                return "테이블을 생성합니다.";
            }
            @Override public Function<UserType, Boolean> requireAuthentication() {
                return UserType.ADMIN::equals;
            }
        }
    }
}

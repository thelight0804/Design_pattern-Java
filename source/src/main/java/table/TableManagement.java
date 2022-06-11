package table;

import conf.enums.UserType;
import conf.interfaces.EndpointElement;
import conf.interfaces.Manager;
import conf.middleware.SessionStorage;
import employee.Employee;
import repository.TableRepository;

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
        });
    }


    @Override
    public void run() {
        while(true) {
            if (!Manager.invokeMenu(TableEndpointContext.values())) break;
        }
    }

    public static void main(String[] args) {
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
                return null;
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
                return null;
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

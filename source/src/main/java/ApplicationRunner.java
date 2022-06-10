import conf.interfaces.Manager;
import conf.interfaces.MenuElement;
import employee.EmployeeManagement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import menu.MenuManagement;

import java.util.function.Supplier;

public class ApplicationRunner implements Manager {


    public static void main(String[] args) {
        ApplicationRunner runner = new ApplicationRunner();
        runner.run();
    }

    @Override
    public void run() {
        while(true) {
            if (!Manager.invokeMenu(ApplicationContext.values())) break;
        }
    }
    /**
     * This enum is used to store the UI items.
     * Should have a Manager's getInstance() as field.
     */
    private enum ApplicationContext implements MenuElement {
        EMPLOYEE_MANAGEMENT{
            @Override public String getName() {
                return "직원 관리";
            }
            @Override public String getDescription() {
                return "직원을 관리할 수 있습니다.";
            }
            @Override public Supplier<Manager> getManagerSupplier() {
                return EmployeeManagement::getInstance;
            }
        },
        MENU_MANAGEMENT{
            @Override public String getName() {
                return "메뉴 관리";
            }
            @Override public String getDescription() {
                return "메뉴를 관리할 수 있습니다.";
            }
            @Override public Supplier<Manager> getManagerSupplier() {
                return MenuManagement::getInstance;
            }
        }
        ;
    }
}

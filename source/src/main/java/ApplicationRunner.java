import conf.enums.UserType;
import conf.interfaces.Manager;
import conf.interfaces.MenuElement;
import conf.middleware.Console;
import conf.middleware.SessionStorage;
import employee.Employee;
import employee.EmployeeManagement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import menu.MenuManagement;
import repository.EmployeeRepository;
import setting.SettingManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class ApplicationRunner implements Manager {


    public static void main(String[] args) {
        ApplicationRunner runner = new ApplicationRunner();
        while (Objects.equals(Console.getInput("로그인 하시겠습니까? (y/n)"), "y")) {
            runner.login();
        }
    }

    private void login() {
        // Retrieve name/password from console
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final String name = Console.getInput("이름을 입력하세요: ");
        final String password = Console.getInput("비밀번호를 입력하세요: ");

        // Check name and password is not null
        if (name == null || password == null) {
            System.out.println("Invalid input.");
            return;
        }
        if (UserType.getByName(name) == null) {
            System.out.println("사용자가 존재하지 않습니다.");
            return;
        }
        if (!UserType.getByName(name).getPassword().equals(password)){
            System.out.println("비밀번호가 일치하지 않습니다.");
            return;
        }
        System.out.println("로그인에 성공했습니다.");
        this.run();
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
            @Override public Function<UserType, Boolean> requireAuthentication() {
                return (user) -> true;
            }
        },
        MENU_MANAGEMENT{
            @Override public String getName() {
                return "메뉴 관리";
            }
            @Override public String getDescription() {
                return "메뉴를 관리할 수 있습니다.";
            }
            @Override public Function<UserType, Boolean> requireAuthentication() {
                return (user) -> user.equals(UserType.ADMIN);
            }
            @Override public Supplier<Manager> getManagerSupplier() {
                return MenuManagement::getInstance;
            }
        },
        ORDER_MANAGEMENT{
            @Override public String getName() {
                return "주문 관리";
            }
            @Override public String getDescription() {
                return "주문을 관리할 수 있습니다.";
            }
            @Override public Function<UserType, Boolean> requireAuthentication() {
                return (user) -> true;
            }
            @Override public Supplier<Manager> getManagerSupplier() {
                return null; // TODO: make OrderManagement class
            }
        },
        SETTING {
            @Override public String getName() {
                return "설정";
            }
            @Override public String getDescription() {
                return "설정을 관리할 수 있습니다.";
            }
            @Override public Function<UserType, Boolean> requireAuthentication() {
                return (user) -> user.equals(UserType.ADMIN);
            }
            @Override public Supplier<Manager> getManagerSupplier() {
                return SettingManager::getInstance;
            }
        }
    }
}

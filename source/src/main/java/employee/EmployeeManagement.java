package employee;

import conf.enums.UserType;
import conf.interfaces.EndpointElement;
import conf.interfaces.Manager;
import conf.middleware.Console;
import employee.commute.CommuteManager;
import employee.exception.NoSpaceForCommandException;
import repository.EmployeeRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

public class EmployeeManagement implements Manager {
    // Use singleton pattern
    private volatile static EmployeeManagement instance = null;
    public static EmployeeManagement getInstance() {
        if (instance == null) {
            synchronized (EmployeeManagement.class) {
                if (instance == null) {
                    instance = new EmployeeManagement();
                }
            }
        }
        return instance;
    }

    EmployeeRepository employeeRepository = EmployeeRepository.getInstance();

    protected void createEmployee() {
        // Get employee information from console
        final String name = Console.getInput("직원 이름을 등록하세요: ");
        final String password =  Console.getInput("직원 비밀번호를 등록하세요: ");
        // check name and password is not null
        if (name == null || password == null) {
            System.out.println("Invalid input.");
            return;
        }
        // Create employee
        Employee employee = Employee.builder()
                .name(name)
                .password(password)
                .build();

        // generate index of command slot for employee commute management
        // find empty slot
        try {
            CommuteManager commuteManager = CommuteManager.getInstance();
            int index = commuteManager.findEmptyIndex();
            System.out.println("로그인을 위한 개인 번호는 " + index + " 입니다. 잊지 않게 주의하세요!");
            employeeRepository.addEmployee(employee);

        } catch (NoSpaceForCommandException e) {
            System.err.println("There is no empty slot for new employee.");
            return;
        }
    }

    public void RetrieveEmployees() {
        employeeRepository.getEmployees().forEach(System.out::println);
    }

    /**
     * Change employee's password
     * @deprecated This method is deprecated due to security reason.
     */
    @Deprecated
    public void changePassword() {
        // Get employee information from console
        final String name = Console.getInput("이름을 입력하세요");
        final String password = Console.getInput("비밀번호를 입력하세요");
        // check name and password is not null
        if (name == null || password == null) {
            System.out.println("입력에 에러가 있습니다.");
            return;
        }
        // change password
        Optional<Employee> employee = employeeRepository.searchEmployeeByName(name);
        if (!employee.isPresent()) {
            System.out.println("존재하지 않는 직원입니다.");
            return;
        }
        if (employee.get().getPassword().equals(password)) {
            System.out.println("비밀번호가 틀렸습니다.");
            return;
        }
        employee.get().setPassword(password);
        System.out.println("비밀번호를 변경하였습니다.");
    }

    @Override
    public void run() {
        while(true){
            if (!Manager.invokeMenu(EmployeeManagementEndpoint.values())) break;
        }
    }

    enum EmployeeManagementEndpoint implements EndpointElement {
        EMPLOYEE_CREATE{
            @Override public String getName() {
                return "직원 추가";
            }
            @Override public String getDescription() {
                return "직원을 데이터베이스에 주가합니다.";
            }
            @Override public Function<UserType, Boolean> requireAuthentication() {
                return userType -> userType == UserType.ADMIN;
            }
            @Override public Runnable getRunner() {
                return EmployeeManagement.getInstance()::createEmployee;
            }
        },
        RETRIEVE_EMPLOYEE{
            @Override public Runnable getRunner() {
                return EmployeeManagement.getInstance()::RetrieveEmployees;
            }
            @Override public String getName() {
                return "직원 조회";
            }
            @Override public String getDescription() {
                return "직원 목록을 조회합니다.";
            }
            @Override public Function<UserType, Boolean> requireAuthentication() {
                return userType -> userType == UserType.ADMIN;
            }
        },
        COMMUTE_MANAGEMENT{
            @Override public Runnable getRunner() {
                return CommuteManager.getInstance()::run;
            }
            @Override public String getName() {
                return "출퇴근 관리";
            }
            @Override public String getDescription() {
                return "출퇴근 관리를 진행합니다.";
            }
            @Override public Function<UserType, Boolean> requireAuthentication() {
                return userType -> true;
            }
        }
    }
}


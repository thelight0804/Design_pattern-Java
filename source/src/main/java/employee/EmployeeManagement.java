package employee;

import conf.interfaces.EndpointElement;
import conf.interfaces.Manager;
import employee.commute.CommuteManager;
import repository.EmployeeRepository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        final String name = ((Supplier<String>) () -> {
            System.out.print("Enter employee name: ");
            try {
                return reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).get();
        final String password = ((Supplier<String>) () -> {
            System.out.print("Enter employee password: ");
            try {
                return reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).get();
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
        // Add employee to repository
        employeeRepository.addEmployee(employee);

        // generate index of command slot for employee commute management
        // find empty slot

        
    }

    @Override
    public void run() {
        System.out.println("직원 관리");

    }

    enum EmployeeManagementEndpoint implements EndpointElement {
        EMPLOYEE_CREATE{
            @Override public String getName() {
                return "직원 추가";
            }
            @Override public String getDescription() {
                return "직원을 데이터베이스에 주가합니다.";
            }
            @Override public Runnable getRunner() {
                return EmployeeManagement.getInstance()::createEmployee;
            }
        }
    }
}


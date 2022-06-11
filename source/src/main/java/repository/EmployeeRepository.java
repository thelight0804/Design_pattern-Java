package repository;

import employee.Employee;
import repository.exception.EntityNotFoundException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EmployeeRepository {
    /*
    TODO : 각 직원의 정보를 저장하는 공간을 생성하는 과정을 작성하세요. 차후 DB에 연결하는 로직 또한 작성해야 합니다.
     */
    List<Employee> employees = new ArrayList<>();

    // Uses signleton pattern
    private static EmployeeRepository instance = null;
    public static EmployeeRepository getInstance() {
        if (instance == null) {
            instance = new EmployeeRepository();
            instance.addEmployee(Employee.builder()
                    .name("sajang")
                    .password("password").build());
        }
        return instance;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public Optional<Employee> searchEmployeeByName(String name) {
        return employees.stream()
                .filter(employee -> employee.getName().equals(name))
                .findFirst();
    }
}

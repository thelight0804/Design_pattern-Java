package employee;

import lombok.*;

@Getter @Setter
public class Employee {

    private String name;
    private String password;

    @Builder
    public Employee(String name, String password) {
        this.name = name;
        this.password = password;
    }

    @Setter(AccessLevel.NONE)
    private Boolean admin = false;

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", admin=" + admin +
                '}';
    }
}

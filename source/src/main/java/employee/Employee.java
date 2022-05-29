package employee;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @Builder
public class Employee {
    private String name;
    private String password;
}

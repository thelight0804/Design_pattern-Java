package conf.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserType {
    // TODO: you should get user password from environment variable
    ADMIN("ADMIN", "관리자", "admin_password"),
    EMPLOYEE("EMPLOYEE", "직원", "employee_password");

    private final String roles;
    private final String name;
    private final String password;

    public static UserType getByName(String name) {
        for (UserType userType : UserType.values()) {
            if (userType.name.equals(name)) {
                return userType;
            }
        }
        return null;
    }
}

package conf.interfaces;

import conf.enums.UserType;
import employee.Employee;

import java.util.function.Function;

public interface Element {
    String getName();

    String getDescription();

    /**
     * check if the user is authenticated to use this context.
     *
     * @return true if the user is authenticated to use this context.
     */
    Function<UserType, Boolean> requireAuthentication();
}

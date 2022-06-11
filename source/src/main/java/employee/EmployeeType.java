package employee;

import employee.commute.receiver.CommandReceiver;
import employee.commute.receiver.DeliveryEmployee;
import employee.commute.receiver.DiningEmployee;
import employee.commute.receiver.KitchenEmployee;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

@AllArgsConstructor @Getter
public enum EmployeeType {
    DINING_EMPLOYEE("홀"),
    KITCHEN_EMPLOYEE("부엌"),
    DELIVERY_EMPLOYEE("배달"),
    ;

    private final String name;
    public static EmployeeType getEmployeeType(String name) {
        return Arrays.stream(EmployeeType.values())
                .filter(employeeType -> employeeType.getName().equals(name))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("EmployeeType is not supported."));
    }
}

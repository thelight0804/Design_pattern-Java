package employee;

import employee.commute.receiver.CommandReceiver;
import employee.commute.receiver.DeliveryEmployee;
import employee.commute.receiver.DiningEmployee;
import employee.commute.receiver.KitchenEmployee;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.List;
import java.util.function.Function;

@AllArgsConstructor @Getter
public enum EmployeeType {
    DINING_EMPLOYEE(),
    KITCHEN_EMPLOYEE(),
    DELIVERY_EMPLOYEE(),
    ;
}

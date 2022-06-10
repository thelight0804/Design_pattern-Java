package employee.factory;

import employee.Employee;
import employee.EmployeeType;
import employee.commute.command.CommuteCommand;
import employee.commute.command.OffWorkCommand;
import employee.commute.command.OnWorkCommand;
import employee.commute.receiver.CommandReceiver;
import employee.commute.receiver.full.FullTimeDeliveryEmployee;
import employee.commute.receiver.full.FullTimeDiningEmployee;
import employee.commute.receiver.full.FullTimeKitchenEmployee;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.function.Function;

public class FullTimeCommandFactory extends EmployeeCommandFactory {

    @Override
    public CommuteCommand createOnWorkCommand(EmployeeType employeeType, Employee employee) {
        return new OnWorkCommand(
                FullTimeEmployeeType.getFullTimeEmployeeType(employeeType)
                        .getCommandReceiverSupplier()
                        .apply(employee));
    }

    @Override
    public CommuteCommand createOffWorkCommand(EmployeeType employeeType, Employee employee) {
        return new OffWorkCommand(
                FullTimeEmployeeType.getFullTimeEmployeeType(employeeType)
                        .getCommandReceiverSupplier()
                        .apply(employee));
    }

    @AllArgsConstructor @Getter
    private enum FullTimeEmployeeType {
        FULL_DINING_EMPLOYEE(EmployeeType.DINING_EMPLOYEE, FullTimeDiningEmployee::new),
        FULL_DELIVERY_EMPLOYEE(EmployeeType.DELIVERY_EMPLOYEE, FullTimeDeliveryEmployee::new),
        FULL_KITCHEN_EMPLOYEE(EmployeeType.KITCHEN_EMPLOYEE, FullTimeKitchenEmployee::new),
        ;

        private final EmployeeType employeeType;
        private final Function<Employee, CommandReceiver> CommandReceiverSupplier;
        public static FullTimeEmployeeType getFullTimeEmployeeType(EmployeeType employeeType) {
            return Arrays.stream(FullTimeEmployeeType.values())
                    .filter(fullTimeEmployeeType -> fullTimeEmployeeType.getEmployeeType().equals(employeeType))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("EmployeeType is not supported."));
        }
    }
}

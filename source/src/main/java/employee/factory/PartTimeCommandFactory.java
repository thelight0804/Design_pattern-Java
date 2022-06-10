package employee.factory;

import employee.Employee;
import employee.EmployeeType;
import employee.commute.command.CommuteCommand;
import employee.commute.command.OffWorkCommand;
import employee.commute.command.OnWorkCommand;
import employee.commute.receiver.CommandReceiver;
import employee.commute.receiver.part.PartTimeDeliveryEmployee;
import employee.commute.receiver.part.PartTimeDiningEmployee;
import employee.commute.receiver.part.PartTimeKitchenEmployee;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Arrays;
import java.util.function.Function;

public class PartTimeCommandFactory extends EmployeeCommandFactory {

    @Override
    public CommuteCommand createOnWorkCommand(EmployeeType employeeType, Employee employee) {
        return new OnWorkCommand(
                PartTimeEmployeeType.getPartTimeEmployeeType(employeeType)
                        .getCommandReceiverSupplier()
                        .apply(employee));
    }

    @Override
    public CommuteCommand createOffWorkCommand(EmployeeType employeeType, Employee employee) {
        return new OffWorkCommand(
                PartTimeEmployeeType.getPartTimeEmployeeType(employeeType)
                        .getCommandReceiverSupplier()
                        .apply(employee));
    }

    @AllArgsConstructor @Getter
    enum PartTimeEmployeeType {
        FULL_DINING_EMPLOYEE(EmployeeType.DINING_EMPLOYEE, PartTimeDiningEmployee::new),
        FULL_DELIVERY_EMPLOYEE(EmployeeType.DELIVERY_EMPLOYEE, PartTimeDeliveryEmployee::new),
        FULL_KITCHEN_EMPLOYEE(EmployeeType.KITCHEN_EMPLOYEE, PartTimeKitchenEmployee::new),
        ;

        private final EmployeeType employeeType;
        private final Function<Employee, CommandReceiver> CommandReceiverSupplier;
        public static PartTimeCommandFactory.PartTimeEmployeeType getPartTimeEmployeeType(EmployeeType employeeType) {
            return Arrays.stream(PartTimeCommandFactory.PartTimeEmployeeType.values())
                    .filter(fullTimeEmployeeType -> fullTimeEmployeeType.getEmployeeType().equals(employeeType))
                    .findAny()
                    .orElseThrow(() -> new IllegalArgumentException("EmployeeType is not supported."));
        }
    }
}

package employee.factory;

import employee.Employee;
import employee.EmployeeType;
import employee.commute.command.CommuteCommand;
import employee.commute.command.OnWorkCommand;

/**
 * This factory class creates Command objects for commute management.
 * @version 0.9
 * @see employee.commute.command.CommuteCommand
 */
public abstract class EmployeeCommandFactory {
    abstract public CommuteCommand createOnWorkCommand(EmployeeType employeeType, Employee employee);
    abstract public CommuteCommand createOffWorkCommand(EmployeeType employeeType, Employee employee);
}

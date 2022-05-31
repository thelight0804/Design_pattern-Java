package employee.commute.command;

/**
 * This class work as a command role on command pattern.
 * execute() method is used to execute the command.
 */
public interface CommuteCommand {
    /**
     * This method is used to execute the command.
     * Please override this method and javadoc on each concrete command.
     */
    public void execute();
}

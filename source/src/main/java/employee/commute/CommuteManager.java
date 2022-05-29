package employee.commute;

import employee.commute.command.CommuteCommand;

import java.util.LinkedList;
import java.util.List;

/**
 * This class works as a Invoker in the Command Pattern.
 * Client should depends this class to manage the Commute.
 */
public class CommuteManager {
    private final int MAX_EMPLOYEE = 10;
    private List<CommuteCommand> onWorkCommands = new LinkedList<CommuteCommand>();
    private List<CommuteCommand> offWorkCommands = new LinkedList<CommuteCommand>();

    public void setCommuteCommand(int index, CommuteCommand onWorkCommand, CommuteCommand offWorkCommand) {
        onWorkCommands.set(index, onWorkCommand);
        offWorkCommands.set(index, offWorkCommand);
    }

    public CommuteManager() { }

    public void onWork(int index) {
        onWorkCommands.get(index).execute();
    }

    public void offWork(int index) {
        offWorkCommands.get(index).execute();
    }


}

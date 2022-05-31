package employee.commute.command;

import employee.commute.receiver.CommandReceiver;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OnWorkCommand implements CommuteCommand{
    private CommandReceiver receiver;

    @Override
    public void execute() {
        receiver.onWork();
    }
}

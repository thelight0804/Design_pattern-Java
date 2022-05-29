package employee.commute.command;

import employee.commute.receiver.CommandReceiver;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OffWorkCommand implements CommuteCommand{
    private CommandReceiver receiver;

    @Override
    public void execute() {
        receiver.offWork();
    }
}

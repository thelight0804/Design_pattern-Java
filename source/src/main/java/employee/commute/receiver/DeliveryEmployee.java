package employee.commute.receiver;

import employee.Employee;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor
public class DeliveryEmployee implements CommandReceiver {
    private final int HOURLY_WAGE = 15000;
    private Employee employee;

    @Override
    public void onWork() {
        // start timer to record time of delivery to calculate wage
        System.out.println("Delivery Employee " + employee.getName() + " is on work");
        System.out.println("Counting time from now... : " + LocalDateTime.now());
    }

    @Override
    public void offWork() {
        // stop timer, calculate wage
        System.out.println("Delivery Employee " + employee.getName() + " is off work");
        System.out.println("Current time : " + LocalDateTime.now());
    }

}

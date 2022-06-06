package employee.commute.receiver;

import employee.Employee;
import lombok.AllArgsConstructor;

import java.io.BufferedReader;
import java.time.Duration;
import java.time.LocalDateTime;

@AllArgsConstructor
public class DiningEmployee implements CommandReceiver {
    private final int HOURLY_WAGE = 9160;
    private Employee employee;
    private Duration workTime;
    private LocalDateTime startTime;

    @Override
    public void onWork() {
        // start timer to record time of delivery to calculate wage
        // check password to block other people's access.
        // If customer want some additional security service, please add here before timer starts(ex. fingerprint)
        // Start timer
        if (!checkPassword()) {
            return;
        }
        System.out.println("Dining Employee " + employee.getName() + " is on work");
        startTime = LocalDateTime.now();
        System.out.println("Current time : " + startTime);
    }

    @Override
    public void offWork() {
        // Calculate wage
        // Check password
        if (!checkPassword()) {
            return;
        }
        System.out.println("Dining Employee " + employee.getName() + " is off work");
        LocalDateTime endTime = LocalDateTime.now();
        workTime = Duration.between(startTime, endTime);
        System.out.println("Work time : " + workTime);
        System.out.println("Wage : " + (workTime.toMinutes() * HOURLY_WAGE));
        // TODO: You should add logic for save wage to database.
    }

    public boolean checkPassword() {
        // Initialize BufferedReader to read password
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        System.out.println("Please enter password to start timer: ");
        try {
            // Check password is equal to password in employee's record
            String password = br.readLine();
            if (password.equals(employee.getPassword())){
                System.out.println("Password is correct");
                return true;
            } else {
                System.out.println("Wrong password");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return false;
    }

}
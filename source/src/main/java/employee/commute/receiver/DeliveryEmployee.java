package employee.commute.receiver;

import employee.Employee;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.time.DurationFormatUtils;

import java.io.BufferedReader;
import java.time.Duration;
import java.time.LocalDateTime;


@RequiredArgsConstructor
public class DeliveryEmployee implements CommandReceiver {
    private final int HOURLY_WAGE = 15000;
    private final Employee employee;
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
        // Check that employee is checked checklist of Delivery Employee
        if (!checkChecklistStatus()) {
            System.out.println("Checklist is failed");
            return;
        }

        System.out.println("Delivery Employee " + employee.getName() + " is on work");
        startTime = LocalDateTime.now();
        System.out.println("Current time : " + startTime);
    }

    @Override
    public void offWork() {
        // Calculate wage
        // Check password
        if (startTime == null) {
            System.out.println("Employee is not on work");
            return;
        }
        if (!checkPassword()) {
            return;
        }
        System.out.println("Delivery Employee " + employee.getName() + " is off work");
        LocalDateTime endTime = LocalDateTime.now();
        workTime = Duration.between(startTime, endTime);
        System.out.println("Work time : " +
                DurationFormatUtils.formatDuration(workTime.toMillis(), "HH:mm:ss", true));
        System.out.println("Wage : " + (workTime.toHours() * HOURLY_WAGE));
        // TODO: You should add logic for save wage to database.
        startTime = null;
    }
    private boolean checkPassword() {
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

    private boolean checkChecklistStatus() {
        BufferedReader br = new BufferedReader(new java.io.InputStreamReader(System.in));
        boolean checkedAll = true;
        try {
            for (DeliveryEmployeeChecklist question : DeliveryEmployeeChecklist.values()) {
                // Print question and wait for answer
                System.out.print(question.getQuestion() + "(Y/N): ");
                if (br.readLine().equals("Y") != question.isAnswer()){
                    checkedAll = false;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            checkedAll = false;
        }
        return checkedAll;
    }
}

@AllArgsConstructor @Getter
enum DeliveryEmployeeChecklist {
    HELMET_EQUIPMENT_STATUS ("헬멧을 쓰고 있습니까?", true),
    FUEL_STATUS ("주유가 정상적으로 되어 있습니까?", true),
    GPS_STATUS ("GPS가 정상적으로 작동하고 있습니까?", true),
    SPEED_LIMITER_STATUS ("속도 제한기가 정상적으로 작동 중입니까?", true),
    ;

    private String question;
    private boolean answer;
}
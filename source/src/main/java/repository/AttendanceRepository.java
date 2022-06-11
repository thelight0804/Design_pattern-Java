package repository;

import employee.Attendance;
import employee.Employee;
import menu.Menu;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AttendanceRepository {

    // In-memory array to store the menu items.
    private List<Attendance> attendances;

    // Uses Singleton pattern
    private volatile static AttendanceRepository instance = null;

    public static AttendanceRepository getInstance() {
        if (instance == null) {
            synchronized (AttendanceRepository.class) {
                if (instance == null) {
                    instance = new AttendanceRepository();
                }
            }
        }
        return instance;
    }

    /**
     * Constructor
     */
    private AttendanceRepository() {
        this.attendances = new ArrayList<>();
    }

    /**
     * Add a work log schedule to the repository.
     * @param attendance The work log schedule is to be added.
     */
    public void createAttendance(Attendance attendance) {
        attendances.add(attendance);
    }

    /**
     * Get all work log schedule in the repository.
     * @return An array of all the work log schedule
     */
    public List<Attendance> getAllAttendances() {
        return attendances;
    }

    /**
     * Get a work log schedule from its employee.
     * @param employee The employee to search.
     * @return The attendance  with the given employee.
     */
    public Optional<Attendance> getAttendanceByEmployee(Employee employee) {
        return attendances.stream()
                .filter(attendance -> attendance.getEmployee().equals(employee))
                .findFirst();
    }

    /**
     * Get a work log schedule from its employee.
     * @param name The name of the employee to search.
     * @return The attendance with the given employees name.
     */
    public Optional<Attendance> getAttendanceByName(String name) {
        return attendances.stream()
                .filter(attendance -> attendance.getEmployee().getName().equals(name))
                .findFirst();
    }

}

package employee;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter @Setter
@AllArgsConstructor @Builder
public class Attendance {
    private Employee employee;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Long wage;

    @Override
    public String toString() {
        return "" +
                "employee=" + employee +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", wage=" + wage;
    }
}

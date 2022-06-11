package sales;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * This class works as a entity class for the sales data.
 */
@Getter @Setter @Builder
@AllArgsConstructor
public class Sales {
    private Long transaction;
    private LocalDateTime timestamp;
    private TransactionType type;
}

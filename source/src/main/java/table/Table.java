package table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Builder
public class Table {
    String tableNum;
    int tableCapacity;
    int tableStatus;
}

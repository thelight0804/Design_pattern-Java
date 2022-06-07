package sales.print.printer;

import repository.SalesRepository;
import sales.Sales;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * This class represents the printer for the sales.<br>
 * This under-decorator-class means no filter is applied to the sales data.
 */
public class SalesPrint {
    List<Sales> sales;

    /**
     * This constructor retrieves whole sales list from SalesRepository.
     * Create this class to print whole sales list.
     */
    public SalesPrint() {
        sales = SalesRepository.getInstance().getSales().stream()
                .sorted(Comparator.comparing(Sales::getTimestamp).reversed())
                .collect(Collectors.toList());
    }

    /**
     * Returns the sales list on specified format. Override this method on decorator class to apply format.
     * @return sales list represented by string on specified format
     */
    public String print() {
        return "--매출 출력 종료--";
    }
}

package sales.print.printer.decorator;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sales.Sales;
import sales.print.printer.SalesPrint;

import java.util.ArrayList;
import java.util.List;

public class YearlySalesPrinter extends PrintDecorator {

    /**
     * Constructor.
     * @param printer The SalesPrint object to be decorated.
     */
    public YearlySalesPrinter(SalesPrint printer) {
        this.parentPrinter = printer;
    }

    @Override
    public String print() {
        StringBuilder sb = new StringBuilder();
        sb.append(parentPrinter.print());
        sb.append("\n");
        sb.append("Yearly sales: \n");
        List<YearlySalesDto> yearlySales = new ArrayList<>();
        for (Sales sales : parentPrinter.getSales()) {
            YearlySalesDto search = yearlySales.stream()
                    .filter(e -> e.getYear() == sales.getTimestamp().getYear())
                    .findFirst()
                    .orElse(null);

            if (search == null) {
                yearlySales.add(YearlySalesDto.builder()
                        .year(sales.getTimestamp().getYear())
                        .sales(sales.getTransaction())
                        .build());
            } else {
                search.setSales(search.getSales() + sales.getTransaction());
            }
        }
        yearlySales.forEach(e -> sb.append(e.getYear()).append(": ").append(e.getSales()).append("\n"));
        return sb.toString();
    }
}

@Builder @Getter @Setter
class YearlySalesDto {
    private int year;
    private Long sales;
}
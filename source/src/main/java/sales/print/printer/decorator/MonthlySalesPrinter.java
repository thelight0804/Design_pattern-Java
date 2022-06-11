package sales.print.printer.decorator;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import sales.Sales;
import sales.print.printer.SalesPrint;

import java.time.Month;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;

public class MonthlySalesPrinter extends PrintDecorator{

    /**
     * Constructor.
     * @param printer The SalesPrint object to be decorated.
     */
    public MonthlySalesPrinter(SalesPrint printer) {
        this.parentPrinter = printer;
    }

    @Override
    public String print() {
        // TODO: need working source code for printing monthly sales
        StringBuilder sb = new StringBuilder();
        sb.append(parentPrinter.print());
        sb.append("\n");
        sb.append("Monthly sales: \n");
        List<MonthlySalesDto> monthlySales = new ArrayList<>();
        for (Sales sales : parentPrinter.getSales()) {
            MonthlySalesDto search = monthlySales.stream()
                    .filter(e -> e.getMonth() == sales.getTimestamp().getMonth())
                    .filter(e -> e.getYear() == sales.getTimestamp().getYear())
                    .findFirst()
                    .orElse(null);

            if (search == null) {
                monthlySales.add(MonthlySalesDto.builder()
                        .month(sales.getTimestamp().getMonth())
                        .year(sales.getTimestamp().getYear())
                        .sales(sales.getTransaction())
                        .build());
            } else {
                search.setSales(search.getSales() + sales.getTransaction());
            }
        }
        monthlySales.forEach(e -> sb.append(e.getYear()).append("/").append(e.getMonth().getValue())
                .append(": ").append(e.getSales()).append("\n"));
        return sb.toString();
    }
}

@Builder @Getter @Setter
class MonthlySalesDto {
    private int year;
    private Month month;
    private Long sales;
}

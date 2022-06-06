package sales.print.printer;

import sales.Sales;
import sales.TransactionType;

import java.util.Comparator;
import java.util.stream.Collectors;

public class CardSalesPrint extends SalesPrint {
    public CardSalesPrint() {
        super();
        sales = sales.stream()
                .filter(s -> s.getType() == TransactionType.CARD_PAYMENT || s.getType() == TransactionType.CARD_REFUND)
                .collect(Collectors.toList());
    }
}

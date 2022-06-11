package menu.sales.print.printer;

import menu.sales.TransactionType;

import java.util.stream.Collectors;

public class GiftcardSalesPrint extends SalesPrint{

    public GiftcardSalesPrint() {
        super();
        sales = sales.stream()
                .filter(s -> s.getType() == TransactionType.GIFTCARD_PAYMENT || s.getType() == TransactionType.GIFTCARD_REFUND)
                .collect(Collectors.toList());
    }
}

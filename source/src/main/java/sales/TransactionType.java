package sales;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum TransactionType {
    // Enum for the type of transaction
    CARD_PAYMENT("Card Payment"),
    CASH_PAYMENT("Cash Payment"),
    GIFTCARD_PAYMENT("Gift Card Payment"),
    CARD_REFUND("Card Refund"),
    CASH_REFUND("Credit Refund"),
    GIFTCARD_REFUND("Gift Card Refund"),
    EMPLOYEE_PAYMENT("Employee Payment"),
    ;

    private final String type;
}

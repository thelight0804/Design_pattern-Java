package model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class Drink {
    // fields: menu(string), price(int), sale(float), amount(int)
    private String menu;
    private int price;
    private float sale;
    private int amount;
}

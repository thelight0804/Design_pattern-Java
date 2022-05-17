package model.order;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import stock.Ingredient;

import java.util.ArrayList;

@Getter @Setter
@AllArgsConstructor
public class Food {
    // fields: menu(string), price(int), sale(float), ingredient(ArrayList<Ingredient>), amount(int)
    private String menu;
    private int price;
    private float sale;
    private ArrayList<Ingredient> ingredient;
    private int amount;
}

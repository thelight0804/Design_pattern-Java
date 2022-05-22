package menu;

import lombok.*;

@Getter @Setter
@AllArgsConstructor
@Builder
public class Menu {
    private String name;
    private int price;

    @Override
    public String toString() {
        return String.format("메뉴 : %s, 가격 : %d원", name, price);
    }
}

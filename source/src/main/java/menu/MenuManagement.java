package menu;

import menu.interfaces.NotifyElement;
import menu.interfaces.Observer;
import menu.observers.SMSNotificationSender;
import repository.MenuRepository;
import repository.exception.EntityNotFoundException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class MenuManagement extends NotifyElement {
    private final MenuRepository menuRepository;

    public MenuManagement() {
        super();
        this.menuRepository = MenuRepository.getInstance();
    }

    public void createMenu(String name, int price) {
        this.menuRepository.createMenu(Menu.builder()
                .name(name)
                .price(price).build());
        this.onCreate(name, price);
    }

    public void modifyMenu(String prevName, String name, int price) {
        try{
            Menu menu = this.menuRepository.getMenuByName(prevName).orElseThrow(EntityNotFoundException::new);
            int prevPrice = menu.getPrice();
            menu.setName(name);
            menu.setPrice(price);
            this.onModify(prevName, prevPrice, name, price);
        }
        catch(EntityNotFoundException e){
            System.out.println("Menu not exists in repository.");
        }
    }

    public void deleteMenu(String name) {
        try{
            Menu menu = this.menuRepository.getMenuByName(name).orElseThrow(EntityNotFoundException::new);
            this.menuRepository.deleteMenu(menu);
            this.onDelete(menu.getName());
        }
        catch(EntityNotFoundException e){
            System.out.println("Menu not exists in repository.");
        }
    }

    public void run() {
        // Initialize buffered reader
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int option = 0;
        while(option != 4) {
            ArrayList<Menu> menuItems = this.menuRepository.getAllMenuItems();
            System.out.println("Menu Management ---------- \n" +
                    "Current menu items:");
            // Print menu list
            menuItems.forEach(menu -> System.out.println(menu.toString()));
            // Print Management options
            System.out.println("Select an option: \n" +
                    "1. Create menu item \n" +
                    "2. Modify menu item \n" +
                    "3. Delete menu item \n" +
                    "4. Exit");
            // Get user input
            System.out.print("Option: ");
            try {
                option = Integer.parseInt(br.readLine());
            } catch (Exception e) {
                System.err.println("숫자만 입력 가능합니다.");
                continue;
            }
            try {
                switch (option) {
                    // Create menu item
                    case 1:
                        System.out.print("Name: ");
                        String name = br.readLine();
                        System.out.print("Price: ");
                        int price = Integer.parseInt(br.readLine());
                        this.createMenu(name, price);
                        break;
                    // Modify menu item
                    case 2:
                        System.out.print("Menu: ");
                        String prevName = br.readLine();
                        System.out.print("Name to change: ");
                        name = br.readLine();
                        System.out.print("Price to change: ");
                        price = Integer.parseInt(br.readLine());
                        this.modifyMenu(prevName, name, price);
                        break;
                    // Delete menu item
                    case 3:
                        System.out.print("Name: ");
                        name = br.readLine();
                        this.deleteMenu(name);
                        break;
                    // Exit
                    case 4:
                        break;
                    default:
                        System.out.println("1에서 4까지만 입력 가능합니다.");
                }
            } catch (Exception e) {
                System.err.println("값을 잘못 입력했습니다. 다시 시도해주세요.");
            }
        }
    }

    // Run this method to do example runs
    // Warning: Do not use this method in production
    public static void main(String[] args) {
        MenuManagement menuManagement = new MenuManagement();
        SMSNotificationSender smsNotificationSender = new SMSNotificationSender();
        smsNotificationSender.subscribe("+82 10-1234-1234");
        smsNotificationSender.subscribe("+82 10-1234-5678");
        menuManagement.addObserver(smsNotificationSender);
        menuManagement.run();
    }
}

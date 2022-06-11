package menu;

import conf.enums.UserType;
import conf.interfaces.EndpointElement;
import conf.interfaces.Manager;
import conf.middleware.SessionStorage;
import employee.Employee;
import menu.interfaces.NotifyElement;
import menu.observers.AppNotificationSender;
import menu.observers.OutdoorBoardModifierModule;
import menu.observers.SMSNotificationSender;
import repository.MenuRepository;
import repository.exception.EntityNotFoundException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

public class MenuManagement extends NotifyElement implements Manager {
    private final MenuRepository menuRepository;

    // Use singleton pattern
    private static volatile MenuManagement instance = null;

    public static MenuManagement getInstance() {
        if (instance == null) {
            synchronized (MenuManagement.class) {
                if (instance == null) {
                    instance = new MenuManagement();
                }
            }
        }
        return instance;
    }

    private MenuManagement() {
        super();
        this.menuRepository = MenuRepository.getInstance();
    }

    public void createMenu() {
        try {
            // Create menu
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Name: ");
            String name = br.readLine();
            System.out.print("Price: ");
            int price = Integer.parseInt(br.readLine());
            this.menuRepository.createMenu(Menu.builder()
                    .name(name)
                    .price(price).build());
            this.onCreate(name, price);
        } catch (IOException e) {
            System.err.println("입력 중 문제가 발생했습니다.");
        } catch (NumberFormatException e) {
            System.err.println("잘못된 값을 입력했습니다.");
        }
    }

    public void modifyMenu() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("수장할 메뉴 이름을 입력하세요: ");
            String prevName = br.readLine();
            System.out.print("변경할 메뉴 이름을 입력하세요(변경사항 없을 시 그대로 입력하시기 바랍니다.): ");
            String name = br.readLine();
            System.out.print("변경된 메뉴 가격을 입력하세요: ");
            int price = Integer.parseInt(br.readLine());
            Menu menu = this.menuRepository.getMenuByName(prevName).orElseThrow(EntityNotFoundException::new);
            int prevPrice = menu.getPrice();
            menu.setName(name);
            menu.setPrice(price);
            this.onModify(prevName, prevPrice, name, price);
        } catch (IOException e) {
            System.err.println("입력 중 문제가 발생했습니다.");
        } catch (NumberFormatException e) {
            System.err.println("잘못된 값을 입력했습니다.");
        } catch (EntityNotFoundException e) {
            System.err.println("존재하지 않는 메뉴입니다.");
        }
    }

    public void deleteMenu() {
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Name: ");
            String name = br.readLine();
            Menu menu = this.menuRepository.getMenuByName(name).orElseThrow(EntityNotFoundException::new);
            this.menuRepository.deleteMenu(menu);
            this.onDelete(menu.getName());
        } catch (IOException e) {
            System.err.println("입력 중 문제가 발생했습니다.");
        } catch (EntityNotFoundException e) {
            System.out.println("메뉴 데이터가 존재하지 않습니다.");
        }
    }

    public void getMenuList() {
        this.menuRepository.getAllMenuItems().forEach(menu -> System.out.println("이름: " + menu.getName() + ", 가격: " + menu.getPrice()));
    }

    @Override
    public void run() {
        while (true) {
            if (!Manager.invokeMenu(MenuManagementEndpoint.values())) break;
        }
    }

    // Run this method to do example runs
    // Warning: Do not use this method in production
    public static void main(String[] args) {
        // Initialize MenuManagement Service
        MenuManagement menuManagement = MenuManagement.getInstance();
        // Create Observer
        SMSNotificationSender smsNotificationSender = new SMSNotificationSender();
        smsNotificationSender.subscribe("+82 10-1234-1234");
        smsNotificationSender.subscribe("+82 10-1234-5678");
        AppNotificationSender appNotificationSender = new AppNotificationSender();
        OutdoorBoardModifierModule outdoorBoardModifierModule = new OutdoorBoardModifierModule();
        // Add observer
        menuManagement.addObserver("SMS", smsNotificationSender);
        menuManagement.addObserver("APP", appNotificationSender);
        menuManagement.addObserver("OutdoorBoard", outdoorBoardModifierModule);
        // Run service
        SessionStorage.getInstance().getStorage().put("user", UserType.ADMIN);
        menuManagement.run();
    }

    private enum MenuManagementEndpoint implements EndpointElement {
        CREATE_MENU {
            @Override
            public String getName() {
                return "메뉴 생성";
            }
            @Override
            public String getDescription() {
                return "메뉴를 생성합니다.";
            }
            @Override
            public Function<UserType, Boolean> requireAuthentication() {
                return userType -> userType == UserType.ADMIN;
            }
            @Override
            public Runnable getRunner() {
                return MenuManagement.getInstance()::createMenu;
            }
        },
        MODIFY_MENU {
            @Override public String getName() {
                return "메뉴 수정";
            }
            @Override public String getDescription() {
                return "메뉴를 수정합니다.";
            }
            @Override public Function<UserType, Boolean> requireAuthentication() {
                return userType -> userType == UserType.ADMIN;
            }
            @Override public Runnable getRunner() {
                return MenuManagement.getInstance()::modifyMenu;
            }
        },
        DELETE_MENU {
            @Override public String getName() {
                return "메뉴 삭제";
            }
            @Override public String getDescription() {
                return "메뉴를 삭제합니다.";
            }
            @Override public Function<UserType, Boolean> requireAuthentication() {
                return userType -> userType == UserType.ADMIN;
            }
            @Override public Runnable getRunner() {
                return MenuManagement.getInstance()::deleteMenu;
            }
        },
        GET_MENU_LIST {
            @Override public String getName() {
                return "메뉴 목록 조회";
            }
            @Override public String getDescription() {
                return "메뉴 목록을 조회합니다.";
            }
            @Override public Function<UserType, Boolean> requireAuthentication() {
                return userType -> userType == UserType.ADMIN;
            }
            @Override public Runnable getRunner() {
                return MenuManagement.getInstance()::getMenuList;
            }
        },
    }
}

package setting.listener;

import conf.enums.UserType;
import conf.interfaces.EndpointElement;
import conf.interfaces.Manager;
import menu.MenuManagement;
import menu.observers.ObserversType;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Function;

public class ListenerManager implements Manager {
    // Singleton pattern
    private volatile static ListenerManager instance = null;
    public static ListenerManager getInstance() {
        if (instance == null) {
            synchronized (ListenerManager.class) {
                if (instance == null) {
                    instance = new ListenerManager();
                }
            }
        }
        return instance;
    }

    /**
     * Add new observer to the observer List - MenuManagement Class
     */
    public void subscribeListener() {
        MenuManagement Subject = MenuManagement.getInstance();
        int count = 1;
        for (ObserversType observer : ObserversType.values()) {
            System.out.printf("[%d] : %s\n", count++, observer.getName());
        }
        System.out.print("등록할 이벤트 리스너를 선택해주세요 : ");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            int observerNumber = Integer.parseInt(br.readLine());
            // if user input is not in the range of the enum, print error message and return
            if (observerNumber < 1 || observerNumber > ObserversType.values().length) {
                System.err.println("잘못된 인덱스를 입력하셨습니다.");
                return;
            }
            // Retrieve user's input for key's name and add it to the observer list
            System.out.print("이벤트 리스너의 이름을 입력해주세요 : ");
            String name = br.readLine();
            Subject.addObserver(name, ObserversType.values()[observerNumber - 1].getObserverSupplier().get());
        } catch (IOException e) {
            System.err.println("에러가 발생했습니다. : " + e.getMessage());
        }
    }

    /**
     * Release observer from the observer List - MenuManagement Class
     */
    public void unSubscribeListener() {
        MenuManagement Subject = MenuManagement.getInstance();
        System.out.println("삭제할 이벤트 리스너의 이름을 선택해주세요 : ");
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            String name = br.readLine();
            Subject.removeObserver(name);
        } catch (IOException e) {
            System.err.println("에러가 발생했습니다. : " + e.getMessage());
        }
    }

    @Override
    public void run() {
        do {
            // Print observer's list first
            System.out.println("현재 등록된 이벤트 리스너");
            MenuManagement.getInstance().getObserverNames().stream()
                    .map((str) -> {
                        return "[" + str + "]";
                    })
                    .forEach(System.out::println);

        } while (Manager.invokeMenu(ListenerMenuElement.values()));
    }

    private enum ListenerMenuElement implements EndpointElement {
        SUBSCRIBE_LISTENER{
            @Override public String getName() {
                return "이벤트 리스너 추가";
            }
            @Override public String getDescription() {
                return "메뉴 변경 시의 알림을 전송받을 이벤트 리스너를 추가합니다.";
            }
            @Override public Function<UserType, Boolean> requireAuthentication() {
                return (userType) -> {
                    return userType == UserType.ADMIN;
                };
            }
            @Override public Runnable getRunner() {
                return ListenerManager.getInstance()::subscribeListener;
            }
        },
    }
}

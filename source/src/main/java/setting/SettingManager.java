package setting;

import conf.enums.UserType;
import conf.interfaces.Manager;
import conf.interfaces.MenuElement;
import setting.listener.ListenerManager;

import java.util.function.Function;
import java.util.function.Supplier;

public class SettingManager implements Manager {

    // Singleton Pattern
    private volatile static SettingManager instance;
    public static SettingManager getInstance() {
        if (instance == null) {
            synchronized (SettingManager.class) {
                if (instance == null) {
                    instance = new SettingManager();
                }
            }
        }
        return instance;
    }

    @Override
    public void run() {
        while(true) {
            if (!Manager.invokeMenu(SettingContext.values())) break;
        }
    }

    private enum SettingContext implements MenuElement {
        SUBSCRIBE_LISTENER{
            @Override public String getName() {
                return "이벤트 리스너 설정";
            }
            @Override public String getDescription() {
                return "메뉴 변경 시의 알림을 전송받을 이벤트 리스너를 설정합니다.";
            }
            @Override public Function<UserType, Boolean> requireAuthentication() {
                return UserType.ADMIN::equals;
            }
            @Override public Supplier<Manager> getManagerSupplier() {
                return ListenerManager::getInstance;
            }
        }
    }
}

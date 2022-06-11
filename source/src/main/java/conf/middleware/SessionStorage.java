package conf.middleware;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

public class SessionStorage {
    // Singleton pattern
    private volatile static SessionStorage instance = null;

    public static SessionStorage getInstance() {
        if (instance == null) {
            synchronized (SessionStorage.class) {
                if (instance == null) {
                    instance = new SessionStorage();
                }
            }
        }
        return instance;
    }

    @Getter
    private final Map<String, Object> storage = new HashMap<>();

}

package sales;

import conf.interfaces.Manager;
import conf.middleware.SessionStorage;

public class SalesManager implements Manager {
    @Override
    public void run() {

    }

    /**
     * When this method is called, hardware will count cash on the stash
     * and save it to Session
     */
    private long countCash() {
        SessionStorage.getInstance().getStorage()
                .put("money",
                        Integer.parseInt("220000" /* TODO: This should be connected to Hardware logic*/)
                );
        return (long) SessionStorage.getInstance().getStorage().get("money");
    }
}

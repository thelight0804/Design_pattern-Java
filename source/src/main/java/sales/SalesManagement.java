package sales;

import conf.enums.UserType;
import conf.interfaces.EndpointElement;
import conf.interfaces.Manager;
import conf.middleware.SessionStorage;
import employee.EmployeeManagement;
import employee.commute.CommuteManager;

import java.util.function.Function;

public class SalesManagement implements Manager {

    // Use singleton pattern
    private static SalesManagement instance = new SalesManagement();
    public static SalesManagement getInstance() {
        if (instance == null) {
            instance = new SalesManagement();
        }
        return instance;
    }

    @Override
    public void run() {
        while(true){
            if (!Manager.invokeMenu(SalesManagement.SalesManagementEndpoint.values())) break;
        }
    }

    public void checkCash(){
        SessionStorage.getInstance()
                .getStorage().put("money", Integer.parseInt("220000" /* TODO: Should be connected to logic. */));
        Object money = SessionStorage.getInstance().getStorage().get("money");
        System.out.println("현재 돈통에 있는 현금 : " + money+ "원");
    }


    enum SalesManagementEndpoint implements EndpointElement {
        CHECK_CASH{
            @Override public String getName() {
                return "돈통 현금 잔액 조회";
            }
            @Override public String getDescription() {
                return "돈통에 있는 현금 잔액을 조회합니다.";
            }
            @Override public Function<UserType, Boolean> requireAuthentication() {
                return userType -> true;
            }
            @Override public Runnable getRunner() {
                return SalesManagement.getInstance()::checkCash;
            }
        }
    }

}

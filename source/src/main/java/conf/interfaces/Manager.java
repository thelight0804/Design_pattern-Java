package conf.interfaces;

import conf.enums.UserType;
import conf.exception.ExitToMenuMessageReceivedException;
import conf.exception.IllegalInputException;
import conf.middleware.SessionStorage;
import employee.Employee;
import menu.Menu;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public interface Manager {
    public void run();

    /**
     * This method is used to invoke the menu.
     *
     * @param contextArray Menu contexts enum list.
     * @return true if the menu is invoked. false means user choose abort.
     */
    static boolean invokeMenu(MenuElement[] contextArray) {
        try {
            List<MenuElement> contexts = new LinkedList<>(Arrays.asList(contextArray));
            MenuElement element = (MenuElement) selectMenu(contexts);
            System.out.println(element.getDescription());
            element.getManagerSupplier().get().run();
            return true;
        } catch (ExitToMenuMessageReceivedException e) {
            return false;
        } catch (Exception e) {
            System.out.println("오류가 발생했습니다.");
            return true;
        }
    }

    static boolean invokeMenu(EndpointElement[] contextArray) {
        try {
            List<EndpointElement> contexts = new LinkedList<>(Arrays.asList(contextArray));
            EndpointElement element = (EndpointElement) selectMenu(contexts);
            System.out.println(element.getDescription());
            element.getRunner().run();
            return true;
        } catch (ExitToMenuMessageReceivedException e) {
            return false;
        } catch (Exception e) {
            System.out.println("오류가 발생했습니다.");
            return true;
        }
    }

    static Element selectMenu(List<? extends Element> contextList) throws ExitToMenuMessageReceivedException, IllegalInputException {
        int count = 1;
        LinkedList<Element> contexts = new LinkedList();
        for (Element context : contextList) {
            if (context.requireAuthentication().apply((UserType) SessionStorage.getInstance().getStorage().get("user"))) {
                contexts.add(context);
            }
        }
        for (Element context : contexts) {
            System.out.printf("[%d]: %s\n", count++, context.getName());
        }
        System.out.printf("[%d]: 종료\n", count);
        System.out.print("원하는 동작을 선택하세요: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int selected = Integer.parseInt(reader.readLine());
            if (selected < 1 || selected > count) {
                System.out.println("잘못된 입력입니다.");
                throw new IllegalInputException();
            }
            if (selected == count) {
                throw new ExitToMenuMessageReceivedException();
            }
            return contexts.get(selected - 1);
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력 가능합니다.");
            throw new IllegalInputException();
        } catch (IOException e) {
            System.out.println("입력 오류입니다.");
            throw new IllegalInputException();
        }
    }
}
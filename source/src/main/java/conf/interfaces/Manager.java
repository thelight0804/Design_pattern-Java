package conf.interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public interface Manager {
    public void run();

    /**
     * This method is used to invoke the menu.
     * @param contexts Menu contexts enum list.
     * @return true if the menu is invoked. false means user choose abort.
     */
    static boolean invokeMenu(MenuElement[] contexts){
        int count = 1;
        for(MenuElement context : contexts){
            System.out.printf("[%d]: %s\n",count++, context.getName());
        }
        System.out.printf("[%d]: 종료\n", count);
        System.out.print("원하는 동작을 선택하세요: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int selected = Integer.parseInt(reader.readLine());
            if(selected < 1 || selected > count){
                System.out.println("잘못된 입력입니다.");
                return true;
            }
            if(selected == count){
                return false;
            }
            contexts[selected - 1].getManagerSupplier().get().run();
            return true;
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력 가능합니다.");
            return true;
        } catch (IOException e) {
            System.out.println("입력 오류입니다.");
            return true;
        }
    }

    static boolean invokeMenu(EndpointElement[] contexts) {
        int count = 1;
        for(EndpointElement context : contexts){
            System.out.printf("[%d]: %s\n",count++, context.getName());
        }
        System.out.printf("[%d]: 종료\n", count);
        System.out.print("원하는 동작을 선택하세요: ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        try {
            int selected = Integer.parseInt(reader.readLine());
            if(selected < 1 || selected > count){
                System.out.println("잘못된 입력입니다.");
                return true;
            }
            if(selected == count){
                return false;
            }
            contexts[selected - 1].getRunner().run();
            return true;
        } catch (NumberFormatException e) {
            System.out.println("숫자만 입력 가능합니다.");
            return true;
        } catch (IOException e) {
            System.out.println("입력 오류입니다.");
            return true;
        }
    }
}

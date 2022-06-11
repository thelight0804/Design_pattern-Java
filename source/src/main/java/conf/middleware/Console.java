package conf.middleware;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.function.Supplier;

public class Console {
    public static String getInput(String message) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        return ((Supplier<String>) () -> {
            System.out.print(message);
            try {
                return reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }).get();
    }
}

package menu.observers;

import menu.interfaces.Observer;

/**
 * This class is used to send notifications to api server.
 *
 * @author Mina_
 * @version 1.0
 * @see Observer
 * @see menu.interfaces.NotifyElement
 */
public class AppNotificationSender implements Observer {
    private final String APPLICATION_API_URL = "https://your.delivery.com/api/v1/app/notifications/menu";
    private final String APPLICATION_CREATE_NOTIFICATION_REQUEST_BODY = "{\"menu_name\":\"%s\",\"price\":\"%s\"}";
    private final String APPLICATION_MODIFY_NOTIFICATION_REQUEST_BODY =
            "{\"menu_name_before\":\"%s\",\"price_before\":\"%s\",\"menu_name_after\":\"%s\",\"price_after\":\"%s\"}";
    private final String APPLICATION_DELETE_NOTIFICATION_REQUEST_BODY = "{\"menu_name\":\"%s\"}";

    @Override
    public void create(String name, int price) {
        System.out.println("AppNotificationSender got create signal -----------");
        this.sendRequest(APPLICATION_API_URL,
                "POST",
                APPLICATION_CREATE_NOTIFICATION_REQUEST_BODY,
                new Object[]{name, price});
        System.out.println("AppNotificationSender sent create signal -----------");
    }

    @Override
    public void modify(String lastName, int lastPrice, String name, int price) {
        System.out.println("AppNotificationSender got modify signal -----------");
        this.sendRequest(APPLICATION_API_URL,
                "PUT",
                APPLICATION_MODIFY_NOTIFICATION_REQUEST_BODY,
                new Object[]{lastName, lastPrice, name, price});
        System.out.println("AppNotificationSender sent modify signal -----------");
    }

    @Override
    public void delete(String name) {
        System.out.println("AppNotificationSender got delete signal -----------");
        this.sendRequest(APPLICATION_API_URL,
                "DELETE",
                APPLICATION_DELETE_NOTIFICATION_REQUEST_BODY,
                new Object[]{name});
        System.out.println("AppNotificationSender sent delete signal -----------");
    }

    private void sendRequest(String url, String method, String bodyFormat, Object[] args) {
        String body = String.format(bodyFormat, args);
        String request = String.format("%s %s HTTP/1.1\r\n" +
                "Host: %s\r\n" +
                "Content-Type: application/json\r\n" +
                "Content-Length: %d\r\n" +
                "Connection: close\r\n" +
                "\r\n" +
                "%s", method, url, APPLICATION_API_URL, body.length(), body);
        System.out.println(request);
    }
}

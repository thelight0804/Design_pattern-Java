package menu.observers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import menu.interfaces.Observer;

import java.util.function.Supplier;

@AllArgsConstructor
@Getter
public enum ObserversType {
    SMS("SMS", "SMS로 알림을 전송합니다.", SMSNotificationSender::new),
    APP("APP", "앱으로 알림을 전송합니다.", AppNotificationSender::new),
    OUTDOOR_BOARD("OUTDOOR_BOARD", "옥외 전광판 수정 모듈에 데이터를 전송합니다.", OutdoorBoardModifierModule::new);

    private final String name;
    private final String description;
    private final Supplier<Observer> observerSupplier;
}

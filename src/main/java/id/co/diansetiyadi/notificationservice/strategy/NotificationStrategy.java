package id.co.diansetiyadi.notificationservice.strategy;

import id.co.diansetiyadi.notificationservice.entity.NotificationType;

public interface NotificationStrategy {

    void sendMessage(String message);
    NotificationType notificationType();
}

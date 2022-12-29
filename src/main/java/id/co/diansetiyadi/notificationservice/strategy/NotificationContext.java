package id.co.diansetiyadi.notificationservice.strategy;

import id.co.diansetiyadi.notificationservice.entity.NotificationType;
import id.co.diansetiyadi.notificationservice.strategy.exception.NotFoundNotificationStrategy;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Objects;

@AllArgsConstructor
@Component
public class NotificationContext {

    private final Map<NotificationType, NotificationStrategy> sendNotificationByType;

    public void sendMessage(String message, NotificationType notificationType) throws NotFoundNotificationStrategy {
        NotificationStrategy notificationStrategy = sendNotificationByType.getOrDefault(notificationType, null);
        if (Objects.isNull(notificationStrategy)) {
            throw new NotFoundNotificationStrategy("Notification type not found : "+notificationType);
        }
        notificationStrategy.sendMessage(message);
    }
}

package id.co.diansetiyadi.notificationservice.strategy.config;

import id.co.diansetiyadi.notificationservice.entity.NotificationType;
import id.co.diansetiyadi.notificationservice.strategy.NotificationStrategy;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

@Configuration
@AllArgsConstructor
public class StrategyConfig {

    private final List<NotificationStrategy> notificationStrategies;

    @Bean
    public Map<NotificationType, NotificationStrategy> sendNotificationByType() {
        Map<NotificationType, NotificationStrategy> messageByType = new EnumMap<>(NotificationType.class);
        notificationStrategies.forEach(notificationStrategy -> messageByType.put(notificationStrategy.notificationType(),notificationStrategy));
        return messageByType;
    }
}

package id.co.diansetiyadi.notificationservice.strategy.impl;

import id.co.diansetiyadi.notificationservice.config.TopicProducer;
import id.co.diansetiyadi.notificationservice.entity.NotificationType;
import id.co.diansetiyadi.notificationservice.service.KafkaNotificationService;
import id.co.diansetiyadi.notificationservice.strategy.NotificationStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class SMSNotificationStrategy implements NotificationStrategy {

    private final KafkaNotificationService kafkaNotificationService;

    @Autowired
    public SMSNotificationStrategy(KafkaNotificationService kafkaNotificationService) {
        this.kafkaNotificationService = kafkaNotificationService;
    }

    @Override
    public void sendMessage(String message) {
        log.info("message send to SMS");
        log.info("Publish Topic Message SMS");
        /**
         * todo logic
         * 1. Persist Data State
         * 2. Publish Message Data State
         */

        kafkaNotificationService.sendMessage(TopicProducer.NOTIFICATION_SMS, message);
    }

    @Override
    public NotificationType notificationType() {
        return NotificationType.SMS;
    }
}

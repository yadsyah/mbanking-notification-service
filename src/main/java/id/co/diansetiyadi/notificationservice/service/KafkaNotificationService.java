package id.co.diansetiyadi.notificationservice.service;

import id.co.diansetiyadi.notificationservice.entity.Notification;
import org.springframework.stereotype.Service;

@Service
public interface KafkaNotificationService {

    void PublishSendNotification(Notification notification);

    void sendMessage(String topicName, String message);
}

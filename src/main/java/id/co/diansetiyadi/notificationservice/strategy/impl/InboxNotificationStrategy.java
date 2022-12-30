package id.co.diansetiyadi.notificationservice.strategy.impl;

import com.google.gson.Gson;
import id.co.diansetiyadi.notificationservice.config.TopicProducer;
import id.co.diansetiyadi.notificationservice.dto.request.AddInboxRequest;
import id.co.diansetiyadi.notificationservice.entity.Notification;
import id.co.diansetiyadi.notificationservice.entity.NotificationType;
import id.co.diansetiyadi.notificationservice.service.KafkaNotificationService;
import id.co.diansetiyadi.notificationservice.strategy.NotificationStrategy;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class InboxNotificationStrategy implements NotificationStrategy {

    private final KafkaNotificationService kafkaNotificationService;
    private final Gson gson;

    @Autowired
    public InboxNotificationStrategy(KafkaNotificationService kafkaNotificationService, Gson gson) {
        this.kafkaNotificationService = kafkaNotificationService;
        this.gson = gson;
    }

    @Override
    public void sendMessage(String message) {
        log.info("message send to inbox");
        log.info("Publish Topic Message Inbox");
        /**
         * todo logic
         * 1. Persist Data State
         * 2. Publish Message Data State
         */
        Notification notification = gson.fromJson(message, Notification.class);

        AddInboxRequest addInboxRequest = new AddInboxRequest();
        addInboxRequest.setCif(notification.getCif());
        addInboxRequest.setMessageDetail(notification.getMessageDetailInbox());
        addInboxRequest.setCategoryInboxEnum(notification.getCategoryInboxEnum());
        addInboxRequest.setDeviceId("");
        addInboxRequest.setAppVersion(notification.getAppVersion());

        kafkaNotificationService.sendMessage(TopicProducer.NOTIFICATION_INBOX, gson.toJson(addInboxRequest));
    }

    @Override
    public NotificationType notificationType() {
        return NotificationType.INBOX;
    }
}

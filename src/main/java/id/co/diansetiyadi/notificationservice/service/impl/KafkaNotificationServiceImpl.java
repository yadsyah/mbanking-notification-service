package id.co.diansetiyadi.notificationservice.service.impl;

import id.co.diansetiyadi.notificationservice.entity.Notification;
import id.co.diansetiyadi.notificationservice.service.KafkaNotificationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class KafkaNotificationServiceImpl implements KafkaNotificationService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaNotificationServiceImpl(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    @Override
    public void PublishSendNotification(Notification notification) {

    }

    @Override
    public void sendMessage(String topicName, String message) {
        try {
            kafkaTemplate.send(topicName, message);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("[ERORR] : Send Fail Kafka");
        }
    }
}

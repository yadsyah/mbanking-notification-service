package id.co.diansetiyadi.notificationservice.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class TopicProducer {

    public static final String NOTIFICATION_FIREBASE = "notification_firebase";
    public static final String NOTIFICATION_MAIL = "notification_mail";
    public static final String NOTIFICATION_INBOX = "notification_inbox";
    public static final String NOTIFICATION_SMS = "notification_sms";
    public static final String NOTIFICATION_WHATSAPP = "notification_whatsapp";


    @Bean
    public NewTopic notificationFirebaseTopic() {
        return TopicBuilder.name(NOTIFICATION_FIREBASE).partitions(10).replicas(1).build();
    }

    @Bean
    public NewTopic notificationMailTopic() {
        return TopicBuilder.name(NOTIFICATION_MAIL).partitions(10).replicas(1).build();
    }

    @Bean
    public NewTopic notificationInboxTopic() {
        return TopicBuilder.name(NOTIFICATION_INBOX).partitions(10).replicas(1).build();
    }

    @Bean
    public NewTopic notificationSMSTopic() {
        return TopicBuilder.name(NOTIFICATION_SMS).partitions(10).replicas(1).build();
    }

    @Bean
    public NewTopic notificationWATopic() {
        return TopicBuilder.name(NOTIFICATION_WHATSAPP).partitions(10).replicas(1).build();
    }

}
